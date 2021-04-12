package bitcamp.api.brd.domain;

import javax.persistence.*;

import bitcamp.api.pay.domain.Payment;
import bitcamp.api.prd.domain.Product;
import bitcamp.api.rpl.domain.Reply;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;



@Entity  @Getter
public class Board {
	@Id @Column(name="brd_no") @GeneratedValue(strategy = GenerationType.IDENTITY) private long brdNo;
	@Column(name="brd_title") private String  brdTitle;
	@Column(name="brd_content") private String brdContent;
	@Column(name="brd_wrt_date") private String brdWrtDate;
	@Column(name="brd_rank") private String brdRank;
	@Column(name="brd_img") private String brdImg;
	@Column(name="brd_kind") private long brdKind;
	@Column(name="brd_count") private long brdCount;
	@Column(name="brd_like") private String brdLike;
	@Column(name="brd_pwd") private String brdPwd;
	@Column(name="usr_name") private String usrName;
	@Column(name="usr_no") private String usrNo;
	private long productNo;
	@OneToMany(mappedBy="board")
	private List<Reply> replies = new ArrayList<>();
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="usr_no")
//	private UserVo user;
	@ManyToOne
	@JoinColumn(name="pay_no")
	private Payment payment;
	@ManyToOne
	@JoinColumn(name="prd_no")
	private Product product;
	
	public void setBrdWrtDate(String brdWrtDate) {
		this.brdWrtDate = brdWrtDate;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public void setBrdCount(long brdCount) {
		this.brdCount = brdCount;
	}
	public void setBrdTitle(String brdTitle) {
		this.brdTitle = brdTitle;
	}
	public void setBrdContent(String brdContent) {
		this.brdContent = brdContent;
	}
	public void setUsrNo(String usrNo) {
		this.usrNo = usrNo;
	}
	@Override
	public String toString() {
		return "Board [brdNo=" + brdNo + ", brdTitle=" + brdTitle + ", brdContent=" + brdContent + ", brdWrtDate="
				+ brdWrtDate + ", brdRank=" + brdRank + ", brdImg=" + brdImg + ", brdKind=" + brdKind + ", brdCount="
				+ brdCount + ", brdLike=" + brdLike + ", brdPwd=" + brdPwd + ", usrNikcname=" + usrName
				+ ", replyList="  + ", payment=" + payment + ", product=" + product + "]";
	}

	
	
}
