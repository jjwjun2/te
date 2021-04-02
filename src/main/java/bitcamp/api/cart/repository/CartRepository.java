package bitcamp.api.cart.repository;

import bitcamp.api.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

interface CartCustomRepository {}

public interface CartRepository extends JpaRepository<Cart, Long>, CartCustomRepository {

}