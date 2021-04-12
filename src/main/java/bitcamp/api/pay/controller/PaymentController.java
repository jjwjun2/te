package bitcamp.api.pay.controller;

import java.util.List;
import java.util.Optional;


import bitcamp.api.common.controller.AbstractController;
import bitcamp.api.pay.domain.Payment;
import bitcamp.api.pay.service.PaymentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/payment")
public class PaymentController extends AbstractController<Payment> {
	final PaymentServiceImpl service;
	
	@PostMapping("/save")
	public ResponseEntity<Long> save(@RequestBody Payment t) {
		return ResponseEntity.ok(service.save(t));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Long> delete(@RequestBody Payment t) {
		return ResponseEntity.ok(service.delete(t));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		return ResponseEntity.ok(service.delete(id));
	}
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> edit(@PathVariable long id, @RequestBody Payment t){
		System.out.println("edit :"+t.toString());
		return ResponseEntity.ok(service.edit(t));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> count() {
		return ResponseEntity.ok(service.count());
	}

	@GetMapping("/one/{id}")
	public ResponseEntity<Payment> getOne(@PathVariable long id) {
		return ResponseEntity.ok(service.getOne(id));
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Optional<Payment>> findById(@PathVariable long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("/exists/{id}")
	public ResponseEntity<Boolean> existsById(@PathVariable long id) {
		return ResponseEntity.ok(service.existsById(id));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Payment>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
}
