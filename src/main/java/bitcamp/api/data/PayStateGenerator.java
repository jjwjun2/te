package bitcamp.api.data;

import java.util.Random;

public class PayStateGenerator {
    Random random = new Random();

    public String payStateGenerator() {
        return (random.nextInt(2) + 1 == 1) ? "Complete" : "Release";
    }
}
