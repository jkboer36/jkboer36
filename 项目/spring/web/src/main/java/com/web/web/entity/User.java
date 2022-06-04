package com.web.web.entity;

import lombok.Data;

@Data
public class User {
    private Integer user_id;
    private String user_account;
    private String user_name;
    private String user_password;
    private Integer user_root;

}
