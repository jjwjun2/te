package bitcamp.api.pay.repository;

import bitcamp.api.pay.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


interface PaymentCustomRepository {

}

public interface PaymentRepository extends JpaRepository<Payment, Long>,
        PaymentCustomRepository {

}