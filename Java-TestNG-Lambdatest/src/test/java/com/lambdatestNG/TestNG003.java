package com.lambdatestNG;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestNG003 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeTest
    @Parameters({"browser", "url"})
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "MacOS Catalina");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        String[] Tags = new String[] { "Feature", "Tag", "Moderate" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
        driver.get("url");
    }

    @Test
    public void basicTest() throws InterruptedException {
        System.out.println("Loading Url");
		driver.manage().window().maximize();
	driver.findElement(By.linkText("Javascript Alerts")).click();	
Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
 String expectedText = "I am an alert box!";
        Assert.assertEquals(alertText, expectedText, "Alert message mismatch!");
System.out.println("Alert message validated successfully!");        alert.accept();		
		
		driver.findElement(By.xpath("//button[text()='Click Me']")).click();

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}