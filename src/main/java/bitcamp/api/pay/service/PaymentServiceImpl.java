package bitcamp.api.pay.service;

import java.util.List;
import java.util.Optional;

import bitcamp.api.common.service.AbstractService;
import bitcamp.api.pay.domain.Payment;
import bitcamp.api.pay.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl extends AbstractService<Payment>
        implements PaymentService {
    private final PaymentRepository repo;

    @Override
    public long save(Payment t) {
        return (repo.save(t) != null) ? 1 : 0;
    }

    @Override
    public String delete(long id) {
        repo.deleteById(id);
        return "SUCCESS";
    }

    @Override
    public String edit(Payment t) {
        Payment pay = repo.save(t);
        return (pay != null) ? "SUCCESS" : "FAILURE";
    }

    @Override
    public long count() {
        return (long) repo.count();
    }

    @Override
    public Payment getOne(long id) {
        return repo.getOne(id);
    }

    @Override
    public Optional<Payment> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public boolean existsById(long id) {
        return repo.existsById(id);
    }

    @Override
    public List<Payment> findAll() {
        return repo.findAll();
    }

    @Override
    public long delete(Payment t) {
        repo.delete(t);
        return (getOne(t.getPayNo()) == null) ? 1 : 0;
    }
}
