package bitcamp.api.usr.domain;

import lombok.Data;

@Data
public class UserDataDto {
    private Integer manData;
    private Integer womanData;
    private Integer ages10s;
    private Integer ages20s;
    private Integer ages30s;
    private Integer ages40s;
    private Integer ages50s;
    private Integer ages60s;
    private Integer agesOver70s;
    private String city;
}
