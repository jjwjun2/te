package bitcamp.api.pay.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import bitcamp.api.brd.domain.Board;
import bitcamp.api.cart.domain.Cart;
import bitcamp.api.prd.domain.Product;
import bitcamp.api.usr.domain.UserVo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;


@Entity @Getter
public class Payment {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name="pay_no") private long payNo;
   @Column(name="pay_price") private String payPrice;
   @Column(name="pay_info") private String payInfo;
   @Column(name="pay_date") private String payDate;
   @Column(name="pay_state") private String payState;
   @Column(name="rcv_name") private String rcvName;
   @Column(name="rcv_phone") private String rcvPhone;
   @Column(name="rcv_addr") private String rcvAddr;
   
   @ManyToOne
   @JoinColumn(name="usr_no")
   private UserVo userVo;
   
   @ManyToOne
   @JoinColumn(name="prd_no")
   private Product product;

   @OneToMany(mappedBy="payment")
   private List<Board> boards = new ArrayList<>();
   
   @OneToOne(mappedBy="payment")
   private Cart cart;

}