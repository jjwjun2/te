package bitcamp.api.rcv.controller;


import bitcamp.api.common.controller.AbstractController;
import bitcamp.api.rcv.domain.Receiver;
import bitcamp.api.rcv.service.ReceiverServiceImpl;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/receiver")
public class ReceiverController extends AbstractController<Receiver> {
    final ReceiverServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody Receiver t) {
        return ResponseEntity.ok(service.save(t));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody Receiver t) {
        return ResponseEntity.ok(service.delete(t));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.count());
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Receiver> getOne(@PathVariable long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Receiver>> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable long id) {
        return ResponseEntity.ok(service.existsById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Receiver>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}