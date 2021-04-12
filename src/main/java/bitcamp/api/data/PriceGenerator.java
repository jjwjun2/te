package bitcamp.api.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PriceGenerator {
    static Random random = new Random();

    public int amountGenerator() {
        return random.nextInt(50) + 1;
    }

    public String priceGenerator() {
        return String.valueOf(amountGenerator() * 1000);
    }


}
