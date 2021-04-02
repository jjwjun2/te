package bitcamp.api.prd.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bitcamp.api.common.service.AbstractService;
import bitcamp.api.prd.domain.Product;
import bitcamp.api.prd.domain.ProductDto;
import bitcamp.api.prd.repository.ProductReporitory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends AbstractService<Product> implements ProductService {

    private final ProductReporitory repo;

//    @Override
//    public long save(Product t) {
//        return (repo.save(t) != null) ? 1 : 0;
//    }

    @Override
    public long count() {
        return (long) repo.count();
    }

    @Override
    public Product getOne(long id) {
        return repo.getOne(id);
    }

    @Override
    public Optional<Product> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public boolean existsById(long id) {
        return false;
    }

    @Override
    public List<Product> findAll() {
        return repo.findAll().stream().sorted(Comparator.comparing(Product::getPrdNo).reversed()).collect(Collectors.toList());
    }

    @Override
    public long save(Product product) {
        return (repo.save(product) != null) ? 1 : 0;
    }

    @Override
    public long delete(Product t) {
        repo.delete(t);
        return (getOne(t.getPrdNo()) == null) ? 1 : 0;
    }

    @Override
    public String deleteById(long id) {
        repo.deleteById(id);
        return "SUCCESS";
    }

    @Override
    public List<Product> findByPrdNo(long prdNo) {
        return repo.findByPrdNo(prdNo);
    }

    public List<Product> findByCtgName(String ctgName) {
        return repo.findByCtgName(ctgName);
    }

    public long update(ProductDto p) {
        Product prd = findById(p.getPrdNo()).get();
        prd.setPrdName(p.getPrdName());
        prd.setCtgName(p.getCtgName());
        prd.setPrdPrice(p.getPrdPrice());
        prd.setPrdImg(p.getPrdImg());
        prd.setPrdInv(p.getPrdInv());
        return repo.save(prd) != null ? 1 : 0;
    }
}