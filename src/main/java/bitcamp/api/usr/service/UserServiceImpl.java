package bitcamp.api.usr.service;

import bitcamp.api.common.service.AbstractService;
import bitcamp.api.security.domain.SecurityProvider;
import bitcamp.api.security.exception.SecurityRuntimeException;
import bitcamp.api.usr.domain.*;
import bitcamp.api.usr.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserServiceImpl extends AbstractService<UserVo> implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityProvider provider;
    private final AuthenticationManager manager;

    @Override
    public UserVo search(String username) {
        UserVo user = userRepository.findByUsername(username);
        if (user == null) {
            throw new SecurityRuntimeException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @Override
    public List<UserVo> findUsersByName(String name) {
        return userRepository.findUserByName(name);
    }


    @Override
    public List<UserVo> findAllUser() {
        return userRepository.findAllUser();
    }


    @Override
    public List<UserVo> findAll() {
        return userRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(UserVo::getUsrNo))
                .collect(Collectors.toList());
    }

    // Read
    @Override
    public Optional<UserVo> updateProfile(UserVo userVo) {
        return userRepository.updateProfile(userVo.getUsrEmail(), userVo.getPassword());
    }

    @Override
    public Map<String, Object> pagenationtest(UserPageDto userPageDto) {
        return null;
    }


    @Override
    public UserVo usrDetail(Long usrNo) {
        System.out.println("findbyId=" + findById(usrNo).get().toString());
        return userRepository.findById(usrNo).get();
    }

    @Override
    public Map<String, Object> paging(Pagination pagination, Optional<Integer> userId) {
        Map<String, Object> map = new HashMap<>();
        List<UserVo> result = userRepository.findAll();
        int pageSize = pagination.getPageSize();
        int currentPage = pagination.getPageNum();
        int totalCount = (int) userRepository.count();
        System.out.println("pagesize= " + pageSize);
        System.out.println("currentPage= " + currentPage);
        System.out.println("totalCount= " + totalCount);
        Pagination page = new Pagination(pageSize, currentPage, totalCount);
        map.put("paging", page);
        map.put("user", result);
        return map;
    }

    // Update
    @Override
    public long save(UserVo userVo) {
        Optional<UserVo> user = userRepository.findUserById(userVo.getUsername());
        if (user.isPresent()) {

        }
        return userRepository.save(userVo) != null ? 1 : 0;
    }


    public void idCheck() {
    }

    // Delete
    @Override
    public long delete(UserVo userVo) {
        System.out.println(userVo.toString() + "User delete.");
        userRepository.delete(userVo);
        return findById(userVo.getUsrNo()).isPresent() ? 1 : 0;
    }


    @Override
    public Map<String, Object> signin(String username, String password) {
        try {
            Map<String, Object> map = new HashMap<>();
            System.out.println("ID: " + username);
            UserVo user = userRepository.findByUsername(username);
            List<Role> roles = user.getRoles();
            String tok = provider.createToken(username, roles);
            map.put("token", provider.createToken(username, roles));
            map.put("user", user);
            System.out.println("token :: " + tok);
            return map;
        } catch (AuthenticationException e) {
            throw new SecurityRuntimeException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @Override
    public String signup(UserDto userDto) {
        logger.info("====================Admin Login====================");
        if (!checkDuplicateId(userDto.getUsername())) {
            userDto.setUsername(userDto.getUsername());
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDto.setUsrEmail(userDto.getUsrEmail());
            userDto.setUsrPhone(userDto.getUsrPhone());
            userDto.setRoles(Arrays.asList(Role.USER));
            addUser(userDto);
            return provider.createToken(userDto.getUsername(), userDto.getRoles());
        } else {
            throw new SecurityRuntimeException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public String signupAdmin(UserDto user) {
        logger.info("====================Admin Login====================");
        if (!checkDuplicateId(user.getUsername())) {
            user.setUsername(user.getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUsrEmail(user.getUsrEmail());
            user.setUsrPhone(user.getUsrPhone());
            user.setRoles(Arrays.asList(Role.ADMIN));
            addUser(user);
            return provider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new SecurityRuntimeException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }


    @Override
    public boolean checkDuplicateId(String userId) {
        if (userId != null) {
            return userRepository.checkDuplicateId(userId);
        }
        throw new SecurityRuntimeException("This ID already exists.", HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @Override
    public boolean checkDuplicateEmail(String usrMail) {
        if (usrMail != null) {
            return userRepository.checkDuplicateId(usrMail);
        }
        throw new SecurityRuntimeException("This email is a duplicate email.", HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }


    @Override
    public long count() {
        return userRepository.count();
    }


    @Override
    public Optional<UserVo> findById(long id) {
        return userRepository.findById(id);
    }


    @Override
    public UserVo getOne(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public UserVo whoami(HttpServletRequest req) {
        return userRepository.findByUsername(provider.getUsername(provider.resolveToken(req)));
    }

    @Override
    public String refresh(String username) {
        return provider.createToken(username, userRepository.findByUsername(username).getRoles());
    }


    @Override
    public UserVo addUser(UserDto userDto) {
        UserVo userVo = new UserVo.UserBuilder(userDto).build();
        userRepository.save(userVo);
        return userVo;
    }

    @Override
    public UserVo delete(UserDto userDto) {
        UserVo userVo = new UserVo.UserBuilder(userDto).build();
        userRepository.delete(userVo);
        return userVo;
    }

    @Override
    public Long deleteUser(UserVo userDto) {
        System.out.println("userDTO= " + userDto.toString());
        deleteUser(userDto);
        return findById(userDto.getUsrNo()).isEmpty() ? 1L : 0L;
    }


    @Override
    public boolean checkPassword(String password) {
        if (password != null) {
            return userRepository.findPassword(password);
        }
        throw new SecurityRuntimeException("The password does not match.", HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @Override
    public UserVo passwordUpdate(UserDto userDto) {
        userDto.setPassword(userDto.getPassword());
        return addUser(userDto);
    }

    @Override
    public boolean existsById(long id) {
        return false;
    }

    @Override
    public UserSignDto signinUser(String username, String password) {
        return null;
    }
}