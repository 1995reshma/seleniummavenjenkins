package maven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testclass {
	
	WebDriver driver;
	
//	private By email = By.id("email");
//	private By password = By.id("passwd");
//	private By loginbtn = By.id("SubmitLogin");
//	
	@BeforeClass
	public void setup() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\reshm\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");

	}
	
	@Test(priority=0)
	public void testlogin() {
		driver.findElement(By.id("email")).sendKeys("reshmarajarjun1995@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("Angel@9571");
		driver.findElement(By.id("SubmitLogin")).click();
		String actualtitle = driver.getTitle();
		Assert.assertEquals(actualtitle, "My account - My Shop");
	}
	
	@Test(priority=1)
	public void testlabel() throws InterruptedException {
		
		Thread.sleep(5000);
		String actuallabel = driver.findElement(By.xpath("//span[text()='Reshma Shenoy']")).getText();
		Assert.assertEquals(actuallabel, "Reshma Shenoy");
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
		driver.quit();
	}

}
