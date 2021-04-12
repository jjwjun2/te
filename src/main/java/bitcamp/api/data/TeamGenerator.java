package bitcamp.api.data;

import java.util.Random;

public class TeamGenerator {
    public String teamGenerator() {
        Random random = new Random();
        return (random.nextInt(2) + 1) == 1 ? "M" : "F";
    }

}
