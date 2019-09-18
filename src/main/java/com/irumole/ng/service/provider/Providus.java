package com.irumole.ng.service.provider;

import com.irumole.ng.dao.BankLogin;
import com.irumole.ng.dto.Account;
import com.irumole.ng.dto.Balance;
import com.irumole.ng.dto.Transaction;
import com.irumole.ng.error.InternalErrorExecption;
import com.irumole.ng.service.BankOperation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class Providus extends com.irumole.ng.service.WebDriver implements BankOperation {

    private Logger logger = LoggerFactory.getLogger(Providus.class);


    @Override
    public Account getAccounts(BankLogin bankLogin) {
        try {
            Account account = new Account();
            BeanUtils.copyProperties(getBalance(bankLogin), account);
            return account;
        }catch (Exception e){
            logger.error("========getAccount(bankLogin) failed for Providus=======", e);
            throw new InternalErrorExecption("internal error - Providus getAccount(bankLogin)");
        }
    }

    @Override
    public Balance getBalance(BankLogin bankLogin) {
        Balance balance;
        try {
            WebDriver driver = getAccountSection(bankLogin);
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".acct-card div")));
            WebElement accountElement = driver.findElement(By.cssSelector(".acct-card div"));
            WebElement accountInfoElement = accountElement.findElement(By.cssSelector("div:nth-child(1)"));
            String accountName = getAccountName(accountInfoElement.getText());
            String accountNumber = getAccountNumber(accountInfoElement.getText());
            String accountType = accountInfoElement.findElement(By.cssSelector("small")).getText().toLowerCase().trim();

            WebElement accountBalanceElement = accountElement.findElement(By.cssSelector("div:nth-child(2)"));
            double accountBalance = Double.parseDouble(accountBalanceElement.findElement(By.cssSelector("strong")).getText().trim().substring(3).trim());
            balance = new Balance(accountName, accountNumber, accountBalance, accountType);
            logout(driver);
            logger.info("=====Providus Balances retrieved=======");
        } catch (Exception e) {
            logger.error("========getBalance(bankLogin) failed for Providus=======", e);
            throw new InternalErrorExecption("internal error - Providus getBalance(bankLogin)");
        }
        return balance;
    }

    @Override
    public List<Transaction> getTransactions(BankLogin bankLogin) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            WebDriver driver = getAccountSection(bankLogin);
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".acct-details")));
            driver.findElement(By.cssSelector(".acct-details a:nth-child(3)")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content form")));
            //set transaction start and end date - mm/dd/yyyy
            driver.findElement(By.id("Mini Statement.Date From")).sendKeys(formatDate(bankLogin.getFrom()));
            driver.findElement(By.id("Mini Statement.Date To")).sendKeys(formatDate(bankLogin.getTo()));
            driver.findElement(By.cssSelector("footer button")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Results")));
            List<WebElement> resultRows = driver.findElements(By.cssSelector("#Results tbody tr"));
            resultRows.forEach(row -> {
                Transaction transaction = new Transaction();
                List<WebElement> tds = row.findElements(By.tagName("td"));
                transaction.setTransactionDate(tds.get(0).getText().trim());
                transaction.setValueDate(tds.get(1).getText().trim());
                transaction.setDebit(tds.get(2).getText().trim());
                transaction.setCredit(tds.get(3).getText().trim());
                transaction.setNarration(tds.get(4).getText().trim());
                transaction.setTransactionReference(tds.get(5).getText().trim());
                transactions.add(transaction);
            });
            logout(driver);
            logger.info("========Providus transactions retrieved=======");
        } catch (Exception e) {
            logger.error("========getTransactions(bankLogin) failed for Providus=======", e);
            throw new InternalErrorExecption("internal error - Providus getTransactions(bankLogin)");
        }
        return transactions;
    }

    private WebDriver login(BankLogin bankLogin) {
        try {
            WebDriver driver = getDriver();
            driver.get(bankLogin.getUrl());
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
            driver.findElement(By.id("userName")).sendKeys(bankLogin.getUsername());
            driver.findElement(By.id("userAuth")).sendKeys(bankLogin.getPassword());
            driver.findElement(By.id("submit")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page #openAccount")));
            driver.findElement(By.id("openAccount")).click();
            return driver;
        } catch (Exception e) {
            logger.error("=====Providus login() failed=======", e);
            throw new InternalErrorExecption("internal error - Providus login()");
        }
    }

    private String formatDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return new SimpleDateFormat("MM/dd/yyyy").format(Date.valueOf(LocalDate.parse(date, formatter)));
    }

    private WebDriver getAccountSection(BankLogin bankLogin) {
        WebDriver driver = login(bankLogin);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dejavu-style")));
        driver.findElement(By.cssSelector(".dejavu-style a:nth-child(4)")).click();
        return driver;
    }

    private String getAccountName(String string) {
        int indexOfSlash = string.indexOf("/");
        if (indexOfSlash != -1)
            string = string.substring(0, indexOfSlash);
        return string;
    }

    private String getAccountNumber(String string) {
        //get start index of account number
        int startIndex = string.indexOf("/") + 1;
        return string.substring(startIndex, startIndex + 10);
    }

    private void logout(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            driver.findElement(By.className("logout")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".MessageBoxButtonSection #bot2-Msg1")));
            driver.findElement(By.cssSelector(".MessageBoxButtonSection #bot2-Msg1")).click();
            driver.close();
        } catch (Exception e) {
            logger.error("=====Providus logout() failed=======", e);
            throw new InternalErrorExecption("internal error - Providus logout()");
        }
    }
}
