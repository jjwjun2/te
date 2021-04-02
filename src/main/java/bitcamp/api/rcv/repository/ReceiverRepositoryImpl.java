package bitcamp.api.rcv.repository;

import bitcamp.api.rcv.domain.Receiver;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


@Repository
public class ReceiverRepositoryImpl extends QuerydslRepositorySupport
        implements ReceiverCustomRepository{
    public ReceiverRepositoryImpl() {
        super(Receiver.class);
    }
}