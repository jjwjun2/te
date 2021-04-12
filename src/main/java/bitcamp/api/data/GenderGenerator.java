package bitcamp.api.data;


import java.util.Random;


enum Gender {
    MAN(1, "남자"), WOMAN(2, "여자");
    private final int genderCode;
    private final String gender;

    Gender(int genderCode, String gender) {
        this.genderCode = genderCode;
        this.gender = gender;
    }

    public int getGenderCode() {
        return genderCode;
    }

    public String getGender() {
        return gender;
    }
}


public class GenderGenerator{
    private Random random = new Random();

    public String randomGenderGenerator() {
        return ((int) random.nextInt(2) + 1 == 1) ?
                Gender.MAN.getGender() : Gender.WOMAN.getGender();
    }

}