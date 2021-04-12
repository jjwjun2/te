package bitcamp.api.brd.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import bitcamp.api.brd.domain.Board;
import bitcamp.api.brd.domain.BoardDto;
import bitcamp.api.brd.repository.BoardRepository;
import bitcamp.api.brd.service.BoardServiceImpl;

import bitcamp.api.usr.domain.Pagination;
import org.modelmapper.ModelMapper;
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
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/board")
public class BoardController {
    private final BoardServiceImpl service;
    private final BoardRepository rep;
    private final ModelMapper modelMapper;

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody Board t) {
        return ResponseEntity.ok(service.save(t));
    }

    @DeleteMapping("/delete/{brdNo}")
    public ResponseEntity<Long> delete(@RequestBody Board brdNo) {
        System.out.println("삭제");
        return ResponseEntity.ok(service.delete(brdNo));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.count());
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Board> getOne(@PathVariable long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Board>> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable long id) {
        return ResponseEntity.ok(service.existsById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Board>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/option/{brdTitle}")
    public ResponseEntity<Board> findByTitle(@PathVariable String brdTitle) {
        System.out.println("상세페이지");
        return ResponseEntity.ok(service.findByTitle(brdTitle));
    }

    @GetMapping("/opt/{brdNo}")
    public ResponseEntity<Board> findByBrd(@PathVariable Board brdNo) {
        System.out.println("페이지");
        return ResponseEntity.ok(service.findByBrd(brdNo));
    }

    @GetMapping("/search/{brdTitle}")
    public ResponseEntity<List<Board>> search(@PathVariable String brdTitle) {
        System.out.println("검색");
        return ResponseEntity.ok(service.search(brdTitle));
    }


    @PutMapping("/update/{brdNo}")
    public ResponseEntity<Long> update(@PathVariable long brdNo, @RequestBody BoardDto t) {
        System.out.println("업데이트" + t.toString());
        return ResponseEntity.ok(service.update(t));
    }

    @GetMapping("/blogAll")
    public ResponseEntity<List<Board>> blogListAll() {
        System.out.println("블로그 목록");

        return ResponseEntity.ok(service.blogListAll());
    }

    @GetMapping("/review/all")
    public ResponseEntity<List<Board>> reviewAll() {

        return ResponseEntity.ok(service.reviewAll());
    }

    @GetMapping("/review/list")
    public ResponseEntity<List<Board>> reviewList() {
        System.out.println("리뷰 목록");

        return ResponseEntity.ok(service.reviewList());
    }

    @PostMapping("/paging")
    public ResponseEntity<Map<String, Object>> paging(@RequestBody Pagination pagination,
                                                      @PathVariable("page") Optional<Integer> page) {

        return ResponseEntity.ok(service.paging(pagination, page));
    }

}
