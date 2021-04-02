package bitcamp.api.rpl.service;

import java.util.List;
import java.util.Optional;

import bitcamp.api.common.service.AbstractService;
import bitcamp.api.rpl.domain.Reply;
import bitcamp.api.rpl.repository.ReplyRepository;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl extends AbstractService<Reply>
        implements ReplyService{
    private final ReplyRepository repo;

    @Override public long save(Reply t) {return (repo.save(t)!=null) ? 1 : 0 ;}
    @Override public long count() {return (long) repo.count();}
    @Override public Reply getOne(long id) {return repo.getOne(id);}
    @Override public Optional<Reply> findById(long id) {return repo.findById(id);}
    @Override public boolean existsById(long id) {return repo.existsById(id);}
    @Override public List<Reply> findAll() {return repo.findAll();}
    @Override public long delete(Reply t) {
        repo.delete(t);
        return (getOne(t.getRplNo())==null) ? 1 : 0;
    }
}