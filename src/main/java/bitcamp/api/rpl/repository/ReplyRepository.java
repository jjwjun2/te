package bitcamp.api.rpl.repository;

import bitcamp.api.rpl.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;


interface IReplyRepository{

}

public interface ReplyRepository extends JpaRepository<Reply, Long>,
        IReplyRepository{

}