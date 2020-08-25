package com.spicejet;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DynamicDropDownTest {
	WebDriver driver;

	@BeforeTest
	@Parameters("browser")
	public void setupBrowser(String browser) throws InterruptedException {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"/Users/sherin/dev/eclipse-workspace/Spicejet/driver/chromedriver");
			driver = new ChromeDriver();
		}
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"/Users/sherin/dev/eclipse-workspace/Spicejet/driver/geckodriver");
			driver = new FirefoxDriver();

		}
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://www.spicejet.com");

	}

	@Test(priority = 1)
	public void dropDownTest() {

		WebElement orgin = driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));
		orgin.click();
		WebElement dest = driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_destinationStation1_CTXT']"));
		driver.findElement(By.xpath("//a[@value='BLR']")).click();

		//String s = orgin.getText();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String valueOrg = (String) js.executeScript("return arguments[0].value",orgin);
		
		System.out.println("Origin" + valueOrg);
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='BOM']"))
				.click();
		String valueDes = (String) js.executeScript("return arguments[0].value",dest);
		System.out.println("Destination" + valueDes);
		Assert.assertEquals(valueOrg, "Bengaluru (BLR)");
		Assert.assertEquals(valueDes, "Mumbai (BOM)");
	}

	@AfterTest
	public void teardown() {
		driver.close();
	}

}
