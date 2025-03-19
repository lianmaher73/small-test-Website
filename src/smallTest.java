import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class smallTest {
	WebDriver driver = new ChromeDriver();
	SoftAssert myAssert = new SoftAssert();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@BeforeTest
	public void befor() {
		driver.get("https://practicetestautomation.com/practice-test-login/");
		driver.manage().window().maximize();

	}

	@Test(priority = 2)
	public void testLogin() {
		WebElement userName = driver.findElement(By.id("username"));
		userName.click();
		userName.sendKeys("student");
		WebElement password = driver.findElement(By.id("password"));
		password.click();
		password.sendKeys("Password123");
		driver.findElement(By.id("submit")).click();
		String urlString = driver.getCurrentUrl();
		myAssert.assertTrue(urlString.contains("logged-in-successfully"));
		myAssert.assertAll();

	}

	@Test(priority = 1)
	public void testLoginwithnonvalid() {
		driver.get("https://practicetestautomation.com/practice-test-login/");
		WebElement userName = driver.findElement(By.id("username"));
		userName.click();
		userName.sendKeys("student1");
		WebElement password = driver.findElement(By.id("password"));
		password.click();
		password.sendKeys("Password1234");
		driver.findElement(By.id("submit")).click();
		myAssert.assertTrue(driver.findElement(By.id("error")).isDisplayed());
		myAssert.assertAll();

	}

	@Test(priority = 3)
	public void practice() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Practice']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Test Exceptions']")).click();
		driver.findElement(By.id("add_btn")).click();
		WebElement confirmationMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
		System.out.println(driver.findElement(By.id("confirmation")).getText());
		myAssert.assertTrue(confirmationMessage.isDisplayed(), "the message doesn't Apear successfully");
		myAssert.assertAll();
	}

	@AfterTest
	public void after() {
		if (driver != null) {
			driver.quit();
		}
	}

}
