package bitcamp.api.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

enum Email {
    EMAIL_LIST("Email", Arrays.asList("@naver.com", "@daum.net", "@google.com"));
    private final String email;
    private final List<String> emailKind;

    Email(String email, List<String> emailKind) {
        this.email = email;
        this.emailKind = emailKind;
    }

    public List<String> getEmailKind() {
        return emailKind;
    }
}


public class EmailGenerator {

    public String format() {
        List<String> lst = Email.EMAIL_LIST.getEmailKind();
        Collections.shuffle(lst);
        return lst.get(0);
    }


    public String baseEmailId() {
        String text = "";
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        int a = random.nextInt(5) + 6;


        for (int i = 0; i < a; i++) {
            text += alpha.charAt((int) (Math.random() * alpha.length()));
        }
        return text + format();
    }

}
