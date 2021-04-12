package bitcamp.api.prd.controller;

import java.util.List;
import java.util.Optional;


import bitcamp.api.common.controller.AbstractController;
import bitcamp.api.prd.domain.Product;
import bitcamp.api.prd.domain.ProductDto;
import bitcamp.api.prd.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ProductController extends AbstractController<Product> {
	private final ProductServiceImpl service;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/save")
	public ResponseEntity<Long> save(@RequestBody Product product) {
		logger.info("저장한 제품명: " + product.getPrdName());
		return ResponseEntity.ok(service.save(product));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Long> delete(@RequestBody Product product) {
		logger.info("삭제한 제품명: " + product.getPrdName());
		return ResponseEntity.ok(service.delete(product));
	}
	
	@DeleteMapping("/delete/{prdNo}")
	public ResponseEntity<String> deleteById(@PathVariable long prdNo){
		logger.info("삭제한 제품 번호: " + prdNo);
		return ResponseEntity.ok(service.deleteById(prdNo));
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> count() {
		return ResponseEntity.ok(service.count());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> findAll() {
		logger.info("제품 전체 조회");
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/one/{prdNo}")
	public ResponseEntity<Product> getOne(@PathVariable long prdNo) {
		logger.info("조회한 제품 번호: " + prdNo);
		return ResponseEntity.ok(service.getOne(prdNo));
	}

	@GetMapping("/find/{prdNo}")
	public ResponseEntity<Optional<Product>> findById(@PathVariable long prdNo) {
		logger.info("조회한 제품 번호: " + prdNo);
		return ResponseEntity.ok(service.findById(prdNo));
	}

	@GetMapping("/exists/{prdNo}")
	public ResponseEntity<Boolean> existsById(@PathVariable long prdNo) {
		logger.info("조회한 제품 번호: " + prdNo);
		return ResponseEntity.ok(service.existsById(prdNo));
	}

	@GetMapping("/product-number/{prdNo}")
	public ResponseEntity<List<Product>> findByPrdNo(@PathVariable long prdNo) {
		logger.info("조회한 제품 번호: " + prdNo);
		return ResponseEntity.ok(service.findByPrdNo(prdNo));
	}
	
	@GetMapping("/search/{prdName}")
	public ResponseEntity<List<Product>> findByPrdNameContaining(@PathVariable String prdName) {
		logger.info("검색한 키워드: " + prdName);
		return ResponseEntity.ok(service.findByPrdNameContaining(prdName));
	}

	@GetMapping("/category/{ctgName}")
	public ResponseEntity<List<Product>> findByCtgName(@PathVariable String ctgName) {
		logger.info("조회한 카테고리: " + ctgName);
		return ResponseEntity.ok(service.findByCtgName(ctgName));
	}

	@PutMapping("/edit/{prdNo}")
	public ResponseEntity<Long> update(@PathVariable long prdNo, @RequestBody ProductDto p) {
		logger.info("수정한 제품명: " + p.getPrdName());
		return ResponseEntity.ok(service.update(p));
	}
}