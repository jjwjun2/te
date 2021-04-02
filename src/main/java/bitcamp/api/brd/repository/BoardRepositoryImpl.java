package bitcamp.api.brd.repository;

import java.util.List;

import javax.persistence.EntityManager;

import bitcamp.api.brd.domain.Board;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;





@Repository
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements IBoardRepository{
    //private final JPAQueryFactory qf;
    private final EntityManager em;
    public BoardRepositoryImpl(EntityManager em) {
        super(Board.class);
        //this.qf = qf;
        this.em = em;

    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Board> findByTitle(String brdTitle) {
        return em.createQuery("select b2 from Board b2 where b2.brd_title like :brdTitle")
                .setParameter("brdTitle", brdTitle)
                .getResultList();
    }
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Board> findByWriteDate(String writeDate) {
//		return em.createQuery("select b from Board b where b.brdwritten_date like :brdwrittenDate")
//				.setParameter("writeDate", writeDate)
//				.getResultList();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<BoardDto> findByUserNo(long usrNo) {
//		return em.createQuery("select "
//				+ "b.brd_no brdNum "
//				+ "b.brd_title brdTitle "
//				+ "b.brd_content brdContent "
//				+ "b.brdwritten_date brdwrittenDate "
//				+ "b.brd_rank brdRank "
//				+ "b.brd_img brdImg "
//				+ "b.brd_kind brdKind "
//				+ "b.count count "
//				+ "b.brd_like brdLike "
//				+ "b.brd_pwd brdPwd "
//				+ "b.usr_no usrNo "
//				+ "b.usr_name usrName \n"
//				+ "from Board b inner join User u on b.usr_no = u.usr_no \n"
//				+ "where b.usr_no like : usrNo")
//				.setParameter("usrNo", usrNo)
//				.getResultList();
//	}



}