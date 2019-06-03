package com.irumole.service;

import com.irumole.dao.User;
import com.irumole.dao.UserBank;

public interface UserService {
    String signUp(User user);
    String addBank(String username, UserBank userBank);
    String removeBank(String username, String bankCode);
}
