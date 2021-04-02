package bitcamp.api.cart.service;

import bitcamp.api.cart.domain.Cart;
import bitcamp.api.cart.repository.CartRepository;
import bitcamp.api.common.service.AbstractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl extends AbstractService<Cart> implements CartService {
    private final CartRepository repo;

    @Override
    public long save(Cart t) {
        return (repo.save(t) != null) ? 1 : 0;
    }

    @Override
    public long count() {
        return (long) repo.count();
    }

    @Override
    public Cart getOne(long id) {
        return repo.getOne(id);
    }

    @Override
    public Optional<Cart> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public boolean existsById(long id) {
        return false;
    }

    @Override
    public List<Cart> findAll() {
        return repo.findAll();
    }

    @Override
    public long delete(Cart t) {
        repo.delete(t);
        return (getOne(t.getCartNo()) == null) ? 1 : 0;
    }
}