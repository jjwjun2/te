package bitcamp.api.usr.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.List;

@Component
@Data
public class UserDto {

    private static final long serialVersionUID = 1L;

    private Long usrNo;

    @NotEmpty @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9][\\w]{7,17}$", message = "8자리 이상 18자리 이하의 숫자만 입력가능합니다.")
    private String username;    // 아이디

    @NotEmpty @NotNull
    @Pattern(regexp = "^[a-zA-Z가-힣]{2,12}$")
    private String usrName;     // 이름


    @NotEmpty @NotNull
    private String usrEmail;    // 이메일


    @NotEmpty @NotNull
    @Pattern(regexp = "[\\.가-힣]*[\\w-]{4,17}$")
    private String password;    // 비밀번호


    @NotEmpty @NotNull
    @Pattern(regexp = "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$")
    private String usrPhone;    // 전화번호

    @NotEmpty @NotNull
    @Pattern(regexp = "^[\\w가-힣]{2,15}$", message = "2자리 이상 15 이내의 글자를 입력해주세요.")
    private String usrNickname; // 닉네임


    //	@ApiModelProperty(position = 3)
    List<Role> roles;       // 레벨

    private String usrAges;
    private String usrCity;
    private String usrGender;
    private String usrAddr;


    public UserVo toEntity() {
        return UserVo.builder()
                .usrName(usrName)
                .username(username)
                .usrNickname(usrNickname)
                .usrEmail(usrEmail)
                .usrPhone(usrPhone)
                .build();
    }

/*
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDto that = (UserDto) o;
		return Objects.equals(usrNo, that.usrNo);
	}
	@Override
	public int hashCode() {
		return Objects.hash(usrNo);
	}
*/


}