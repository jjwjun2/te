package bitcamp.api.cart.repository;

import bitcamp.api.cart.domain.Cart;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


@Repository
public class CartRepositoryImpl extends QuerydslRepositorySupport
        implements CartCustomRepository {
    public CartRepositoryImpl() {
        super(Cart.class);
    }
}