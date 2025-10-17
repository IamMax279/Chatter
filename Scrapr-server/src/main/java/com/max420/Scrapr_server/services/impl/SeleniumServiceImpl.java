package com.max420.Scrapr_server.services.impl;

import com.max420.Scrapr_server.services.SeleniumService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class SeleniumServiceImpl implements SeleniumService {
    @Value("${twitter.email}")
    private String twitterEmail;
    @Value("${twitter.password}")
    private String twitterPassword;

    @Override
    public List<String> scrapeSomeData() {
        ChromeOptions options = new ChromeOptions();
//      options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://crypto.com/en/price");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cookieButton = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(text(), 'Reject non-essential cookies ')]")));
        cookieButton.click();

        System.out.println("Przeglądarka pozostaje otwarta. Naciśnij Enter, aby zakończyć...");
        new java.util.Scanner(System.in).nextLine();

        driver.quit();
        return List.of();
    }
}
