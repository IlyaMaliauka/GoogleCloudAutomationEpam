package com.epam.automation.page;

import com.epam.automation.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement firstFrame;

    @FindBy(id = "myFrame")
    private WebElement secondFrame;

    @FindBy(id = "input_60")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGPUsCheckbox;

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

    private void selectDataCenter(String region) {
        specifyOptionFromDropDownList(driver.findElement(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.location']")), region);
    }

    private void selectGPUType(String GPUType) {
        specifyOptionFromDropDownList(driver.findElement(By.xpath("//*[@id='select_value_label_371']")), GPUType);
    }

    private void selectMachineType(String machineType) {
        specifyOptionFromDropDownList(driver.findElement(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.instance']")), machineType);
    }

    private void selectLocalSSD(String localSSD) {
        specifyOptionFromDropDownList(driver.findElement(By.id("select_value_label_192")), localSSD);
    }

    private void specifyOptionFromDropDownList(WebElement dropDownList, String option) {
        waitVisibilityOf(dropDownList);
        clickThroughJS(dropDownList);

        String xpath = String.format("//md-select-menu//md-option//div[contains(text(), '%s')]", option);
        WebElement specifiedOption = driver.findElement(By.xpath(xpath));

        waitVisibilityOf(specifiedOption);
        clickThroughJS(specifiedOption);
    }

    private void waitVisibilityOf(WebElement webElement) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(webElement));
    }

    private void clickThroughJS(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }

    public FakeMailHomePage estimatePricing(User user) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        driver.switchTo().frame(firstFrame).switchTo().frame(secondFrame);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("input_60")));
        numberOfInstancesInput.sendKeys(user.getDesiredInstancesNumber());
       selectMachineType("n1-standard-8");

        addGPUsCheckbox.click();
        selectGPUType("NVIDIA Tesla T4");

        selectLocalSSD("2x375 GB");

        selectDataCenter("Frankfurt");
//        datacenterLocationSelect.click();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        switch (user.getRegion()) {
//            case "Europe":
//                selectEU3.click();
//                break;
//            case "United States":
//                selectUSWEST2.click();
//                break;
//            default:
//                selectEUWEST1.click();
//
//        }


        estimateButton.click();
        emailEstimateButton.click();
        logger.info("Google Cloud Service pricing calculated");

        return new FakeMailHomePage(driver);
    }
}
