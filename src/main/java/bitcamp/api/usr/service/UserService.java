package bitcamp.api.usr.service;

import bitcamp.api.usr.domain.UserDto;
import bitcamp.api.usr.domain.UserVo;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UserService {
    // security default method
    String signin(String username, String password);
    String signup(UserVo user);
    void delete(String username);
    UserVo whoami(HttpServletRequest req);
    String refresh(String username);

    // 4.상제 사용자 보기
    UserVo search(String username);

    // 2.아이디 중복체크
    boolean checkDuplicateId(String userId);
    boolean checkDuplicateNickname(String userId);

    // 3.이름으로 사용자찾기
    List<UserVo> findUsersByName(String name);

    // 4-1.전체 유저보기
    List<UserVo> findAllUser();
    Optional<UserVo> updateProfile(UserVo userVo);
    void sendMail(String to,String sub, String text);


}