package bitcamp.api.usr.service;

import bitcamp.api.usr.domain.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    UserSignDto signinUser(String username, String password);
    String signup(UserDto user);
    void delete(String username);
    UserVo whoami(HttpServletRequest req);
    String refresh(String username);
    Map<String, Object> paging(Pagination pagination, Optional<Integer> userId);
    Map<String,Object> signin(String username, String password);
    UserVo search(String username);
    UserDataDto userStatistic(UserDataDto userDataDto);
    boolean checkDuplicateEmail(String usrMail);
    boolean checkPassword(String password);
    boolean checkDuplicateId(String userId);
    Long deleteUser(UserVo userDto);
    List<UserVo> findUsersByName(String name);
    List<UserVo> findAllUser();
    Optional<UserVo> updateProfile(UserVo userVo);
    Map<String, Object> pagenationtest(UserPageDto userPageDto);
    List<UserVo> findUserDynamicSearch(UserSearchCondition keyword);
    UserVo passwordUpdate(UserDto userDto);
    UserVo usrDetail(Long usrNo);
    UserVo delete(UserDto userDto);
    UserVo addUser(UserDto userDto);
}