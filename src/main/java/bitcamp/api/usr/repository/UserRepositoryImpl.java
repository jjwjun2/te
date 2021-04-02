package bitcamp.api.usr.repository;


import bitcamp.api.usr.domain.UserVo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static bitcamp.api.usr.domain.QUserVo.userVo;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements IUserRepository {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
        super(UserVo.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public boolean checkDuplicateId(String userId) {
        return queryFactory.selectFrom(userVo)
                .where(userVo.username.eq(userId))
                .fetchOne() != null;
    }

    @Override
    public boolean checkDuplicateNickname(String userNickname) {
        return queryFactory.selectFrom(userVo)
                .where(userVo.usrNickname.eq(userNickname))
                .fetchOne() != null;
    }

    @Override
    public List<UserVo> findAllUser() {
        return queryFactory.selectFrom(userVo)
                .orderBy(userVo.usrName.desc())
                .fetch();
    }


    @Override
    public List<UserVo> findUserByName(String name) {
        return queryFactory.selectFrom(userVo)
                .where(userVo.usrName.eq(name)).fetch();
    }


    @Override
    public boolean findUserByEmail(String email) {
        return false;
    }


    @Override
    public String findIdByEmail(String email) {
        return queryFactory.select(userVo.username).from(userVo).fetchOne();
    }

    @Override
    public Optional<UserVo> findUserById(String email) {
        return Optional.ofNullable(queryFactory.selectFrom(userVo)
                .where(userVo.usrEmail.eq(email))
                .fetchOne());
    }


    @Override
    public Optional<UserVo> updateProfile(String email, String password) {
        return Optional.ofNullable(
                queryFactory.selectFrom(userVo)
                        .where(userVo.usrEmail.eq(email).and(userVo.password.eq(password)))
                        .fetchOne());
    }


}