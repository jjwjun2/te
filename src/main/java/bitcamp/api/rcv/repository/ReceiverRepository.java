package bitcamp.api.rcv.repository;

import bitcamp.api.rcv.domain.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;


interface ReceiverCustomRepository {

}

public interface ReceiverRepository extends JpaRepository<Receiver, Long>,
        ReceiverCustomRepository {

}