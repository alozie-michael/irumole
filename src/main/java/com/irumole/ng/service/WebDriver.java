package com.irumole.ng.service;

import org.apache.commons.exec.OS;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebDriver {

    @Value("${irumole.chrome.driver.path}")
    private String chromeDriverPath;
    private Logger logger = LoggerFactory.getLogger(WebDriver.class);

    protected org.openqa.selenium.WebDriver getDriver(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1400,800");
        options.addArguments("--headless");

        if(OS.isFamilyMac()){
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }

        if(OS.isFamilyWindows()){
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }

        /*if(OS.isFamilyUnix()){
            try{
                //GOOGLE_CHROME_SHIM GOOGLE_CHROME_BIN
                String binaryPath=EnvironmentUtils.getProcEnvironment().get("GOOGLE_CHROME_SHIM");
                logger.info("Path: {}", binaryPath);
                options.setBinary(binaryPath);
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
            }catch(Exception e){
                e.printStackTrace();
            }
        }*/

        return new ChromeDriver(options);
    }
}
