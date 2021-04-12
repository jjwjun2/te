package bitcamp.api.rpl.domain;

import javax.persistence.*;


import bitcamp.api.brd.domain.Board;
import bitcamp.api.usr.domain.UserVo;
import lombok.Getter;

@Entity @Getter @Table(name = "replies")
public class Reply {
    @Id
    @Column(name = "rpl_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rplNo;

    @Column(name = "rpl_content")
    private String rplContent;


    @ManyToOne
    @JoinColumn(name="brd_no")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "usr_no")
    private UserVo userVo;
    Reply(){};

    public long getRplNo() {
        return rplNo;
    }

    public void setRplNo(long rplNo) {
        this.rplNo = rplNo;
    }

    public String getRplContent() {
        return rplContent;
    }

    public void setRplContent(String rplContent) {
        this.rplContent = rplContent;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }
}