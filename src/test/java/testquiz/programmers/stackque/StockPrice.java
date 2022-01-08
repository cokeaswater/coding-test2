package testquiz.programmers.stackque;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@Slf4j
public class StockPrice {

    @Test
    public void test() {
        int[][] testCases = {

                {1, 2, 3, 2, 3}

        };

        Arrays.stream(testCases).forEach(testCase -> {
            log.info("{}", Arrays.toString(testCase));
            int[] result = solution(testCase);
            log.info("## result : {}", result);
        });

    }

    public int[] solution(int[] prices) {

        int[] maintenance = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            int main = 0;
            for (int j = i + 1; j < prices.length; j++) {
                main++;
                if (prices[j] < price) {
                    break;
                }
            }
            maintenance[i] = main;
        }
        return maintenance;
//
//        List<Tick> tickList =
//                IntStream.range(1, prices.length + 1)
//                        .mapToObj(i -> new Tick(i, prices[i - 1])).collect(Collectors.toList());
//
//
//        return IntStream.range(0, tickList.size())
//                .map(i -> {
//                    Tick tick = tickList.get(i);
//                    return tickList.stream()
//                            .filter(comp -> comp.seconds > tick.seconds && comp.price < tick.price)
//                            .mapToInt(find -> find.seconds - tick.seconds)
//                            .findFirst().orElseGet(() -> tickList.size() - (i + 1));
//
//                }).toArray();

    }

    @ToString
    private static class Tick {
        private final int seconds;
        private final int price;
        private int maintenance = 0;

        private Tick(int seconds, int price) {
            this.seconds = seconds;
            this.price = price;
        }
    }
}
