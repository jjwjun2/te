package bitcamp.api.pay.repository;


import bitcamp.api.pay.domain.Payment;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryImpl extends QuerydslRepositorySupport 
									implements PaymentCustomRepository{
	// private final JPAQueryFactory qf;
	public PaymentRepositoryImpl() {
		super(Payment.class);
		// this.qf = qf;
	}
}
