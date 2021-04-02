package bitcamp.api.rcv.domain;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Lazy
public class ReceiverDto {
    private long rcvNo;
    private String usrEmail, rcvName, rcvPhone, rcvAddr;
}