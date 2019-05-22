package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.User;

public interface UserService {

    String signUp(User user);
    String addBank();
    String removeBank();
}
