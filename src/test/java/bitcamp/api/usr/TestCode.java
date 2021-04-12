package bitcamp.api.usr;

import bitcamp.api.ApiApplication;
import bitcamp.api.data.*;
import bitcamp.api.pay.domain.Payment;
import bitcamp.api.usr.domain.Role;
import bitcamp.api.usr.domain.UserSearchCondition;
import bitcamp.api.usr.domain.UserVo;
import bitcamp.api.usr.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static bitcamp.api.usr.domain.QUserVo.userVo;
import static bitcamp.api.pay.domain.QPayment.payment;


@Commit
@SpringBootTest
@Transactional
@ContextConfiguration(classes = ApiApplication.class)
class TestCode {
    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @Autowired
    UserRepository userRepository;

    @Test
    public void exe() {
        System.out.println("Ex");
    }

    @BeforeEach
    public void beforeTest() {

        // 데이터 생성기
        queryFactory = new JPAQueryFactory(em);
        AgeGenerator ageGenerator = new AgeGenerator();
        NameGenerator nameGenerator = new NameGenerator();
        CityGenerator cityGenerator = new CityGenerator();
        GenderGenerator genderGenerator = new GenderGenerator();
        TeamGenerator teamGenerator = new TeamGenerator();
        PriceGenerator priceGenerator = new PriceGenerator();
        CategoryGenerator categoryGenerator = new CategoryGenerator();
        Random random = new Random();
        EmailGenerator emailGenerator = new EmailGenerator();
        PhoneNumberGenerator phoneNumberGenerator = new PhoneNumberGenerator();
        PayStateGenerator payStateGenerator = new PayStateGenerator();
        DateGenerator dateGenerator = new DateGenerator();
        List<Role> list = Arrays.asList(Role.USER);
        for (int i = 0; i < 100000; i++) {
            long usrNo = (i + 1);
            String password =
                    IntStream.iterate(0, j -> (char) (random.nextInt('Z' - 'A') + 'A'))
                            .limit(25)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();
            String username = "userId_is_" + i;
            String address = cityGenerator.randomCitySelect();
            String manName = nameGenerator.mannameGenerator();
            String womanName = nameGenerator.womannameGenerator();
            int number = random.nextInt(2) + 1;
            String name = number == 1 ? manName : womanName;
            String nickname = name.substring(1, name.length());
            // Gender
            String gender = number == 1 ? "M" : "F";
            String phoneNumber = phoneNumberGenerator.phoneNumberGenerator();
            System.out.println(phoneNumber);

            // Id
            String id = String.format("usr%d", i + 1);

            // City
            String city = cityGenerator.randomCitySelect();

            // Age
            int age = ageGenerator.randomAgeGenerator();

            // Category
            String category = categoryGenerator.categoryGenerator();

            // Price
            String price = priceGenerator.priceGenerator();

            // Email
            String email = emailGenerator.baseEmailId();

            List<Role> admin = Arrays.asList(Role.ADMIN);
            UserVo userVo = new UserVo(name, email, password, age, city, gender,
                    phoneNumber, username, city, list);
            em.persist(userVo);

//            Payment payment = new Payment(userVo, priceGenerator.priceGenerator(),
//                    payStateGenerator.payStateGenerator(), dateGenerator.monthGenerator());
//            em.persist(payment);

            System.out.println("Number " + i);
        }
    }



    @Test
    public void execution() {
        System.out.println("execute!");
    }


    @Test
    public void sortingTest_First() {
        long start = System.currentTimeMillis();
//        userRepository.findAll().stream().sorted(Comparator.comparing(UserVo::getUsrNo).reversed());
        long end = System.currentTimeMillis();
        System.out.println("Time= " + (end - start) / 1000 + "초");
    }

    @Test
    public void sortingTest_Second() {
        long start = System.currentTimeMillis();
        userRepository.findAllUser();
        long end = System.currentTimeMillis();
        System.out.println("Time= " + (end - start) / 1000 + "초");
    }



    @Test
    public void coveringIndexTest2() {
        long start = System.currentTimeMillis();
//        queryFactory.select(userVo.usrNo, userVo.usrName, userVo.usrAge)
//                .from(userVo)
//                .where(userVo.usrNo.goe(50))
//                .orderBy(userVo.usrNo.asc())
//                .limit(50).offset(30).fetch().forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("Time= " + (end - start));
    }

    @Test
    public void getOne_First() {
        System.out.println("=======================");
        UserVo user = userRepository.getOne(1L);
        System.out.println("=======================");
    }

    @Test
    public void getOne_Second() {
        System.out.println("=======================");
        UserVo user = userRepository.getOne(1L);
        System.out.println("=======================");
        user.getUsername();
    }

    @Test
    public void findById_First() {
        System.out.println("=======================");
        Optional<UserVo> user = userRepository.findById(1L);
        System.out.println("=======================");
    }

    @Test
    public void findById_Second() {
        System.out.println("=======================");
        Optional<UserVo> user = userRepository.findById(1L);
        System.out.println("=======================");
    }


    @Test
    public void streamTest_First() {
        long start = System.currentTimeMillis();
        userRepository.findAll().stream()
                .sorted(Comparator.comparing(UserVo::getUsrNo)).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("Time= " + (end - start));
    }

    @Test
    public void streamTest_Second() {
        long start = System.currentTimeMillis();
        userRepository.findAllUser();
        long end = System.currentTimeMillis();
        System.out.println("Time= " + (end - start));
    }

    @Test
    public void streamTest_Third() {
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            userRepository.findAllUser();
            long end = System.currentTimeMillis();
            System.out.println("Basic= " + (end - start));

            System.out.println("================================");

            long start2 = System.currentTimeMillis();
            userRepository.findAll().stream()
                    .sorted(Comparator.comparing(UserVo::getUsrNo)).collect(Collectors.toList());
            long end2 = System.currentTimeMillis();
            System.out.println("Custom= " + (end2 - start2));
        }
    }

    @Test
    public void hannaJoin_Test() {
        queryFactory.select(userVo.usrName)
                .from(userVo)
                .join(userVo.payments, payment)
                .where(payment.payState.eq("Unpurchased"))
                .fetch();
        String payState = queryFactory.select(payment.payState)
                .from(payment)
                .join(payment.userVo, userVo)
                .where(payment.payState.eq("Purchase"))
                .fetchOne();
    }

    @Test
    public void timeTest() {
        queryFactory.selectFrom(payment)
                .where(payment.payDate.loe(String.valueOf(LocalDateTime.now())))
                .fetch()
                .stream().forEach(System.out::println);
    }


    @Test
    public void test33_() {
        userRepository.userSignDtoPayment().stream().limit(10).forEach(System.out::println);
    }


    @Test
    public void dynamicQueryTest() {
        UserSearchCondition condition = new UserSearchCondition();
        condition.setUserName("김지원");
        condition.setAgeGoe(20);
        condition.setAgeLoe(60);
        condition.setGender("F");
        condition.setUserCity("서울");
        userRepository.dynamicUserSearch(condition).forEach(System.out::println);
    }

    @Test
    public void statistic_man() {
        long manCount = userRepository.manData();
        long womanCount = userRepository.womanData();

        long totalCount = userRepository.count();
        totalCount = manCount + womanCount;

        System.out.println("Man= " + manCount);
        System.out.println("Woman= " + womanCount);
        System.out.println("Total= " + totalCount);

    }

    @Test
    public void statistic_age() {

        // Given
        long ages10s = userRepository.age10sData();
        long ages20s = userRepository.age20sData();
        long ages30s = userRepository.age30sData();
        long ages40s = userRepository.age40sData();
        long ages50s = userRepository.age50sData();
        long ages60s = userRepository.age60sData();
        long agesOver70s = userRepository.ageOver70sData();

        // When
        long totalCount = userRepository.count();

        // Then
        totalCount = ages10s + ages20s + ages30s
                + ages40s + ages50s + ages60s + agesOver70s;

        System.out.println(ages10s);
        System.out.println(ages20s);
        System.out.println(ages30s);
        System.out.println(ages40s);
        System.out.println(ages50s);
        System.out.println(ages60s);
        System.out.println(agesOver70s);
        System.out.println(totalCount);
    }

    @Test
    public void tte3() {
        queryFactory.selectFrom(payment)
                .join(payment.userVo, userVo)
                .where(userVo.usrNo.eq(1L))
                .fetch().stream().forEach(System.out::println);
    }



    // Covering Inex Test
    @Test
    public void coveringIndexTest() {
        String keyword = "동물";
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            userRepository.coveringIndex1(keyword, 20000, 10);
            long end = System.currentTimeMillis();
            System.out.println(String.format("Coveringindex Time= %d", (end - start)));

            long start2 = System.currentTimeMillis();
            userRepository.coveringIndex2(keyword, 20000, 10);
            long end2 = System.currentTimeMillis();
            System.out.println(String.format("NonCoveringindex Time= %d", (end2 - start2)));
            System.out.println("==================================");
        }

    }


    /**
     * Sorting Test
     * */

    @Test
    public void stream_sorting(){
        userRepository.findAllUser().stream().limit(100).forEach(System.out::println);
    }

    @Test
    public void jpa_Sorting(){
        userRepository.findAll().stream().limit(100).forEach(System.out::println);
    }

    @Test
    public void sorting_Integerate(){
        for(int i=0; i<10; i++){
            long start1 = System.currentTimeMillis();
            userRepository.findAllUser();
            long end1 = System.currentTimeMillis();
            System.out.println("1.가져온 후 정렬= " + (end1 - start1));

            long start2 = System.currentTimeMillis();
            userRepository.findAll();
            long end2 = System.currentTimeMillis();
            System.out.println("2.정렬 후 가져오기= " + (end2 - start2));
            System.out.println("=================");
        }
    }


    @Test
    public void findByName() {
        String name = "김태영";
        userRepository.findUserByName(name).forEach(System.out::println);
    }
}