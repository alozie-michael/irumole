package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;
import com.banking.automation.irumole.dto.Account;
import com.banking.automation.irumole.dto.Balance;
import com.banking.automation.irumole.dto.Transaction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.banking.automation.irumole.dao.User;

import java.util.ArrayList;
import java.util.List;

public class Access implements BankOperation {
	
	private Logger logger = LoggerFactory.getLogger(Access.class);

	public WebDriver login(User user, String url) {
		System.setProperty("webdriver.chrome.driver", "");
		WebDriver driver = new ChromeDriver();
		/*try {
			driver.get(url);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
			driver.findElement(By.name("username")).sendKeys(user.getUsername());
			driver.findElement(By.name("password")).sendKeys(user.getPassword());
			Thread.sleep(2000);
			driver.findElement(By.className("login-btn")).click();
		} catch (Exception e) {
			logger.error("=====login() failed=======");
			e.printStackTrace();
		}*/
		return driver;
	}

	@Override
	public Balance getBalance(BankLogin bankLogin){
		/*List<Balance> accounts = new ArrayList<Balance>();
		try {
			logger.info("=======Access bank login========");
			WebDriver driver = login(user, url);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tile")));
			driver.findElement(By.cssSelector(".wrapper-content .tile-one")).click();
			
			List<WebElement> accountRows = driver.findElements(By.className("odd"));

			for (WebElement accountRow : accountRows) {				
				String accountName = accountRow.findElement(By.tagName("span")).getText().trim();
				String accountNumber = accountRow.findElement(By.cssSelector("td:nth-child(2)")).getText().trim();
				String accountType =  accountRow.findElement(By.cssSelector("td:nth-child(4)")).getText().trim();
				double accountBalance = Double.parseDouble(accountRow.findElements(By.className("success")).get(1).getText().trim().replace(",", "").substring(1));
				Balance account = new Balance(accountName, accountNumber, accountBalance, accountType);
				logger.info(account.toString());
				accounts.add(account);
			}
			driver.quit();
			logger.info("========balance retrieved=======");
		} catch (Exception e) {
			logger.error("=====getBalance(bankLogin) failed========");
			e.printStackTrace();
		}*/
		return new Balance();
	}

	@Override
	public List<Transaction> getTransactions(BankLogin bankLogin){
		return new ArrayList<>();
	}

	@Override
	public Account getAccounts(BankLogin bankLogin){
		return new Account();
	}


	private WebDriver login() {
		return new ChromeDriver();
	}

	private void logout(WebDriver driver) {
	}


}
