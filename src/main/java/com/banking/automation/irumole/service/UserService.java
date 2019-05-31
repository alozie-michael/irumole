package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.User;
import com.banking.automation.irumole.dao.UserBank;

public interface UserService {
    String signUp(User user);
    String addBank(String username, UserBank userBank);
    String removeBank(String username, String bankCode);
}
