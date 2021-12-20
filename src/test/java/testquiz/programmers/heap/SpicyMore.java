package testquiz.programmers.heap;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class SpicyMore {

    @Test
    public void test() {

        int[][] foods = {
                {1, 2, 3, 9, 10, 12}
        };

        int[] k = {
                7
        };

        IntStream.range(0, foods.length)
                .forEach(n -> {
                    int result = solution(foods[n], k[n]);
                    log.info("## Result : {}", result);
                });
    }

    public int solution(int[] scoville, int K) {

        PriorityQueue<Integer> heap = Arrays.stream(scoville).boxed().collect(Collectors.toCollection(PriorityQueue::new));

        int count = 0;
        while (heap.size() > 0) {

            int first = heap.poll();


            if (heap.size() == 1) {
                return first < K ? -1 : count;
            }

            if (first < K) {
                int second = heap.poll();

                log.info("## first : {}, second : {}", first, second);

                int combined = first + (second * 2);
                heap.add(combined);
                count++;
            }
        }
        return count;
    }
//
//    public int solution(int[] scoville, int K) {
//
//        Arrays.sort(scoville);
//
//        int pos = 0;
//        int count = 0;
//        int lastCombined = 0;
//
//
//        while (pos < scoville.length) {
//            if (pos == scoville.length - 1) {
//                if (scoville[pos] < K)
//                    return -1;
//            }
//
//            if (scoville[pos] < K) {
//                int first = scoville[pos] > lastCombined ? lastCombined;
//                int second =
//
//                int combined = scoville[pos] + (scoville[pos] * 2);
//                count++;
//
//
//                if (scoville[pos + 2] > combined) {
//                    scoville[pos +1 ] =
//                }
//
//            }
//
//
//            pos++;
//        }
//
//
//        return count;
//    }

//


    public int noPerformance(int[] scoville, int K, int count) {

        if (scoville.length == 1) {
            return scoville[0] < K ? -1 : count;
        }

        Arrays.sort(scoville);

        if (scoville[0] < K) {
            int combined = scoville[0] + (scoville[1] * 2);
            count += 1;

            return noPerformance(IntStream.range(0, scoville.length - 1)
                    .map(i -> i == 0 ? combined : scoville[i + 1])
                    .toArray(), K, count);

        }
        return count;
    }

    public int recursive(int[] scoville, int K, int count) {
        if (scoville.length == 1) {
            return scoville[0] < K ? -1 : count;
        }
        if (scoville[0] < K) {
            int combined = scoville[0] + (scoville[1] * 2);
            count += 1;

            AtomicBoolean isInserted = new AtomicBoolean();


            return recursive(IntStream.range(0, scoville.length - 1)
                    .map(i -> {
                        boolean isLast = i == scoville.length - 2; // 갯수 하나 빠지고 (-1), 배열은 0부터 (-1)
                        int realPos = isLast ? i + 1 : isInserted.get() ? i + 1 : i + 2;
                        //log.info("# I : {}, realPos : {}, {}, {}", i, realPos, isInserted.get(), Arrays.toString(scoville));
                        boolean isBiggerThanCombined = scoville[realPos] > combined;
                        int cur = isInserted.get() ? scoville[realPos] : isBiggerThanCombined ? combined : isLast ? combined : scoville[realPos];
                        //isBiggerThanCombined ? isInserted.get() ? combined : scoville[i + 2] : isInserted.get() ? scoville[i + 2] : scoville[i + 2];
                        //log.info("## ins : {}, bigger : {}, cur : {}, com : {}, +1 : {}, +2 : {}", isInserted.get(), isBiggerThanCombined, cur, combined, scoville[i + 1], scoville[i + 2]);
                        if (isBiggerThanCombined) isInserted.set(true);
                        return cur;
                    }).toArray(), K, count);


        }

        return count;

    }


}
