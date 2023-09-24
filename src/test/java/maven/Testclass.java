package maven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testclass {
	
	WebDriver driver;
	private ExtentReports extent;
    private ExtentTest test;

	
//	private By email = By.id("email");
//	private By password = By.id("passwd");
//	private By loginbtn = By.id("SubmitLogin");
    
    @BeforeSuite
    public void setUp() {
    	ExtentSparkReporter spark = new ExtentSparkReporter("test-output/Spark.html");
        //ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }
//	
	@BeforeClass
	public void setup() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\reshm\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-extensions");
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-software-rasterizer");
		options.addArguments("--disable-web-security");
		options.addArguments("--disable-features=IsolateOrigins,site-per-process");
		options.addArguments("--window-size=1920x1080");
		driver = new ChromeDriver(options);
		driver.get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");

	}
	
	@Test(priority=0)
	public void testlogin() {
		
		ExtentTest test = extent.createTest("Your Test Name");
		driver.findElement(By.id("email")).sendKeys("reshmarajarjun1995@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("Angel@9571");
		driver.findElement(By.id("SubmitLogin")).click();
		String actualtitle = driver.getTitle();
		Assert.assertEquals(actualtitle, "My account - My Shop");
		test.log(Status.PASS, "Test Passed");
	}
	
	@Test(priority=1)
	public void testlabel() throws InterruptedException {
		
		ExtentTest test = extent.createTest("Your Test Name");
		Thread.sleep(5000);
		String actuallabel = driver.findElement(By.xpath("//span[text()='Reshma Shenoy']")).getText();
		Assert.assertEquals(actuallabel, "Reshma Shenoy");
		test.log(Status.PASS, "Test Passed");
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
		driver.quit();
		extent.flush();
	}

}
