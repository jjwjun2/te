package bitcamp.api.brd.service;

import bitcamp.api.usr.domain.Pagination;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface BoardService {
    public Map<String, Object> paging(Pagination pagination, Optional<Integer> userId);


}
