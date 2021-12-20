package testquiz.programmers.hash;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
public class FailedPlayer {

    @Test
    public void test() {
        String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion = {"josipa", "filipa", "marina", "nikola"};

        String result = solution(participant, completion);

        log.info("## Result : {}", result);
    }

    public String solution(String[] participant, String[] completion) {

        Arrays.sort(participant);
        Arrays.sort(completion);

//        for (int i = 0; i < participant.length; i++) {
//
//            if (participant.length - 1 == i || !participant[i].equals(completion[i]))
//                return participant[i];
//        }
//        return "";
        return IntStream.range(0, completion.length)
                .filter(i -> !completion[i].equals(participant[i])).findFirst().stream()
                .mapToObj(i -> participant[i]).findFirst().orElseGet(() -> participant[participant.length - 1]);
    }
}
