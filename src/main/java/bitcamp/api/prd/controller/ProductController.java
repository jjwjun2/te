package bitcamp.api.prd.controller;


import bitcamp.api.common.controller.AbstractController;
import bitcamp.api.prd.domain.Product;
import bitcamp.api.prd.domain.ProductDto;
import bitcamp.api.prd.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ProductController extends AbstractController<Product> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody Product t) {
        logger.info("저장한 제품: " + t.toString());
        return ResponseEntity.ok(service.save(t));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody Product prd) {
        logger.info("삭제" + prd.toString());
        return ResponseEntity.ok(service.delete(prd));
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

    @GetMapping("/one/{id}")
    public ResponseEntity<Product> getOne(@PathVariable long id) {
        logger.info("조회한 제품 번호: " + id);
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable long id) {
        logger.info("조회한 제품 번호: " + id);
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<Boolean> existsById(long id) {
        return null;
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existById(@PathVariable long id) {
        logger.info("조회한 제품 번호: " + id);
        return ResponseEntity.ok(service.existsById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/product-number/{prdNo}")
    public ResponseEntity<List<Product>> findByPrdNo(@PathVariable long prdNo) {
        return ResponseEntity.ok(service.findByPrdNo(prdNo));
    }

    @GetMapping("/category/{ctgName}")
    public ResponseEntity<List<Product>> findByCtgName(@PathVariable String ctgName) {
        return ResponseEntity.ok(service.findByCtgName(ctgName));
    }

    @PutMapping("/edit/{prdNo}")
    public ResponseEntity<Long> update(@PathVariable long prdNo,
                                       @RequestBody ProductDto p) {
        logger.info("수정한 제품: " + prdNo);
        return ResponseEntity.ok(service.update(p));
    }

}