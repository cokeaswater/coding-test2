package testquiz.programmers.stackque;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class FunctionDevelop {

    @Test
    public void test() {
        int[][][] testCases = {
                {
                        {93, 30, 55}, {1, 30, 5},

                },
                {
                        {95, 90, 99, 99, 80, 99}, {1, 1, 1, 1, 1, 1}
                },
                {
                        {90, 90, 90}, {1, 1, 1}
                }
        };

        log.info("## {}", testCases.length);

        Arrays.stream(testCases).forEach(testCase -> {
            log.info("{}", Arrays.deepToString(testCase));
            int[] result = solution(testCase[0], testCase[1]);
            log.info("## result : {}", result);
        });

    }

    public int[] solution(int[] progresses, int[] speeds) {

        int[] deploy = new int[progresses.length];


        for (int i = 0; i < progresses.length; i++) {
            int loop = 0;
            for (int j = progresses[i]; j < 100; j += speeds[i]) {
                loop++;
            }
            deploy[i] = loop;
        }

        int max = deploy[0];
        int dep = 1;

        List<Integer> deployNumbers = new ArrayList<>();
        for (int i = 1; i < deploy.length; i++) {

            if (max >= deploy[i])
                dep++;
            else {
                deployNumbers.add(dep);
                max = deploy[i];
                dep = 1;

            }
        }
        deployNumbers.add(dep);


        return deployNumbers.stream().mapToInt(no -> no).toArray();
    }
}
