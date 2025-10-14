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
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver();
        driver.get("https://x.com/elonmusk");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // accept the cookies first
        WebElement cookieButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Refuse non-essential cookies')]")));
        cookieButton.click();
        // click the sign-in button
        WebElement signInButton = driver.findElement(By.xpath("//span[contains(text(), 'Zaloguj się')]"));
        signInButton.click();
        // log in using provided credentials

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='text']")));
        emailField.sendKeys(twitterEmail);
        WebElement proceed = driver.findElement(By.xpath("//span[contains(text(), 'Dalej')]"));
        proceed.click();
        // type in the password
        WebElement passwordField = driver.findElement(By.className("r-30o5oe"));
        passwordField.sendKeys(twitterPassword);
        WebElement signIn = driver.findElement(By.className("css-1jxf684"));
        signIn.click();

        List<WebElement> titles = driver.findElements(By.className("css-1jxf684"));
        List<String> deserialized = titles.stream()
                .map(WebElement::getText)
                .filter(text -> !text.isBlank())
                .toList();

        driver.quit();
        return deserialized;
    }
}
