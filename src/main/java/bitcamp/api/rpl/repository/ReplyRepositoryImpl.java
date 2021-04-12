package bitcamp.api.rpl.repository;
import bitcamp.api.rpl.domain.Reply;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ReplyRepositoryImpl extends QuerydslRepositorySupport
        implements IReplyRepository{
    public ReplyRepositoryImpl() {
        super(Reply.class);
    }



//    public List<Reply> test(){
//        return
//
//    }

}