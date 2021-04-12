package bitcamp.api.pay.domain;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


import lombok.Data;
@Component @Data @Lazy
public class PaymentDto {
	 private long payNo;
	 private String payPrice;
	 private String payInfo;
	 private String payDate;
	 private String payState;
	 private String rcvName;
	 private String rcvPhone;
	 private String rcvAddr;
}
