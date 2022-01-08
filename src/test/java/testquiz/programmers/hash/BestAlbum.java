package testquiz.programmers.hash;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class BestAlbum {

    @Test
    public void test() {
        String[][] genres = {
                {"classic", "pop", "classic", "classic", "pop"}
        };

        int[][] plays = {
                {500, 600, 150, 800, 2500}
        };

        IntStream.range(0, genres.length).forEach(i -> {
            log.info("## Sample : {}, {}", Arrays.toString(genres[i]), plays[i]);
            int[] result = solution(genres[i], plays[i]);
            log.info("## Result : {}", Arrays.toString(result));
        });


    }


    public int[] solution(String[] genres, int[] plays) {

        return IntStream.range(0, genres.length)
                .mapToObj(i -> new Song(i, plays[i], genres[i]))
                .collect(Collectors.groupingBy(song -> song.genre))
                .entrySet().stream().sorted((o1, o2) ->
                        o2.getValue().stream().flatMapToInt(s -> IntStream.of(s.plays)).sum() - o1.getValue().stream().flatMapToInt(s -> IntStream.of(s.plays)).sum())
                .map(entry -> entry.getValue().stream().sorted().limit(2)) //.mapToInt(value -> value.no).toArray())
                .flatMapToInt(songs -> songs.flatMapToInt(song -> IntStream.of(song.no))).toArray();


    }


    @ToString
    private static class Song implements Comparable<Song> {
        final int no;
        final int plays;
        final String genre;

        private Song(int no, int plays, String genre) {
            this.no = no;
            this.plays = plays;
            this.genre = genre;
        }


        @Override
        public int compareTo(@NotNull Song o) {
            return o.plays - this.plays;
        }
    }


}
