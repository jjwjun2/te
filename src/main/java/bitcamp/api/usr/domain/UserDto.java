package bitcamp.api.usr.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@ToString
public class UserDto {

    private static final long serialVersionUID = 1L;

    private Long usrNo;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "([\\w가-힣]*){2,17}$")
    private String usrName;

    @Pattern(regexp = "[\\a-zA-Z0-9]*[\\w-]{2,15}$")
    private String usrEmail;

    @NotNull
    @NotEmpty
    private String password;
    private int usrAge;
    private String usrCity;


    private String usrGender;

    @NotNull
    @NotEmpty
    private String usrPhone;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9][\\w]{7,15}$", message = "8자리 이상 18자리 이하의 숫자만 입력가능합니다.")
    private String username;
    private String usrAddr;
    private List<Role> roles;

    public UserDto() {}


    public UserDto(String usrName, int usrAge, String usrGender) {
        this.usrName = usrName;
        this.usrAge = usrAge;
        this.usrGender = usrGender;
    }

}