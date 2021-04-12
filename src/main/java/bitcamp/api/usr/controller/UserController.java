package bitcamp.api.usr.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import bitcamp.api.usr.domain.*;
import bitcamp.api.usr.service.UserServiceImpl;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usr")
@Api(tags = "usr")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;


    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseEntity<Map<String, Object>> signin(@Valid @RequestBody UserDto userDto) {
        logger.info("User Login Info: " + userDto.toString());
        System.out.println("=========================");
        return ResponseEntity.ok(userService.signin(userDto.getUsername(), userDto.getPassword()));
    }


    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = { //
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use")})
    public ResponseEntity<String> signup(@ApiParam("Signup User") @RequestBody UserDto user) throws Exception {
        logger.info("User Join Info:" + user.toString());
        return ResponseEntity.ok(userService.signup(modelMapper.map(user, UserDto.class)));
    }


    @GetMapping("/findusers/{name}")
    public ResponseEntity<List<UserVo>> findByName(@RequestBody String name) {
        logger.info("Find user by name: " + name);
        return ResponseEntity.ok(userService.findUsersByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserVo>> findAll() {
        logger.info("Find all users.");
        System.out.println("Find all user");
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/all/detail")
    public ResponseEntity<UserVo> userDetail(@RequestBody UserVo userVo) {
        logger.info("Find detail users.");
        return ResponseEntity.ok(userService.search(userVo.getUsername()));
    }

    @PostMapping("/update/profile")
    public ResponseEntity<Optional<UserVo>> updateProfile(@RequestBody UserVo userVo) {
        logger.info("Update user profile: " + userVo.toString());
        return ResponseEntity.ok(userService.updateProfile(userVo));
    }

    @PostMapping("/update/password")
    public ResponseEntity<Optional<UserVo>> updatePassword(@PathVariable Long id, UserVo userVo) {
        logger.info("Update user profile: " + userVo.toString());
        return ResponseEntity.ok(userService.updateProfile(userVo));
    }

    @DeleteMapping("/delete/{usrNo}")
    public ResponseEntity<Long> delete(@PathVariable UserVo usrNo) {
        logger.info("Delete user :" + usrNo.toString());
        return ResponseEntity.ok(userService.deleteUser(usrNo));
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

    @PostMapping("/paging")
    public ResponseEntity<Map<String, Object>> paging(@RequestBody Pagination pagination,
                                                      @PathVariable("page") Optional<Integer> page) {
        logger.info("Pagination Controller");
        return ResponseEntity.ok(userService.paging(pagination, page));
    }


    @GetMapping("/detail/{usrNo}")
    public ResponseEntity<UserVo> userDetail(@PathVariable Long usrNo) {
        logger.info("Update user profile= " + usrNo.toString());
        return ResponseEntity.ok(userService.usrDetail(usrNo));
    }


    @PostMapping("/admin/statistic")
    public ResponseEntity<UserDataDto> userStatistic(@PathVariable UserDataDto us, @RequestBody UserDataDto userDataDto) {
        logger.info("Send user data= " + userDataDto.toString());
        return ResponseEntity.ok(userService.userStatistic(userDataDto));
    }


}