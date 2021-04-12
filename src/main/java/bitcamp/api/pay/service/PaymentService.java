package bitcamp.api.pay.service;


import bitcamp.api.pay.domain.Payment;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    long save(Payment t);
//    List<Payment> all();
//    Optional<Payment> one(long id);
    String edit(Payment t);
    String delete(long id);
}
