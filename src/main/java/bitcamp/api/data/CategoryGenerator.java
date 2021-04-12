package bitcamp.api.data;

import java.util.Random;
import java.util.stream.IntStream;

public class CategoryGenerator {
    public String categoryGenerator() {
        Random random = new Random();
        return IntStream.iterate(0, i -> (char) (random.nextInt('E' - 'A') + 'A'))
                .limit(2).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
