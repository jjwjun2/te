package bitcamp.api.brd.service;


import java.time.LocalDate; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;


import bitcamp.api.brd.domain.Board;
import bitcamp.api.brd.domain.BoardDto;
import bitcamp.api.brd.repository.BoardRepository;
import bitcamp.api.common.service.AbstractService;
import bitcamp.api.usr.domain.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor 
public class BoardServiceImpl extends AbstractService<Board> implements BoardService{
	private final BoardRepository repository;
	
	@Override public long save(Board t) {
		String title = t.getBrdTitle();
			String date = String.format("%s %s", LocalDate.now(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("a HH시 mm분")));
			t.setBrdWrtDate(date);
			return (repository.save(t)!=null) ? 1:0;

	}
	@Override public long count() {return (long)repository.count();}
	@Override public Board getOne(long id) {return repository.getOne(id);}
	@Override public Optional<Board> findById(long id){return repository.findById(id);}
	@Override public boolean existsById(long id) {return repository.existsById(id);}
	@Override public List<Board> findAll() {
		return repository.findAll().stream().sorted(Comparator.comparing(Board::getBrdWrtDate).reversed()).collect(Collectors.toList());
	}
	@Override 
	public long delete(Board t) {
		repository.delete(t); 
		return (getOne(t.getBrdNo())==null)? 1:0;
	}

	public Board findByTitle(String brdTitle) {
		return repository.findByTitle(brdTitle);
	}
	public Board findByBrd(Board board) {
		return repository.findByBrd(board);
	}
	public List<Board> search(String brdTitle) {
		return repository.search(brdTitle);
	}
	public List<Board> blogListAll() {
		return repository.blogListAll();
	}
	public List<Board> reviewAll() {
		return repository.reviewAll();
	}
	public long update(BoardDto dto) {
		Board map = findById(dto.getBrdNo()).get();
			map.setBrdTitle(dto.getBrdTitle());
			map.setBrdContent(dto.getBrdContent());
			return repository.save(map)==null? 1:0;


	}

	public List<Board> reviewList() {
		return repository.reviewList();
	}

	@Override
	public Map<String, Object> paging(Pagination pagination, Optional<Integer> userId) {
		Map<String, Object> map = new HashMap<>();

		List<Board> result = repository.findAll();
		int pageSize = pagination.getPageSize();
		int currentPage = pagination.getPageNum();
		int totalCount = (int) repository.count();
		System.out.println("pagesize= " + pageSize);
		System.out.println("currentPage= " + currentPage);
		System.out.println("totalCount= " + totalCount);
		Pagination page = new Pagination(pageSize, currentPage, totalCount);
		map.put("paging", page);
		map.put("result", result);
		return map;
	}



	
}
