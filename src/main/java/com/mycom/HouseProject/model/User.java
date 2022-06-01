package com.mycom.HouseProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,}", message = "아이디는 영문, 숫자 포함 4자 이상입니다.")
    private String userid;

    //@NotBlank(message = "비밀번호를 입력해주세요.")
    //@Pattern(regexp = "^.*(?=^.{8,16}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[\\d~!@#$%*^&+=]).*$", message = "비밀번호 형식이 올바르지 않음")
    //@Pattern(regexp = "[a-zA-Z0-9]+", message = "비밀번호 형식 오류")
    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\\$\\@\\$\\!\\%\\*\\#\\?\\&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$", message = "영문, 숫자 포함 8자 이상")
    //@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,20}", message = "비밀번호는 영문, 숫자, 특수문자 포함 8~16자입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$", message = "비밀번호는 영문,숫자,특수문자 포함 8자이상입니다.")
    private String password;
    @Size(min = 2, max = 10)
    private String username;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    private String useradd1;
    private String useradd2;
    private String useradd3;
    private Boolean enabled;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // 기본값 => OneToMany, ManyToMany: LAZY / OneToOne, ManyToOne: EAGER
    private List<Board> boards = new ArrayList<>();

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Cart> carts = new ArrayList<>();
}
