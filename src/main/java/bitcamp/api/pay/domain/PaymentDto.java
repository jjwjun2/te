package bitcamp.api.pay.domain;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


import lombok.Data;

@Component
@Data
@Lazy
public class PaymentDto {
    private long payNo;
    private String payPrice;
    private long payAmount;
    private String dvrFee;
    private String payDate;
    private String payState;
}