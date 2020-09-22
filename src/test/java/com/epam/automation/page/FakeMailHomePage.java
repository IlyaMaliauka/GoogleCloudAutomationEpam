package com.epam.automation.page;

import com.epam.automation.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class FakeMailHomePage {

    private WebDriver driver;
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement firstFrame;

    @FindBy(id = "myFrame")
    private WebElement secondFrame;

    @FindBy(xpath = "//div[@class='input-box-col hidden-xs-sm']")
    private WebElement copyEmailButton;

    @FindBy(xpath = "//input[@ng-model='emailQuote.user.email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@ng-model='emailQuote.user.firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@ng-model='emailQuote.user.lastname']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//button[@ng-disabled='emailForm.$invalid']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//a[text()='Google Cloud Platform Price Estimate']")
    private WebElement emailWithPricingButton;

    public FakeMailHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String findGoogleCloudCalculatorPrice() {
        String calculatorPrice = driver.findElement(By.xpath("//b[@class='ng-binding']")).getText();
        String[] elements = calculatorPrice.split(" ");

        return elements[4];
    }

    public FakeMailHomePage sendPricingOnFakeEmail(User user) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open('https://10minemail.com/');");

        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        copyEmailButton.click();
        driver.switchTo().window(tabs2.get(0));
        driver.switchTo().frame(firstFrame).switchTo().frame(secondFrame);


        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@ng-model='emailQuote.user.email']")));
        emailInput.sendKeys(Keys.LEFT_CONTROL, "v");
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        sendEmailButton.click();
        logger.info("Email with pricing sent to 10-minute mail");

        driver.switchTo().window(tabs2.get(1));
        jse.executeScript("window.scrollBy(0,450)");
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Google Cloud Platform Price Estimate']")));
        emailWithPricingButton.click();
        jse.executeScript("window.scrollBy(0,350)");

        return this;
    }

    public String findEmailedPrice() {
        String emailResult = driver.findElement(By.xpath("//*[@id='mobilepadding']/td/h2")).getText();
        String[] emailElements = emailResult.split(" ");

        return emailElements[4];
    }

}
