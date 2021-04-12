package bitcamp.api.usr.repository;

import bitcamp.api.pay.domain.Payment;
import bitcamp.api.rcv.domain.Receiver;
import bitcamp.api.usr.domain.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jdo.annotations.Transactional;
import java.util.List;
import java.util.Optional;

interface IUserRepository  {
    List<UserVo> findUserByName(String name);
    boolean checkDuplicateId(String userId);
    boolean findUserByEmail(String email);
    String findIdByEmail(String email);
    Optional<UserVo> findUserById(String email);
    Optional<UserVo> updateProfile(String email, String password);
    UserVo findUserById(String username, String password);
    List<UserVo> findAllUser();
    List<Payment> userPaymentJoin();
    List<Receiver> userSigninRece();
    boolean findPassword(String password);





}

public interface UserRepository extends JpaRepository<UserVo, Long>, IUserRepository {
    boolean existsByUsername(String username);

    UserVo findByUsername(String username);

    //	Page<UserVo> findByUserNoGreaterThan(Long userNo, Pageable paging);
    @Transactional
    void deleteByUsername(String username);
}