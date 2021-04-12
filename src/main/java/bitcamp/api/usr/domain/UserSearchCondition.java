package bitcamp.api.usr.domain;

import lombok.Data;

@Data
public class UserSearchCondition {
    private String userName;
    private Integer ageLoe;
    private Integer ageGoe;
    private String userCity;
    private String gender;
}