package com.epam.automation.test;

import com.epam.automation.page.FakeMailHomePage;
import com.epam.automation.page.GoogleCloudHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleCloudPricingCalculatorTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test()
    public void testCalculatedPricingEqualsEmailedPricing() {
        FakeMailHomePage getPricing = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForCalculator("pricing calculator")
                .estimatePricing();
        String calculatedPrice = getPricing.findGoogleCloudCalculatorPrice();
        getPricing.sendPricingOnFakeEmail();
        String emailedPrice = getPricing.findEmailedPrice();

        Assert.assertEquals(calculatedPrice, emailedPrice);
    }


//        @AfterMethod(alwaysRun = true)
//    public void browserTearDown() {
//            driver.quit();
//            driver = null;
//        }

}
