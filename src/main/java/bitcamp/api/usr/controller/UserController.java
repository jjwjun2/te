package bitcamp.api.usr.controller;

import java.util.List;
import java.util.Optional;

import bitcamp.api.common.controller.AbstractController;
import bitcamp.api.usr.domain.UserDto;
import bitcamp.api.usr.domain.UserVo;
import bitcamp.api.usr.service.UserService;
import bitcamp.api.usr.service.UserServiceImpl;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usr")
@Api(tags = "usr")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    @PostMapping("/signin/")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = { //
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseEntity<String> signin(@RequestBody UserVo user) {
        logger.info("User Login Info: " + user.toString());
        return ResponseEntity.ok(userService.signin(user.getUsername(), user.getPassword()));
    }


    @PostMapping("/signin/admin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = { //
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseEntity<String> adminSignin(@RequestBody UserVo user) {
        logger.info("User Login Info: " + user.toString());
        return ResponseEntity.ok(userService.signin(user.getUsername(), user.getPassword()));
    }

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = { //
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use")})
    public ResponseEntity<String> signup(@ApiParam("Signup User") @RequestBody UserDto user) throws Exception {
        logger.info("User Join Info:" + user.toString());
        return ResponseEntity.ok(userService.signup(modelMapper.map(user, UserVo.class)));
    }

    // 사용자 찾기
    @GetMapping("/find/{name}")
    public ResponseEntity<List<UserVo>> findByName(@RequestBody UserVo userVo) {
        logger.info("Find user by name: " + userVo.getUsrName());
        return ResponseEntity.ok(userService.findUsersByName(userVo.getUsrName()));
    }

    // 복호화 시키는 것: 시큐리티가 하는
    @GetMapping("/all")
    public ResponseEntity<List<UserVo>> findAll() {
        logger.info("Find all users.");
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/update/profile")
    public ResponseEntity<Optional<UserVo>> updateProfile(@RequestBody UserVo userVo) {
        logger.info("Update user profile: " + userVo.toString());
        return ResponseEntity.ok(userService.updateProfile(userVo));
    }

    @PostMapping("/update/password")
    public ResponseEntity<Optional<UserVo>> updatePassword(@RequestBody UserVo userVo) {
        logger.info("Update user profile: " + userVo.toString());
        return ResponseEntity.ok(userService.updateProfile(userVo));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody UserVo userVo) {
        logger.info("Delete user :" + userVo.toString());
        return ResponseEntity.ok(userService.delete(userVo));
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<UserVo> getOne(@PathVariable long id) {
        return ResponseEntity.ok(userService.getOne(id));
    }


    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        logger.info("Query total count.");
        return ResponseEntity.ok(userService.count());
    }


}