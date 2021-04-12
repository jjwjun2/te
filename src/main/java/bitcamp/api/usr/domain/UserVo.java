package bitcamp.api.usr.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import bitcamp.api.pay.domain.Payment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity
@Getter
@Table(name = "users")
public class UserVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_no")
    private Long usrNo;


    @Column(name = "usr_name")
    private String usrName; //

    @Column(name = "usr_email")
    private String usrEmail;

    @Column(name = "password")
    private String password;

    @Column(name = "usr_ages")
    private int usrAge;

    @Column(name = "usr_city")
    private String usrCity;

    @Column(name = "usr_gender")
    private String usrGender;

    @Column(name = "usr_phone")
    private String usrPhone;

    @Column(name = "username")
    private String username;

    @Column(name = "usr_addr")
    private String usrAddr;

    @ElementCollection(fetch = FetchType.LAZY)
    List<Role> roles;

    @OneToMany(mappedBy = "userVo")
    @JsonBackReference
    private List<Payment> payments = new ArrayList<>();


    public UserVo() {
    }

    public UserVo(String usrName, String usrEmail, String password,
                  int usrAge, String usrCity, String usrGender, String usrPhone, String username,
                  String usrAddr, List<Role> role) {
        this.usrName = usrName;
        this.usrEmail = usrEmail;
        this.password = password;
        this.usrAge = usrAge;
        this.usrCity = usrCity;
        this.usrGender = usrGender;
        this.usrPhone = usrPhone;
        this.username = username;
        this.usrAddr = usrAddr;
        this.roles = role;
    }

    private UserVo(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.usrName = builder.usrName;
        this.usrPhone = builder.usrPhone;
        this.usrEmail = builder.usrEmail;
    }


    public static class UserBuilder implements CommonBuilder<UserVo> {
        private final String username;
        private final String password;
        private final String usrName;
        private final String usrPhone;
        private final String usrEmail;


        public UserBuilder(UserDto userDto) {
            this.username = userDto.getUsername();
            this.password = userDto.getPassword();
            this.usrName = userDto.getUsrName();
            this.usrPhone = userDto.getUsrPhone();
            this.usrEmail = userDto.getUsrEmail();
        }


        @Override
        public UserVo build() {
            return new UserVo(this);
        }
    }
}


