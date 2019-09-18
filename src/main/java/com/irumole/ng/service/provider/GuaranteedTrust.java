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
public class GuaranteedTrust extends com.irumole.ng.service.WebDriver implements BankOperation {

    private Logger logger = LoggerFactory.getLogger(GuaranteedTrust.class);

    @Override
    public Balance getBalance(BankLogin bankLogin){
        try {
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
        }catch (Exception e) {
            logger.error("=====GT Bank getBalance() failed=======", e);
            throw new InternalErrorExecption("internal error - GT Bank getBalance()");
        }
    }

    @Override
    public List<Transaction> getTransactions(BankLogin bankLogin){
        try {
            List<Transaction> transactions = new ArrayList<>();
            WebDriver driver = login(bankLogin);
            driver.findElement(By.cssSelector("#__a div:nth-child(2)")).click();
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_eo__ctl0_bdpStartDate_picker")));
            //set transaction start and end date - mm/dd/yyyy
            driver.findElement(By.id("_eo__ctl0_bdpStartDate_picker")).sendKeys(formatDate(bankLogin.getFrom()));
            driver.findElement(By.id("_eo__ctl0_bdpEndDate_picker")).sendKeys(formatDate(bankLogin.getTo()));
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
        }catch (Exception e) {
            logger.error("=====GT Bank getTransactions() failed=======", e);
            throw new InternalErrorExecption("internal error - GT Bank getTransactions()");
        }
    }

    @Override
    public Account getAccounts(BankLogin bankLogin){
        try {
            Account account = new Account();
            BeanUtils.copyProperties(getBalance(bankLogin), account);
            return account;
        }catch (Exception e) {
            logger.error("=====GT Bank getAccounts() failed=======", e);
            throw new InternalErrorExecption("internal error - GT Bank getAccounts()");
        }
    }

    private WebDriver login(BankLogin bankLogin){
        try {
            WebDriver driver = getDriver();
            driver.get(bankLogin.getUrl());
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnClose")));
            driver.findElement(By.id("btnClose")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUserName")));
            driver.findElement(By.id("txtUserName")).sendKeys(bankLogin.getUsername());
            char[] password = bankLogin.getPassword().toCharArray();
            for (char output : password) {
                driver.findElement(By.cssSelector("#keys input[value='" + output + "']")).click();
            }
            driver.findElement(By.id("Keypad1_btnOK")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginButton11")));
            driver.findElement(By.id("loginButton11")).click();
            return driver;
        } catch (Exception e) {
            logger.error("=====GT Bank login() failed=======", e);
            throw new InternalErrorExecption("internal error - GT Bank login()");
        }
    }

    private void logout(WebDriver driver) {
        try {
            driver.findElement(By.id("HorizontalMenu1_lbtnLogOff")).click();
            driver.close();
        }catch (Exception e){
            logger.error("=====GT Bank logout() failed=======", e);
            throw new InternalErrorExecption("internal error - GT Bank logout()");
        }
    }

    private String extractAccountName(String string) {
        //extract account name from "Internet Banking - Customer Feed Back from [account name]- [account number]"
        return string.substring(42, string.indexOf("-", string.indexOf("-") + 1)).trim();
    }

    private String formatDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.parse(date, formatter)));
    }
}
