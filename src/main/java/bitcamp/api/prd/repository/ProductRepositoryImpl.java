package bitcamp.api.prd.repository;

import java.util.List;

import javax.persistence.EntityManager;

import bitcamp.api.prd.domain.Product;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ProductRepositoryImpl extends QuerydslRepositorySupport implements IProductRepository {
    private final EntityManager em;
    private final JPAQueryFactory qf;

    public ProductRepositoryImpl(EntityManager em, JPAQueryFactory qf) {
        super(Product.class);
        this.qf = qf;
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> findByPrdNo(long prdNo) {
        return em.createQuery("select prd from Product prd where prd.prd_no like :prdNo")
                .setParameter("prdNo", prdNo).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> findByCtgName(String ctgName) {
        return em.createQuery("select prd from Product prd where prd.ctg_name like :ctgName")
                .setParameter("ctgName", ctgName).getResultList();
    }

//	1개. null
//	Optional<>
//	fetchOne.2개
//	@Override
//	public List<Product> findByPrdName(String prdName) {
//		qf.selectFrom("dd").where("dd".equals(prdName).fetch().stream().forEach(System.out::println);
//		return qf.selectFrom("dd").where("dd".equals(prdName).fetch());
//	}
}