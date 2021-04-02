package bitcamp.api.brd.repository;

import java.util.List;

import bitcamp.api.brd.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;


interface IBoardRepository {
    public List<Board> findByTitle(String brdTitle);
//	public List<Board> findByWriteDate(String writeDate);
//	public List<BoardDto> findByUserNo(int usrNo);


}

public interface BoardRepository extends JpaRepository<Board, Long>, IBoardRepository {


}