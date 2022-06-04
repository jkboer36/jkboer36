package com.example.springboot.entity;

import lombok.Data;

@Data
public class user {
    private int user_id;
    private String user_account;
    private String user_name;
    private String user_password;
    private int user_root;
}
