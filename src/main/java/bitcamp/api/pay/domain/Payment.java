package bitcamp.api.pay.domain;


import bitcamp.api.brd.domain.Board;
import bitcamp.api.cart.domain.Cart;
import bitcamp.api.prd.domain.Product;
import bitcamp.api.rcv.domain.Receiver;
import bitcamp.api.usr.domain.UserVo;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_no")
    private long payNo;
    @Column(name = "pay_price")
    private String payPrice;
    @Column(name = "pay_amount")
    private long payAmount;
    @Column(name = "dvr_fee")
    private String dvrFee;
    @Column(name = "pay_date")
    private String payDate;
    @Column(name = "pay_state")
    private String payState;

    @ManyToOne
    @JoinColumn(name = "usr_no")
    private UserVo userVo;

    @ManyToOne
    @JoinColumn(name = "prd_no")
    private Product product;

    @OneToMany(mappedBy = "payment")
    private List<Receiver> receiverList = new ArrayList<>();

    @OneToMany(mappedBy = "payment")
    private List<Board> boardList = new ArrayList<>();

    @OneToOne(mappedBy = "payment")
    private Cart cart;

}