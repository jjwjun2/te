package bitcamp.api.rpl.repository;
import bitcamp.api.rpl.domain.Reply;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


@Repository
public class ReplyRepositoryImpl extends QuerydslRepositorySupport
        implements IReplyRepository{
    public ReplyRepositoryImpl() {
        super(Reply.class);
    }

}