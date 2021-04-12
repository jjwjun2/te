package bitcamp.api.usr.repository;


import bitcamp.api.brd.domain.Board;
import bitcamp.api.pay.domain.Payment;
import bitcamp.api.rcv.domain.Receiver;
import bitcamp.api.usr.domain.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static bitcamp.api.pay.domain.QPayment.payment;
import static bitcamp.api.usr.domain.QUserVo.userVo;
import static bitcamp.api.brd.domain.QBoard.board;
import static bitcamp.api.rcv.domain.QReceiver.receiver;
import static bitcamp.api.brd.domain.QBoard.board;
import java.time.LocalDateTime;
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
        queryFactory.select(payment.payNo)
                .from(payment)
                .join(payment.boards, board)
                .where(board.payment.payNo.eq(payment.payNo))
                .fetch();


        return queryFactory.selectFrom(userVo)
                .where(userVo.username.eq(userId))
                .fetchOne() != null;
    }


    @Override
    public List<UserVo> findAllUser() {
        return queryFactory.selectFrom(userVo)
                .orderBy(userVo.usrNo.asc())
                .fetch();
    }

    @Override
    public List<UserVo> userSearch(UserSearchCondition condition) {
        return null;
    }

    @Override
    public boolean findPassword(String password) {
        return queryFactory.selectFrom(userVo).fetchOne().getPassword().equals(password);
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
        return Optional.ofNullable(
                queryFactory.selectFrom(userVo)
                        .where(userVo.usrEmail.eq(email))
                        .fetchOne());
    }

    @Override
    public UserVo findUserById(String username, String password) {
        return queryFactory.selectFrom(userVo)
                .where(userVo.username.eq(username).and(userVo.password.eq(password)))
                .fetchOne();
    }

    @Override
    public List<Board> userSignDtoBoard() {
        return queryFactory.selectFrom(board)
                .join(board.payment, payment)
                .where(payment.payNo.eq(board.payment.payNo))
                .fetch();
    }

    @Override
    public List<Receiver> userSigninRece() {
        return queryFactory.selectFrom(receiver)
                .join(receiver.payment, payment)
                .where(payment.payNo.eq(receiver.payment.payNo)).fetch();
    }


    @Override
    public List<Payment> userSignDtoPayment() {
        return queryFactory.selectFrom(payment)
                .join(payment.userVo, userVo)
                .where(userVo.usrNo.eq(payment.userVo.usrNo))
                .fetch();
    }


    @Override
    public Optional<UserVo> updateProfile(String email, String password) {
        long result = queryFactory.selectFrom(userVo)
                .where(userVo.usrAge.between(20, 29))
                .fetchCount();


        queryFactory.selectFrom(payment).where(payment.payDate.between("2020-05-01", "2020-09-01"))
                .fetch();
        LocalDateTime localDateTime = LocalDateTime.now();

        return Optional.ofNullable(
                queryFactory.selectFrom(userVo)
                        .where(userVo.usrEmail.eq(email).and(userVo.password.eq(password)))
                        .fetchOne());
    }


    @Override
    public List<UserDto> coveringIndex1(String keyword, int pageNo, int pageSize) {
        List<Long> result = queryFactory
                .select(userVo.usrNo)
                .from(userVo)
                .where(userVo.usrCity.eq(keyword))
                .orderBy(userVo.usrNo.desc())
                .limit(pageSize)
                .offset(pageNo * pageSize)
                .fetch();

        return queryFactory
                .select(Projections.fields(UserDto.class,
                        userVo.usrNo.as("usrNo"),
                        userVo.usrName,
                        userVo.usrAge,
                        userVo.usrGender))
                .from(userVo)
                .where(userVo.usrNo.in(result))
                .orderBy(userVo.usrNo.desc())
                .fetch();
    }

    @Override
    public List<UserDto> coveringIndex2(String keyword, int pageNo, int pageSize) {
        return queryFactory.select(
                Projections.fields(UserDto.class,
                        userVo.usrNo.as("usrNo"),
                        userVo.username,
                        userVo.usrAge,
                        userVo.usrGender
                ))
                .from(userVo)
                .where(userVo.usrCity.eq(keyword))
                .orderBy(userVo.usrNo.desc())
                .fetch();
    }

    @Override
    public List<UserVo> dynamicUserSearch(UserSearchCondition condition) {
        return queryFactory.selectFrom(userVo)
                .where(
                        nameEq(condition.getUserName()),
                        cityEq(condition.getUserCity()),
                        genderEq(condition.getGender()),
                        ageBetween(condition.getAgeGoe(), condition.getAgeLoe())
                ).limit(25)
                .fetch();
    }

    public BooleanExpression nameEq(String username) {
        return StringUtils.hasText(username) ? userVo.username.eq(username) : null;
    }

    public BooleanExpression cityEq(String city) {
        return StringUtils.hasText(city) ? userVo.usrCity.eq(city) : null;
    }

    public BooleanExpression genderEq(String gender) {
        return StringUtils.hasText(gender) ? userVo.usrGender.eq(gender) : null;
    }

    public BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? userVo.usrAge.goe(ageGoe) : null;
    }
    public BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? userVo.usrAge.loe(ageLoe) : null;
    }
    public BooleanExpression ageBetween(int ageLoe, int ageGoe) {
        return ageGoe(ageLoe).and(ageLoe(ageGoe));
    }

    @Override
    public long manData() {
        return queryFactory.select(userVo.usrGender)
                .from(userVo)
                .where(userVo.usrGender.eq("M"))
                .fetchCount();

    }

    @Override
    public long womanData() {
        return queryFactory.select(userVo.usrGender)
                .from(userVo)
                .where(userVo.usrGender.eq("F"))
                .fetchCount();
    }

    @Override
    public long age10sData() {
        return queryFactory.select(userVo.usrGender)
                .from(userVo)
                .where(userVo.usrAge.between(10, 19))
                .fetchCount();
    }

    @Override
    public long age20sData() {
        return queryFactory.select(userVo.usrGender)
                .from(userVo)
                .where(userVo.usrAge.between(20, 29))
                .fetchCount();
    }

    public long age30sData() {
        return queryFactory.select(userVo.usrGender)
                .from(userVo)
                .where(userVo.usrAge.between(30, 39))
                .fetchCount();
    }

    @Override
    public long age40sData() {
        return queryFactory.select(userVo.usrGender)
                .from(userVo)
                .where(userVo.usrAge.between(40, 49))
                .fetchCount();
    }

    @Override
    public long age50sData() {
        return queryFactory.select(userVo.usrGender)
                .from(userVo)
                .where(userVo.usrAge.between(50, 59))
                .fetchCount();
    }

    @Override
    public long age60sData() {
        return queryFactory.select(userVo.usrGender)
                .from(userVo)
                .where(userVo.usrAge.between(60, 69))
                .fetchCount();
    }

    @Override
    public long ageOver70sData() {
        return queryFactory.select(userVo.usrGender)
                .from(userVo)
                .where(userVo.usrAge.goe(70))
                .fetchCount();
    }

    @Override
    public List<Payment> userPaymentJoin() {
        return queryFactory.selectFrom(payment)
                .join(userVo.payments, payment)
                .where(payment.userVo.usrNo.eq(userVo.usrNo)).fetch();
    }

}