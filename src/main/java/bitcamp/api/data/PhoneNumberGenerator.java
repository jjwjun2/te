package bitcamp.api.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PhoneNumberGenerator {
    List<String> phoneList = new ArrayList<>();
    static Random random = new Random();

    public String firstNumber(){
        return IntStream.iterate(0, i -> (char) (random.nextInt('9' - '1') + '1'))
                .limit(1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String otherNumber(){
        return IntStream.iterate(0, i -> (char) (random.nextInt('9' - '0') + '0'))
                .limit(7)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }


    public String phoneNumberGenerator() {
        String number = "010"+firstNumber()+otherNumber();
        if (duplicateCheck(number)){
            return "없음";
        }
        phoneList.add(number);
        return number;
    }
    public boolean duplicateCheck(String number){
        return phoneList.contains(number);
    }
}
