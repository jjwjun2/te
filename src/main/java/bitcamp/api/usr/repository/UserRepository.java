package bitcamp.api.usr.repository;

import bitcamp.api.brd.domain.Board;
import bitcamp.api.pay.domain.Payment;
import bitcamp.api.rcv.domain.Receiver;
import bitcamp.api.usr.domain.UserDto;
import bitcamp.api.usr.domain.UserSearchCondition;
import bitcamp.api.usr.domain.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jdo.annotations.Transactional;
import java.util.List;
import java.util.Optional;

interface IUserRepository  {
    List<UserVo> findUserByName(String name);

    boolean checkDuplicateId(String userId);

//    boolean checkDuplicateNickname(String userNickname);

    boolean findUserByEmail(String email);

    String findIdByEmail(String email);

    Optional<UserVo> findUserById(String email);

    Optional<UserVo> updateProfile(String email, String password);

    // Login
    UserVo findUserById(String username, String password);
    List<UserVo> findAllUser();

    //	public Page<UserDto> paginationCountSearchBtn2(boolean useSearchBtn, Pageable pageable, String name);
    List<Payment> userPaymentJoin();
    List<UserVo> userSearch(UserSearchCondition condition);
    List<Receiver> userSigninRece();
    boolean findPassword(String password);
    List<UserDto> coveringIndex1(String keyword, int pageNo, int pageSize);
    List<UserDto> coveringIndex2(String keyword, int pageNo, int pageSize);
    List<Board> userSignDtoBoard();
    List<Payment> userSignDtoPayment();
    List<UserVo> dynamicUserSearch(UserSearchCondition condition);
    long manData();
    long womanData();
    long age10sData();
    long age20sData();
    long age30sData();
    long age40sData();
    long age50sData();
    long age60sData();
    long ageOver70sData();




}

public interface UserRepository extends JpaRepository<UserVo, Long>, IUserRepository {
    boolean existsByUsername(String username);

    UserVo findByUsername(String username);

    //	Page<UserVo> findByUserNoGreaterThan(Long userNo, Pageable paging);
    @Transactional
    void deleteByUsername(String username);
}