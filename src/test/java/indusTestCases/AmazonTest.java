package indusTestCases;


	
	
	
	import java.io.FileInputStream;
	import java.util.List;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;



	public class AmazonTest {
	WebDriver driver;
	public  Properties prop;
	public  WebDriverWait wait ;

	By signInLink = By.xpath("//a/span[contains(text(),'Sign in')]");
	By emilId = By.id("ap_email");
	By continueButton = By.id("continue");
	By passwordField = By.id("ap_password");
	By loginButton = By.id("signInSubmit");
	By searchBox = By.id("twotabsearchtextbox");
	By searchButton = By.xpath("//input[@value='Go' and @type='submit']");


	@BeforeMethod
	public void setup() {

	prop = new Properties(); // For 

	try {
	FileInputStream ip = new FileInputStream("/Users/gauravsingh/Documents/workspace_gaurav/IndusAssignmentTest/"
	+ "/src/main/java/com/qa/indusAutomation/indusconfig.properties");// To establish the connection between 
	//class and config file
	prop.load(ip);//Read config File
	} catch (Exception e) {

	e.printStackTrace();
	} 

	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get(prop.getProperty("url"));

	}



	@Test
	public void loginAmazon() {
	driver.findElement(signInLink).click();
	driver.findElement(emilId).sendKeys(prop.getProperty("username"));
	driver.findElement(continueButton).click();
	driver.findElement(passwordField).sendKeys(prop.getProperty("password"));
	driver.findElement(loginButton).click();


	}

	@Test
	public void searchProduct() {
	driver.findElement(searchBox).sendKeys("Redmi Note");
	driver.findElement(searchButton).click();
	Boolean bProductFound = false;
	while (bProductFound == false) {
	List<WebElement> productList = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
	System.out.println(productList.size());
	for(int i = 0 ; i<productList.size();i++) {
	System.out.println(productList.get(i).getText());
	if (productList.get(i).getText().equalsIgnoreCase("Redmi Y2 (Rose Gold, 3GB RAM, 32GB Storage)")) {
	bProductFound = true;
	productList.get(i).click();
	break;
	}
	}
	WebElement NextButton = driver.findElement(By.xpath("//li[@class='a-last']/a"));
	NextButton.click();
	}

	}



	@AfterMethod
	public void tearDown() {
	driver.quit();

	}
	







	}
	
	


