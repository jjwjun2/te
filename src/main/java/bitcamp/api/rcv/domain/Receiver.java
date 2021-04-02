package bitcamp.api.rcv.domain;

import javax.persistence.*;


import bitcamp.api.pay.domain.Payment;
import lombok.Getter;

@Entity
@Getter @Table(name="receivers")
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rcv_no")
    private long rcvNo;
    @Column(name = "rcv_name")
    private String rcvName;
    @Column(name = "rcv_phone")
    private String rcvPhone;
    @Column(name = "rcv_addr")
    private String rcvAddr;

    @ManyToOne
    @JoinColumn(name = "pay_no")
    private Payment payment;
}
