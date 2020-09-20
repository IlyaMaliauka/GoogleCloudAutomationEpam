package com.epam.automation.page;

import com.epam.automation.model.User;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class FakeMailHomePage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement firstFrame;

    @FindBy(id = "myFrame")
    private WebElement secondFrame;

    @FindBy(xpath = "//*[@id='copy_address']/span")
    private WebElement copyEmailButton;

    @FindBy(xpath = "//input[@ng-model='emailQuote.user.email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@ng-model='emailQuote.user.firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@ng-model='emailQuote.user.lastname']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//button[@ng-disabled='emailForm.$invalid']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//*[text()='Google Cloud Platform Price Estimate']")
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
        jse.executeScript("window.open('https://10minutemail.com/');");

        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        copyEmailButton.click();
        driver.switchTo().window(tabs2.get(0));
        jse.executeScript("scroll(0, 1500);");
        driver.switchTo().frame(firstFrame).switchTo().frame(secondFrame);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@ng-model='emailQuote.user.email']")));
        emailInput.sendKeys(Keys.LEFT_CONTROL, "v");
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        sendEmailButton.click();

        driver.switchTo().window(tabs2.get(1));
        jse.executeScript("scroll(0, 500);");
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Google Cloud Platform Price Estimate']")));
        emailWithPricingButton.click();

        return this;
    }

    public String findEmailedPrice() {
        String emailResult = driver.findElement(By.xpath("//*[@id='mobilepadding']/td/h2")).getText();
        String[] emailElements = emailResult.split(" ");

        return emailElements[4];
    }

}
