package testquiz.programmers.hash;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
public class PhoneNumberList {

    @Test
    public void test() {

        String[][] cases = {
                {"119", "97674223", "1195524421"},
                {"123", "456", "789"},
                {"12", "123", "1235", "567", "88"}
        };

        IntStream.range(0, cases.length)
                .forEach(i -> {
                    boolean result = solution(cases[i]);
                    log.info("## Result TestCase {} : {}", i, result);
                });
    }

    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

//        for (int i = 1; i < phone_book.length; i++) {
//            if (phone_book[i].startsWith(phone_book[i - 1]))
//                return false;
//        }

        return IntStream.range(1, phone_book.length)
                .filter(i -> phone_book[i].startsWith(phone_book[i - 1]))
                .findFirst().isEmpty();
    }
}
