package bitcamp.api.cart.domain;

import bitcamp.api.pay.domain.Payment;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter @Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_no")
    private long cartNo;

    @Column(name = "cart_amount")
    private long cartAmount;

    @OneToOne
    @JoinColumn(name = "pay_no")
    private Payment payment;
}
