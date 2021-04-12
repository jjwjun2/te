package bitcamp.api.brd.repository;

import static bitcamp.api.brd.domain.QBoard.board;
import static bitcamp.api.prd.domain.QProduct.product;
import java.util.List;       
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.transaction.Transactional;
import static bitcamp.api.brd.domain.QBoard.board;
import bitcamp.api.brd.domain.Board;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;


import lombok.RequiredArgsConstructor;



@Repository
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements IBoardRepository{
	private final JPAQueryFactory qf;
	private final EntityManager em;
	public BoardRepositoryImpl(EntityManager em,JPAQueryFactory qf) {
		super(Board.class);
		this.qf = qf;
		this.em = em;
		
	}

	@Override
	public Board findByTitle(String brdTitle) {
//		return em.createQuery("select b2 from Board b2 where b2.brd_title like :brdTitle")
//				.setParameter("brdTitle", brdTitle)
//				.getResultList();
		return qf.selectFrom(board).where(board.brdTitle.eq(brdTitle)).fetchOne();
	}
	@Transactional
	@Override
	public Board findByBrd(Board brd) {
		qf.update(board).set(board.brdCount, brd.getBrdCount()+1).where(board.brdNo.eq(brd.getBrdNo())).execute();
		return qf.selectFrom(board).where(board.brdNo.eq(brd.getBrdNo())).fetchOne();
	}



	@Override
	public List<Board> search(String brdTitle) {

		return qf.selectFrom(board).where(board.brdTitle.contains(brdTitle)).fetch();
	}
	@Override
	public List<Board> blogListAll(){
//		System.out.println("===========================");
//		 qf.select(board.brdTitle,board.brdWrtDate,userVo.usrNickname)
//				.from(board).join(board.user,userVo).where(board.brdKind.eq(1L)).orderBy(board.brdWrtDate.desc()).fetch().stream().forEach(System.out::println);
//		System.out.println("===========================");
		return qf.selectFrom(board).where(board.brdKind.eq(1L)).orderBy(board.brdWrtDate.desc()).fetch();
	}

	@Override
	public List<Board> reviewAll() {
		return qf.selectFrom(board).where(board.brdKind.eq(2L)).orderBy(board.brdWrtDate.desc()).fetch();
	}

	@Override
	public List<Board> reviewList() {
		return qf.selectFrom(board)
				.join(board.product, product)
				.where(product.prdNo.eq(board.product.prdNo))
				.fetch();

	}


	


}
