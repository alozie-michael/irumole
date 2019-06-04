package com.irumole.ng.service;


import com.irumole.ng.Security.Security;
import com.irumole.ng.dao.BankLogin;
import com.irumole.ng.dao.Service;
import com.irumole.ng.model.User;
import com.irumole.ng.model.UserBank;
import com.irumole.ng.repository.UserRepository;
import com.irumole.ng.service.provider.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Data
@org.springframework.stereotype.Service
public class ServiceResolver {

    private Logger logger = LoggerFactory.getLogger(ServiceResolver.class);

    private final
    UserRepository userRepository;

    @Autowired
    public ServiceResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object resolve(String username, BankLogin login, Service requestedService) {
        Object response = "Couldn't resolve bank";
        BankLogin bankLogin = getUserLoginCredentials(username, login);

        switch (bankLogin.getBankCode()) {
            case "044": {
                BankService bankService = new BankService(new Access());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "023": {
                BankService bankService = new BankService(new Citi());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "063": {
                BankService bankService = new BankService(new Diamond());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "050": {
                BankService bankService = new BankService(new Eco());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "214": {
                BankService bankService = new BankService(new Fcmb());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "070": {
                BankService bankService = new BankService(new Fidelity());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "011": {
                BankService bankService = new BankService(new FirstBank());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "058": {
                BankService bankService = new BankService(new GuaranteedTrust());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "030": {
                BankService bankService = new BankService(new Heritage());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "301": {
                BankService bankService = new BankService(new Jaiz());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "082": {
                BankService bankService = new BankService(new Keystone());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "076": {
                BankService bankService = new BankService(new Skye());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "101": {
                BankService bankService = new BankService(new Providus());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "039": {
                BankService bankService = new BankService(new Stanbic());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "068": {
                BankService bankService = new BankService(new StandardChartered());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "232": {
                BankService bankService = new BankService(new Sterling());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "033": {
                BankService bankService = new BankService(new Uba());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "032": {
                BankService bankService = new BankService(new Union());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "215": {
                BankService bankService = new BankService(new Unity());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "035": {
                BankService bankService = new BankService(new Wema());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            case "057": {
                BankService bankService = new BankService(new Zenith());
                response = serviceResponse(bankLogin, requestedService, response, bankService);
            }
            break;
            default:
                response = "Invalid Bank Code";
        }
        return response;
    }

    private Object serviceResponse(BankLogin bankLogin, Service requestedService, Object response, BankService bankService) {
        switch (requestedService) {
            case GET_ACCOUNT:
                response = bankService.returnAccounts(bankLogin);
                break;
            case GET_BALANCE:
                response = bankService.returnBalance(bankLogin);
                break;
            case GET_TRANSACTIONS:
                response = bankService.returnTransactions(bankLogin);
                break;
        }
        return response;
    }

    private BankLogin getUserLoginCredentials(String username, BankLogin login) {
        Optional<User> user = userRepository.getUser(username);
        List<UserBank> userBanks = user.get().getUserBank();
        Optional<UserBank> accountDetails = userBanks.stream().filter(userBank -> userBank.getBank().getBankCode().equals(login.getBankCode())
        ).findFirst();
        //TODO throw bank not found error.
        login.setUsername(Security.decrypt(accountDetails.get().getUsername(), user.get().getBvn()));
        login.setPassword(Security.decrypt(accountDetails.get().getPassword(), user.get().getBvn()));
        login.setUrl(accountDetails.get().getBank().getUrl());
        login.setBankCode(accountDetails.get().getBank().getBankCode());
        return login;
    }
}
