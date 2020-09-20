package com.epam.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage {

    private static String HOMEPAGE_URL = "https://cloud.google.com/ ";
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='devsite-searchbox']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@aria-label='Search box']")
    private WebElement searchInput;

    @FindBy(xpath = "//a[@data-ctorig='https://cloud.google.com/products/calculator']")
    private WebElement calculatorSearchResult;

    public GoogleCloudHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public PricingCalculatorPage searchForCalculator(String searchText) {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='devsite-searchbox']")));
        searchButton.click();
        searchInput.sendKeys(searchText);
        searchInput.sendKeys(Keys.ENTER);
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-ctorig='https://cloud.google.com/products/calculator']")));
        calculatorSearchResult.click();
        return new PricingCalculatorPage(driver);
    }

}
