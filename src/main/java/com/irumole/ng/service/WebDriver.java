package com.irumole.ng.service;

import org.apache.commons.exec.OS;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WebDriver {

    private Logger logger = LoggerFactory.getLogger(WebDriver.class);

   /* protected org.openqa.selenium.WebDriver getDriver(){

        String webDriverUrl = "http://localhost:4444/wd/hub";

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        return new RemoteWebDriver(getUrl(webDriverUrl), options);
    }

    private URL getUrl(String url){
        URL newUrl = null;
        try {
            logger.info("connecting to remote webdriver on {}", url);
            newUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return newUrl;
    }*/

    protected org.openqa.selenium.WebDriver getDriver(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1400,800");
        options.addArguments("--headless");

        if(OS.isFamilyMac()){
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }

        if(OS.isFamilyUnix()){
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
        }

        return new ChromeDriver(options);
    }
}
