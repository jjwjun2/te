package bitcamp.api.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeliveryFee {

    public String deliveryFee() {
        List<String> feeList = Arrays.asList("0", "3000");
        Collections.shuffle(feeList);
        return feeList.get(0);
    }
}
