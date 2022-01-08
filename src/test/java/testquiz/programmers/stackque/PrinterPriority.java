package testquiz.programmers.stackque;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class PrinterPriority {

    @Test
    public void test() {

        int[][][] testCases = {
                {
                        {2, 1, 3, 2}, {2}
                },
                {
                        {1, 1, 9, 1, 1, 1}, {0}
                }
        };

        Arrays.stream(testCases).forEach(tc -> {
            int result = solution(tc[0], tc[1][0]);
            log.info("## result : {}", result);
        });
    }


    public int solution(int[] priorities, int location) {

        Queue<Document> documents = IntStream.range(0, priorities.length)
                .mapToObj(i -> new Document(i, priorities[i]))
                .collect(Collectors.toCollection(LinkedList::new));

        Printer printer = new Printer(documents);

        for (int i = 0; i < priorities.length; i++) {
            Document printed = printer.print();
            if (printed.location == location)
                return i + 1;
        }

        return 0;
    }

    private static class Printer {
        private final Queue<Document> pool;

        private Document print() {
            int maxPriority = pool.stream().mapToInt(doc -> doc.priority).max().getAsInt();

            while (!pool.isEmpty() && pool.peek().priority != maxPriority) {
                Document toTail = pool.poll();
                pool.add(toTail);
            }
            return pool.poll();
        }

        private Printer(Queue<Document> pool) {
            this.pool = pool;
        }
    }

    @ToString
    private static class Document {
        private final int location;
        private final int priority;

        private Document(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }
    }

}
