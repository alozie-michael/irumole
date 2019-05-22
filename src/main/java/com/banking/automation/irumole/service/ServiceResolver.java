package com.banking.automation.irumole.service;


import com.banking.automation.irumole.dao.BankLogin;
import com.banking.automation.irumole.dao.Service;
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
			case "035":{
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

	private String serviceResponse(BankLogin bankLogin, Service requestedService, String response, BankService bankService) {
		switch (requestedService){
			case GET_ACOOUNT:
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
}
