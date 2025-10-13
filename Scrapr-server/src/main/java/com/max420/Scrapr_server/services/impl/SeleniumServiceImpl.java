package com.max420.Scrapr_server.services.impl;

import com.max420.Scrapr_server.services.SeleniumService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeleniumServiceImpl implements SeleniumService {
    @Override
    public List<String> scrapeSomeData() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://x.com/elonmusk");
        List<WebElement> titles = driver.findElements(By.className("css-1jxf684"));
        List<String> deserialized = titles.stream()
                .map(WebElement::getText)
                .filter(text -> !text.isBlank())
                .toList();

        driver.quit();
        return deserialized;
    }
}
