package testquiz.programmers.stackque;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class BridgeTruck {

    @Test
    public void test() {
        int[][][] testCases = {
                {
                        {2}, {10}, {7, 4, 5, 6}

                },
                {
                        {100}, {100}, {10}
                },
                {
                        {100}, {100}, {10, 10, 10, 10, 10, 10, 10, 10, 10, 10}
                }
        };

        Arrays.stream(testCases).forEach(tc -> {
            int result = solution(tc[0][0], tc[1][0], tc[2]);
            log.info("## result : {}", result);
        });

    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> waiting = Arrays.stream(truck_weights).mapToObj(Truck::new).collect(Collectors.toCollection(LinkedList::new));

        Bridge bridge = new Bridge(bridge_length, weight, waiting);


        return bridge.cross();
    }

    @ToString
    private static class Bridge {
        private final int length;
        private final int limit;
        private final int total;
        private final Queue<Truck> waiting;
        private final Queue<Truck> crossing = new LinkedList<>();
        private final List<Truck> crossDone = new ArrayList<>();

        private Bridge(int length, int limit, Queue<Truck> waiting) {
            this.length = length;
            this.limit = limit;
            this.waiting = waiting;
            this.total = waiting.size();
        }

        public int cross() {
            int count = 0;
            while (total != crossDone.size()) {
                count++;
                crossing.forEach(Truck::forward);

                List<Truck> done = crossing.stream().filter(truck -> truck.position > length).collect(Collectors.toList());

                crossDone.addAll(done);
                IntStream.range(0, done.size()).forEach(i -> crossing.poll());

                int crossWeight = crossing.stream().mapToInt(truck -> truck.weight).sum();

                if (waiting.size() > 0 && waiting.peek().weight <= limit - crossWeight) {
                    Truck add = waiting.poll();
                    add.forward();
                    crossing.add(add);
                }

            }
            return count;
        }
    }


    @ToString
    private static class Truck {
        private final int weight;
        private int position = 0;

        private Truck(int weight) {
            this.weight = weight;
        }

        public void forward() {
            position++;
        }
    }

}
