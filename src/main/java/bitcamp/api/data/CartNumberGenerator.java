package bitcamp.api.data;


import java.util.Random;

public class CartNumberGenerator {
    Random random = new Random();
    public int cartnumberGenerator(){
        return random.nextInt(2)+1;
    }
}
