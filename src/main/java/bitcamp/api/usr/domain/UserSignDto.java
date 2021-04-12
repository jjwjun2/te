package bitcamp.api.usr.domain;

import bitcamp.api.brd.domain.Board;
import bitcamp.api.pay.domain.Payment;
import bitcamp.api.rcv.domain.Receiver;
import lombok.Data;

import java.util.List;

@Data
public class UserSignDto {
    private String token;
    private UserVo user;
    private List<Payment> payments;
    private List<Receiver> receivers;
    private List<Board> boards;
}