package bitcamp.api.rpl.controller;

import java.util.List;
import java.util.Optional;

import bitcamp.api.common.controller.AbstractController;
import bitcamp.api.rpl.domain.Reply;
import bitcamp.api.rpl.service.ReplyServiceImpl;
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
@RequestMapping("/reply")
public class ReplyController extends AbstractController<Reply> {
    final ReplyServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody Reply t) {
        return ResponseEntity.ok(service.save(t));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody Reply t) {
        return ResponseEntity.ok(service.delete(t));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.count());
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Reply> getOne(@PathVariable long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Reply>> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable long id) {
        return ResponseEntity.ok(service.existsById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reply>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}