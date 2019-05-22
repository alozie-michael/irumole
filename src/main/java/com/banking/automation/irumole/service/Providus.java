package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.banking.automation.irumole.dao.User;

@Service
public class Providus implements BankOperation {
	
	private Logger logger = LoggerFactory.getLogger(Providus.class);


	private WebDriver login(User user, String bankUrl) {
		System.setProperty("webdriver.chrome.driver", "");
		WebDriver driver = new ChromeDriver();
		/*try {
			driver.get(bankUrl);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username-input")));
			driver.findElement(By.id("username-input")).sendKeys(user.getUsername());
			driver.findElement(By.id("password-input")).sendKeys(user.getPassword());
			driver.findElement(By.id("sign-in-button")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nav-pills")));
		} catch (Exception e) {
			logger.error("=====login() failed=======");
			e.printStackTrace();
		}*/
		return driver;
	}

	@Override
	public String getBalance(BankLogin bankLogin) {
		/*List<Account> accounts = new ArrayList<Account>();
		try {
			logger.error("=====providus login=======");
			WebDriver driver = login(user, bankUrl);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nav-pills")));
			WebElement navBar = driver.findElement(By.cssSelector("ul li:nth-child(2)"));
			Thread.sleep(3000);
			navBar.findElement(By.tagName("a")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accounts-container")));
			List<WebElement> accountRows = driver.findElement(By.id("accounts-container")).findElements(By.cssSelector(".summary-box .row"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("box-account-balance")));
		
			//loop through all accounts
			for(WebElement accountRow: accountRows) {
				Double accountBalance = Double.parseDouble(accountRow.findElement(By.className("box-account-balance")).getText().substring(1).replace(",", ""));
				String accountName = accountRow.findElement(By.className("box-account-name")).getText();
				String accountNumber = accountRow.findElement(By.className("box-account-number")).getText();
				String accountType = accountRow.findElement(By.className("box-account-type")).getText();
				Account account = new Account(accountName, accountNumber, accountBalance, accountType);
				logger.info(account.toString());
				accounts.add(account);
			}
			driver.close();
			logger.error("=====Balances retrieved=======");
		}catch(Exception e) {
			logger.info("========getBalance(bankLogin) failed=======");
			e.printStackTrace();
		}*/
		return "Your Providus Bank account balances";
	}

	@Override
	public String getTransactions(BankLogin bankLogin){
		return "Your Providus Bank account transactions";
	}

	@Override
	public String getAccounts(BankLogin bankLogin){
		return "Your Providus Bank accounts";
	}
}
