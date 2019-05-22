package com.banking.automation.irumole.model;

import lombok.Data;

import java.util.List;

@Data
public final class User {

    private String lastName;
    private String otherName;
    private String bvn;
    private String email;
    private String phoneNumber;
    private String password;
    private List<UserBank> userBank;
}
