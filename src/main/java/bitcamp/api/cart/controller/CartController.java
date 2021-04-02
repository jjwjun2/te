package bitcamp.api.cart.controller;

import bitcamp.api.cart.domain.Cart;
import bitcamp.api.cart.service.CartServiceImpl;
import bitcamp.api.common.controller.AbstractController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController extends AbstractController<Cart> {
    private final CartServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<Long> save(Cart t) {
        return ResponseEntity.ok(service.save(t));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(Cart t) {
        return ResponseEntity.ok(service.delete(t));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.count());
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Cart> getOne(@PathVariable long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Cart>> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<Boolean> existsById(long id) {
        return null;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Cart>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}