package com.banking.automation.autoBank.service;

import java.util.ArrayList;
import java.util.List;

import com.banking.automation.autoBank.dao.BankLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.banking.automation.autoBank.dao.User;
import com.banking.automation.autoBank.dto.Account;

@Service
public class Access {
	
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
	
	String getBalance(BankLogin bankLogin){
		/*List<Account> accounts = new ArrayList<Account>();
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
				Account account = new Account(accountName, accountNumber, accountBalance, accountType);
				logger.info(account.toString());
				accounts.add(account);
			}
			driver.quit();
			logger.info("========balance retrieved=======");
		} catch (Exception e) {
			logger.error("=====getBalance(bankLogin) failed========");
			e.printStackTrace();
		}*/
		return "Your Access Bank account balances";
	}

	public String getTransactions(BankLogin bankLogin){
		return "Your Access Bank account transactions";
	}

	public String getAccounts(BankLogin bankLogin){
		return "Your Access Bank accounts";
	}
}
