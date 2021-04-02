package bitcamp.api.cart.domain;

import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Data
@Lazy
public class CartDto {
    private long cartNo, carAmount;

    private long usrNo, prdNo;
}