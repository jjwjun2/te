package bitcamp.api.data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DateGenerator {
    String time = "2021-01-01 13시 01분";
    Random random =new Random();
    List<String> odd = Arrays.asList("01", "03", "05", "07", "08", "10", "12");
    List<String> even = Arrays.asList("04", "06", "07", "09", "11");

    public String test(){
        String year = "2021";
        return year;
    }

    public String hour(){
        int hour = random.nextInt(24)+1;
        return hour<10 ? "0"+hour : String.valueOf(hour);
    }


    public String minute(){
        int hour = random.nextInt(60)+1;
        return hour<10 ? "0"+hour : String.valueOf(hour);
    }

    public String monthGenerator(){
        int number = random.nextInt(12)+1;
        String month = number <10 ? "0"+number : String.valueOf(number);
        String date = null;
        if (month.equals("02")){
            date = februry();
        }

        if (odd.contains(month)){
            date  = oddgene();
        }

        if (even.contains(month)){
            date  = evenGene();
        }
//        System.out.println("month= "+month);
//        System.out.println("date= "+date);
        return test()+"-"+month+"-"+date+" "+hour()+"시"+" "+minute()+"분";
    }


    public String februry(){
        int number = random.nextInt(28)+1;
        return number < 10 ? "0" +number : String.valueOf(number);
    }

    public String oddgene(){
        int number = random.nextInt(30)+1;
        return number < 10 ? "0" +number : String.valueOf(number);
    }

    public String evenGene(){
        int number = random.nextInt(31)+1;
        return number < 10 ? "0" +number : String.valueOf(number);
    }







    public static void main(String args []){
        DateGenerator dateGenerator = new DateGenerator();

        for(int i=0; i< 200; i++){
            System.out.println(dateGenerator.monthGenerator());
        }

    }
}

