package com.lambdatestNG;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestNG002 {

    private RemoteWebDriver driver;
    private String Status = "failed";

   @Parameters({"browser", "url"}) 
   @BeforeTest
    
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("browserVersion", "128");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };

        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
        driver.get("url");

    }

    @Test()
    public void basicTest() throws InterruptedException {
        
        System.out.println("Loading Url");
    		
    		driver.manage().window().maximize();
    		driver.findElement(By.xpath("//a[text()='Checkbox Demo']")).click();

    		  WebElement singleCheckbox = driver.findElement(By.id("isAgeSelected"));

    	        // Click the checkbox
    	        singleCheckbox.click();

    	        // Validate if it is selected
    	        Assert.assertTrue(singleCheckbox.isSelected(), "Checkbox should be selected!");

    	        // Click the checkbox again to unselect
    	        singleCheckbox.click();

    	        // Validate if it is unselected
    	        Assert.assertFalse(singleCheckbox.isSelected(), "Checkbox should be unselected!");

    }

    @AfterTest
    public void tearDown() {
                driver.quit();
    }

}