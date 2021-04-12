package bitcamp.api.usr.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserPageDto {
    private int pageSize;
    private int page;
    private String name;
    private String nickName;
    private String ageGoe;
    private String ageLoe;
    private String userAddrs;

}
