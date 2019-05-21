package com.banking.automation.autoBank.service;


import com.banking.automation.autoBank.dao.BankLogin;
import com.banking.automation.autoBank.dao.Service;
import lombok.Data;

@Data
@org.springframework.stereotype.Service
public class ServiceResolver {

	public String resolve(BankLogin bankLogin, Service requestedService) {

		bankLogin.setUrl(""); //retrieve URL from DB
		//List<Account> accounts = new ArrayList<Account>();
		String response = "Couldn't resolve bank";

		switch (bankLogin.getBankCode()) {
			case "044":{
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Access().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Access().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Access().getTransactions(bankLogin);
						break;
				}
			}
			break;
			case "023":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Citi().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Citi().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Citi().getTransactions(bankLogin);
						break;
				}
				break;
			case "063":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Diamond().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Diamond().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Diamond().getTransactions(bankLogin);
						break;
				}
				break;
			case "050":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Eco().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Eco().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Eco().getTransactions(bankLogin);
						break;
				}
				break;
			case "214":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Fcmb().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Fcmb().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Fcmb().getTransactions(bankLogin);
						break;
				}
				break;
			case "070":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Fidelity().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Fidelity().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Fidelity().getTransactions(bankLogin);
						break;
				}
				break;
			case "011":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new FirstBank().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new FirstBank().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new FirstBank().getTransactions(bankLogin);
						break;
				}
				break;
			case "058":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new GuaranteedTrust().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new GuaranteedTrust().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new GuaranteedTrust().getTransactions(bankLogin);
						break;
				}
				break;
			case "030":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Heritage().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Heritage().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Heritage().getTransactions(bankLogin);
						break;
				}
				break;
			case "301":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Jaiz().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Jaiz().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Jaiz().getTransactions(bankLogin);
						break;
				}
				break;
			case "082":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Keystone().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Keystone().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Keystone().getTransactions(bankLogin);
						break;
				}
				break;
			case "076":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Skye().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Skye().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Skye().getTransactions(bankLogin);
						break;
				}
				break;
			case "101":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Providus().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Providus().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Providus().getTransactions(bankLogin);
						break;
				}
				break;
			case "039":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Stanbic().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Stanbic().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Stanbic().getTransactions(bankLogin);
						break;
				}
				break;
			case "068":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new StandardChartered().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new StandardChartered().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new StandardChartered().getTransactions(bankLogin);
						break;
				}
				break;
			case "232":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Sterling().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Sterling().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Sterling().getTransactions(bankLogin);
						break;
				}
				break;
			case "033":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Uba().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Uba().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Uba().getTransactions(bankLogin);
						break;
				}
				break;
			case "032":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Union().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Union().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Union().getTransactions(bankLogin);
						break;
				}
				break;
			case "215":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Unity().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Unity().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Unity().getTransactions(bankLogin);
						break;
				}
				break;
			case "035":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Wema().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Wema().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Wema().getTransactions(bankLogin);
						break;
				}
				break;
			case "057":
				switch (requestedService){
					case GET_ACOOUNT:
						response = new Zenith().getAccounts(bankLogin);
						break;
					case GET_BALANCE:
						response = new Zenith().getBalance(bankLogin);
						break;
					case GET_TRANSACTIONS:
						response = new Zenith().getTransactions(bankLogin);
						break;
				}
				break;
			default:
				response = "Invalid Bank Code";
		}
		return response;
	}
}
