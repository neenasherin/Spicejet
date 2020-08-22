package com.spicejet;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		driver.findElement(By.xpath("//a[@value='BLR']")).click();

		String s = orgin.getText();
		System.out.println("fgff" + s);
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='BOM']"))
				.click();
		System.out.println(driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_destinationStation1_CTXT']"))
				.getText());
	}

	@AfterTest
	public void teardown() {
		driver.close();
	}

}
