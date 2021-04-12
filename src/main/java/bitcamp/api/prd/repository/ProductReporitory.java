package bitcamp.api.prd.repository;

import java.util.List;


import bitcamp.api.prd.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface IProductRepository {
	public List<Product> findByPrdNo(long prdNo);
	public List<Product> findByPrdNameContaining(String prdName);
	public List<Product> findByCtgName(String ctgName);
}

public interface ProductReporitory extends JpaRepository<Product, Long>, IProductRepository {
	@Query(value = "update Products prd set prd.prd_name = :prdName, "+ "prd.ctg_name = :ctgName " + "prd.prd_price = :prdPrice, " + "prd.prd_inv = :prdInv " + "prd.prd_img = :prdImg"
					+ " where prd.prd_no = :prdNo", nativeQuery = true)
	public long update(@Param("prdNo") long prdNo, @Param("ctgName") String ctgName, @Param("prdPrice") String prdPrice,
					   @Param("prdInv") String prdInv, @Param("prdImg") String prdImg, @Param("prdName") String prdName);
}