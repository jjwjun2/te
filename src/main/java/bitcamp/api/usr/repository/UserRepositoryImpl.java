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
    public List<Receiver> userSigninRece() {
        return queryFactory.selectFrom(receiver)
                .join(receiver.payment, payment)
                .where(payment.payNo.eq(receiver.payment.payNo)).fetch();
    }





    @Override
    public Optional<UserVo> updateProfile(String email, String password) {
        long result = queryFactory.selectFrom(userVo)
                .where(userVo.usrAge.between(20, 29))
                .fetchCount();


        queryFactory.selectFrom(payment).where(payment.payDate.between("2020-05-01", "2020-09-01"))
                .fetch();

        return Optional.ofNullable(
                queryFactory.selectFrom(userVo)
                        .where(userVo.usrEmail.eq(email).and(userVo.password.eq(password)))
                        .fetchOne());
    }





    @Override
    public List<Payment> userPaymentJoin() {
        return queryFactory.selectFrom(payment)
                .join(userVo.payments, payment)
                .where(payment.userVo.usrNo.eq(userVo.usrNo)).fetch();
    }



}