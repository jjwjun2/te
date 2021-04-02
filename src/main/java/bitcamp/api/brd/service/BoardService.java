package bitcamp.api.brd.service;

import bitcamp.api.brd.domain.Board;

import java.util.List;

public interface BoardService {
    public List<Board> findByTitle(String title);

}