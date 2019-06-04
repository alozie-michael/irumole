package com.irumole.ng.service;

import com.irumole.ng.dao.User;
import com.irumole.ng.dao.UserBank;

public interface UserService {
    String signUp(User user);

    String addBank(String username, UserBank userBank);

    String removeBank(String username, String bankCode);
}
