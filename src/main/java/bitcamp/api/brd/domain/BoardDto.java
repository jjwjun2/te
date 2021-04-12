package bitcamp.api.brd.domain;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data @Component @Lazy 
public class BoardDto {
	 private long brdNo;
	 private String  brdTitle;
	 private String brdContent;
	 private String brdWrtDate;
	 private String brdRank;
	 private String brdImg;
	 private long brdKind;
	 private long count;
	 private String brdLike;
	 private String brdPwd;
	 private String usrName;
	 private String usrNo;
	 
		
}
