package com.epam.automation.test;

import com.epam.automation.driver.DriverSingleton;
import com.epam.automation.model.User;
import com.epam.automation.page.FakeMailHomePage;
import com.epam.automation.page.GoogleCloudHomePage;
import com.epam.automation.service.UserCreator;
import com.epam.automation.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class GoogleCloudPricingCalculatorTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {

        driver = DriverSingleton.getDriver();

    }

    @Test()
    public void testCalculatedPricingEqualsEmailedPricing() {
        User testUser = UserCreator.withCredentialsFromProperty();
        FakeMailHomePage getPricing = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForCalculator("pricing calculator")
                .estimatePricing(testUser);
        String calculatedPrice = getPricing.findGoogleCloudCalculatorPrice();
        getPricing.sendPricingOnFakeEmail(testUser);
        String emailedPrice = getPricing.findEmailedPrice();

        Assert.assertEquals(calculatedPrice, emailedPrice);
    }


        @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
            DriverSingleton.closeDriver();
        }

}
