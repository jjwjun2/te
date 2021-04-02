package bitcamp.api.brd.domain;

import javax.persistence.*;

import bitcamp.api.pay.domain.Payment;
import bitcamp.api.prd.domain.Product;
import bitcamp.api.rpl.domain.Reply;
import bitcamp.api.usr.domain.UserVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Table(name="boards")
public class Board {
    @Id
    @Column(name = "brd_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long brdNo;

    @Column(name = "brd_title")
    private String brdTitle;

    @Column(name = "brd_content")
    private String brdContent;

    @Column(name = "brd_written_date")
    private String brdWrtDate;

    @Column(name = "brd_rank")
    private String brdRank;

    @Column(name = "brd_img")
    private String brdImg;

    @Column(name = "brd_kind")
    private long brdKind;

    @Column(name = "brd_count")
    private long brdCount;

    @Column(name = "brd_like")
    private String brdLike;

    @Column(name = "brd_pwd")
    private String brdPwd;

    @Column(name = "usr_nickname")
    private String usrNikcname;

    @OneToMany(mappedBy = "board")
    private List<Reply> replyList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usr_no")
    private UserVo userVo;

    @ManyToOne
    @JoinColumn(name = "pay_no")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "prd_no")
    private Product product;

}