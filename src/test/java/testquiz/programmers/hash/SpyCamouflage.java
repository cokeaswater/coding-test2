package testquiz.programmers.hash;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class SpyCamouflage {


    @Test
    public void test() {

        String[][][] cases = {
                {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}},
                {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}}
        };

        Arrays.stream(cases).forEach(tc -> {

            int result = solution(tc);
            log.info("## Result : {} ", result);
        });
    }


    public int solution(String[][] clothes) {

        List<Integer> cases = Arrays.stream(clothes)
                .collect(Collectors.groupingBy(set -> set[1]))
                .values().stream().flatMapToInt(list -> IntStream.of(list.size() + 1)).boxed().collect(Collectors.toList());

        return cases.stream().reduce(1, (a, b) -> a * b) - 1;
    }

}
