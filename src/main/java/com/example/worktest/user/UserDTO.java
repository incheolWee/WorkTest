package com.example.worktest.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private String createDate;
    private String updateDate;
    private String loginTime;

}
