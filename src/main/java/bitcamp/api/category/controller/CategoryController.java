package bitcamp.api.category.controller;

import bitcamp.api.category.domain.Category;
import bitcamp.api.category.service.CategoryServiceImpl;
import bitcamp.api.common.controller.AbstractController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController extends AbstractController<Category> {
    private final CategoryServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<Long> save(Category t) {
        return ResponseEntity.ok(service.save(t));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(Category t) {
        return ResponseEntity.ok(service.delete(t));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.count());
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Category> getOne(@PathVariable long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Category>> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<Boolean> existsById(long id) {
        return null;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


}