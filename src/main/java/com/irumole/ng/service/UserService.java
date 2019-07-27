package com.irumole.ng.service;

import com.irumole.ng.dao.User;
import com.irumole.ng.dao.UserBank;
import com.irumole.ng.dto.Bank;

import java.util.List;

public interface UserService {
    String signUp(User user);
    List<Bank> getBanks(String username);
    String addBank(String username, UserBank userBank);
    String removeBank(String username, String bankCode);
    String deleteUser(String username);
}
