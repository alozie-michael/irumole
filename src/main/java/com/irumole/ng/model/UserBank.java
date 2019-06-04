package com.irumole.ng.model;

import lombok.Data;

@Data
public final class UserBank {
    private Bank bank;
    private String username;
    private String password;
}
