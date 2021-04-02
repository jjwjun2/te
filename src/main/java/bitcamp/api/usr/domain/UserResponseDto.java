package bitcamp.api.usr.domain;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserResponseDto {
    @ApiModelProperty(position = 0)
    private Integer id;

    @ApiModelProperty(position = 1)
    private String username;

    @ApiModelProperty(position = 2)
    private String usrEmail;

    @ApiModelProperty(position = 3)
    List<Role> roles;
}