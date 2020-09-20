package com.epam.automation.page;

import com.epam.automation.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PricingCalculatorPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement firstFrame;

    @FindBy(id = "myFrame")
    private WebElement secondFrame;

    @FindBy(id = "input_60")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.instance']")
    private WebElement machineTypeSelectButton;

    @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement standart8option;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGPUsCheckbox;

    @FindBy(xpath = "//*[@id='select_value_label_371']")
    private WebElement selectGPUType;

    @FindBy(xpath = "//*[@id='select_option_385']")
    private WebElement selectTESLAT4;

    @FindBy(xpath = "//*[@id='select_value_label_192']")
    private WebElement selectSSD;

    @FindBy(xpath = "//*[@id='select_option_257']")
    private WebElement select2x375GB;

    @FindBy(xpath = "//*[@id='select_value_label_59']")
    private WebElement committedUsageSelect;

    @FindBy(xpath = "//*[@id='select_option_93']")
    private WebElement selectOneYear;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.location']")
    private WebElement datacenterLocationSelect;

    @FindBy(xpath = "//*[@id='select_option_204']")
    private WebElement selectEU3;

    @FindBy(xpath = "//*[@id='select_option_199']")
    private WebElement selectUSWEST2;

    @FindBy(xpath = "//*[@id='select_option_202']")
    private WebElement selectEUWEST1;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement estimateButton;

    @FindBy(id = "email_quote")
    private WebElement emailEstimateButton;


    public PricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FakeMailHomePage estimatePricing(User user) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        driver.switchTo().frame(firstFrame).switchTo().frame(secondFrame);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("input_60")));
        numberOfInstancesInput.sendKeys(user.getDesiredInstancesNumber());
        // jse.executeScript("scroll(0, 1050);");
        machineTypeSelectButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        standart8option.click();

        addGPUsCheckbox.click();
        selectGPUType.click();
        selectTESLAT4.click();

        selectSSD.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        select2x375GB.click();

        datacenterLocationSelect.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (user.getRegion()) {
            case "Europe":
                selectEU3.click();
                break;
            case "United States":
                selectUSWEST2.click();
                break;
            default:
                selectEUWEST1.click();

        }

        // jse.executeScript("scroll(0, 5000);");
        committedUsageSelect.click();
        selectOneYear.click();

        estimateButton.click();
        emailEstimateButton.click();

        return new FakeMailHomePage(driver);
    }
}
