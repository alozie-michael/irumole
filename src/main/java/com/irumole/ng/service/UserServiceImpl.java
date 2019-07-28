package com.irumole.ng.service;

import com.irumole.ng.Security.Security;
import com.irumole.ng.dao.User;
import com.irumole.ng.dao.UserBank;
import com.irumole.ng.error.BankNotFoundExecption;
import com.irumole.ng.error.CustomerNotFoundException;
import com.irumole.ng.error.ExistingCustomerException;
import com.irumole.ng.model.Bank;
import com.irumole.ng.repository.BankRepository;
import com.irumole.ng.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BankRepository bankRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BankRepository bankRepository) {
        this.userRepository = userRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public String signUp(User user) {
        Optional<com.irumole.ng.model.User> existingUser = userRepository.getUser(user.getEmail());
        if (existingUser.isPresent())
            throw new ExistingCustomerException(user.getEmail());

        com.irumole.ng.model.User newUser = new com.irumole.ng.model.User();
        //Encrypt user password with BCrypt
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        BeanUtils.copyProperties(user, newUser);
        userRepository.insertUser(newUser);
        return "success";
    }

    @Override
    public String addBank(String username, UserBank userBank) {
        Optional<com.irumole.ng.model.User> user = userRepository.getUser(username);
        if (!user.isPresent())
            throw new CustomerNotFoundException(username);

        List<com.irumole.ng.model.UserBank> userBanks = user.get().getUserBank();
        if (userBanks == null) {
            userBanks = new ArrayList<>();
        }
        //Retrieve bank from database using bankCode
        Optional<Bank> bank = bankRepository.getBank(userBank.getBankCode());
        if (!bank.isPresent())
            throw new BankNotFoundExecption(username);

        com.irumole.ng.model.UserBank newUserBank = new com.irumole.ng.model.UserBank();
        /*
         * Encrypt username & password with AES.
         * User BVN is the salt value
         * */
        newUserBank.setUsername(Security.encrypt(userBank.getUsername(), user.get().getBvn()));
        newUserBank.setPassword(Security.encrypt(userBank.getPassword(), user.get().getBvn()));
        newUserBank.setBank(bank.get());
        userBanks.add(newUserBank);
        userRepository.updateUser(username, "userBank", userBanks);
        return "success";
    }

    @Override
    public String removeBank(String username, String bankCode) {
        Optional<com.irumole.ng.model.User> user = userRepository.getUser(username);
        if (!user.isPresent())
            throw new CustomerNotFoundException(username);

        List<com.irumole.ng.model.UserBank> userBanks = user.get().getUserBank();

        if(userBanks == null)
            throw new BankNotFoundExecption(username);

        List<com.irumole.ng.model.UserBank> newUserBank = userBanks.stream().filter(userBank -> !userBank.getBank().getBankCode().equals(bankCode)).collect(Collectors.toList());
        userRepository.updateUser(username, "userBank", newUserBank);
        return "success";
    }

    @Override
    public String deleteUser(String username) {
        Optional<com.irumole.ng.model.User> user = userRepository.getUser(username);
        if (!user.isPresent())
            throw new CustomerNotFoundException(username);

        userRepository.deleteUser(user.get());
        return "success";
    }

    @Override
    public List<com.irumole.ng.dto.Bank> getBanks(String username) {
        Optional<com.irumole.ng.model.User> user = userRepository.getUser(username);
        if(!user.isPresent())
            throw new CustomerNotFoundException(username);

        List<com.irumole.ng.dto.Bank> banks = new ArrayList<>();

        //Retrieve user bank
        List<com.irumole.ng.model.UserBank> userBanks = user.get().getUserBank();
        if(userBanks == null)
            throw new BankNotFoundExecption(username);

        userBanks.forEach(userBank -> {
            com.irumole.ng.dto.Bank bank = new com.irumole.ng.dto.Bank();
            BeanUtils.copyProperties(userBank.getBank(), bank);
            banks.add(bank);
        });
        return banks;
        }
    }
