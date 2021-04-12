package bitcamp.api.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

enum Age {
    TEENAGER("10s"), _20S("20s"), _30S("30s"), _40S("40s"),
    _50S("50s"), _60S("60s"), OVER_70S("over70s");

    private final String age;

    Age(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }
}


public class AgeGenerator {
    public String randomAgesGenerator() {
        List<Age> ageList = Arrays.asList(Age.values().clone());
        Collections.shuffle(ageList);
        return ageList.get(0).getAge();
    }


    public int randomAgeGenerator() {
        Random random = new Random();
        return random.nextInt(65) + 16;
    }
}