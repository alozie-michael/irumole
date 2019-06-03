package com.irumole.service.provider;

import com.irumole.dao.BankLogin;
import com.irumole.dto.Account;
import com.irumole.dto.Balance;
import com.irumole.dto.Transaction;
import com.irumole.service.BankOperation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuaranteedTrust implements BankOperation {

    private Logger logger = LoggerFactory.getLogger(GuaranteedTrust.class);

    @Override
    public Balance getBalance(BankLogin bankLogin){
        WebDriver driver = login(bankLogin);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_ctl0_ItemsGrid")));
        List<WebElement> accountInfoRow = driver.findElements(By.cssSelector("#_ctl0_ItemsGrid tbody tr")).get(1).findElements(By.tagName("td"));
        Balance balance = new Balance();
        balance.setAccountNumber(accountInfoRow.get(0).getText().trim());
        balance.setAccountType(accountInfoRow.get(2).getText().trim());
        balance.setAccountBalance(Double.parseDouble(accountInfoRow.get(5).getText().replace(",", "").trim()));
        driver.findElement(By.id("Navbar1_lkFeedback")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_ctl0_Lblsubject")));
        balance.setAccountName(extractAccountName(driver.findElement(By.id("_ctl0_Lblsubject")).getText()));
        logout(driver);
        return balance;
    }

    @Override
    public List<Transaction> getTransactions(BankLogin bankLogin){
        List<Transaction> transactions = new ArrayList<>();
        WebDriver driver = login(bankLogin);
        driver.findElement(By.cssSelector("#__a div:nth-child(2)")).click();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_eo__ctl0_bdpStartDate_picker")));
        driver.findElement(By.id("_eo__ctl0_bdpStartDate_picker")).sendKeys(bankLogin.getFrom());
        driver.findElement(By.id("_eo__ctl0_bdpEndDate_picker")).sendKeys(bankLogin.getTo());
        driver.findElement(By.id("_ctl0_btnGo")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_ctl0_dgtrans")));
        List<WebElement> transactionRowsElement = driver.findElements(By.cssSelector("#_ctl0_dgtrans tr"));
        transactionRowsElement.stream().skip(1).forEach(transactionRowElement -> {
            Transaction transaction = new Transaction();
            List<WebElement> tds = transactionRowElement.findElements(By.tagName("td"));
            transaction.setTransactionDate(tds.get(0).getText().trim());
            transaction.setNarration(tds.get(2).getText().trim());
            transaction.setValueDate(tds.get(3).getText().trim());
            transaction.setDebit(tds.get(4).getText().trim());
            transaction.setCredit(tds.get(5).getText().trim());
            transaction.setTransactionReference(tds.get(8).getText().trim());
            transactions.add(transaction);
        });
        logout(driver);
        return transactions;
    }

    @Override
    public Account getAccounts(BankLogin bankLogin){
        Account account = new Account();
        BeanUtils.copyProperties(getBalance(bankLogin), account);
        return account;
    }

    private WebDriver login(BankLogin bankLogin){
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(bankLogin.getUrl());
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnClose")));
            driver.findElement(By.id("btnClose")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUserName")));
            driver.findElement(By.id("txtUserName")).sendKeys(bankLogin.getUsername());
            char[] password = bankLogin.getPassword().toCharArray();
            for(char output: password){
                driver.findElement(By.cssSelector("#keys input[value='" + output + "']")).click();
            }
            driver.findElement(By.id("Keypad1_btnOK")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginButton11")));
            driver.findElement(By.id("loginButton11")).click();
        }catch (Exception e){
            logger.error("=====GT Bank login() failed=======");
            e.printStackTrace();
        }
        return driver;
    }

    private void logout(WebDriver driver){
        driver.findElement(By.id("HorizontalMenu1_lbtnLogOff")).click();
        driver.close();
    }

    private String extractAccountName(String string){
        //extract account name from "Internet Banking - Customer Feed Back from [account name]- [account number]"
        return string.substring(42, string.indexOf("-", string.indexOf("-") + 1)).trim();
    }
}
