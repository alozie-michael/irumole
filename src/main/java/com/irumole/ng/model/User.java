package com.irumole.ng.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public final class User {
    private final String roles = "USER";
    private ObjectId id;
    private String lastName;
    private String otherName;
    private String bvn;
    private String email;
    private String phoneNumber;
    private String password;
    private List<UserBank> userBank;
}
