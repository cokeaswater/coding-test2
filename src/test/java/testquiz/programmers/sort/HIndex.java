package testquiz.programmers.sort;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@Slf4j
public class HIndex {

    @Test
    public void test() {

        int[][] testCases = {
                {3, 0, 6, 1, 5},
                {5, 4, 2, 3, 1, 8},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {1},
                {3, 3},
                {22, 42}
        };
        Arrays.stream(testCases).forEach(tc -> {
            int result = solution(tc);
            log.info("## result : {}", result);
        });
    }


    public int solution(int[] citations) {


        Arrays.sort(citations);
        log.info("## Sorted : {}", Arrays.toString(citations));

//        if (Arrays.stream(citations).max().orElseGet(() -> 0))
//                return citations[0];

        int centerPos, refers;
        centerPos = citations.length % 2 == 0 ? citations.length / 2 - 1 : citations.length / 2;
        refers = citations.length / 2;

        int paperRefers = citations[centerPos];

        if (paperRefers > refers) {
            for (int i = centerPos; i > 0; i--) {
                if (citations[i] > refers)
                    refers++;
                if (i + 1 > refers)
                    break;
            }
        } else {
            for (int i = centerPos; i < citations.length; i++) {
                if (citations[i] <= refers && centerPos + 1 > refers) {
                    refers--;
                }

                if (i + 1 >= refers)
                    break;
            }
        }
        return refers;
    }


}
