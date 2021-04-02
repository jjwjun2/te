package bitcamp.api.brd.controller;

import java.util.List;
import java.util.Optional;

import bitcamp.api.brd.domain.Board;
import bitcamp.api.brd.service.BoardServiceImpl;
import bitcamp.api.common.controller.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/board")
public class BoardController extends AbstractController<Board> {
    final BoardServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody Board t) {
        return ResponseEntity.ok(service.save(t));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody Board t) {
        return ResponseEntity.ok(service.delete(t));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.count());
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Board> getOne(@PathVariable long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Board>> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable long id) {
        return ResponseEntity.ok(service.existsById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Board>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/option/{title}")
    public ResponseEntity<List<Board>> findByTitle(@PathVariable String title) {
        return ResponseEntity.ok(service.findByTitle(title));
    }


}
