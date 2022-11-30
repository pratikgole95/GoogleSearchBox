package searchBox;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchMethods {
	
	
	
	WebDriver driver;
	
	@BeforeMethod
	public void Broswerlnch()
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions bw = new ChromeOptions();
		bw.addArguments("--incognito");
		driver = new ChromeDriver(bw);
		driver.get("https://www.google.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterMethod
	public void BrowserClose()
	{
		driver.close();
	}
	
	

	
	@Test
	public void VerifySearchBox()
	{		
			String ActualTitle=driver.getTitle();
			System.out.println(ActualTitle);
			String Expectedtitle="Google";
			Assert.assertEquals(ActualTitle, Expectedtitle);
	}
	
	@Test
	private void VerifyTrendingSerach() {
		
		WebElement search=driver.findElement(By.xpath("//*[@class='gLFyf']"));
		Actions act = new Actions(driver);
		act.doubleClick(search).perform();
		WebElement txt =driver.findElement(By.xpath("//*[text()='Trending searches']"));
		String ActualText=txt.getText();
		System.out.println(ActualText);
		Assert.assertEquals(ActualText, "Trending searches");
	}
	
	@Test
	public void VerifyIamFeelingButton()
	{
		driver.findElement(By.xpath("//*[@class='gLFyf']")).sendKeys("selenium");
		driver.findElement(By.xpath("//*[@class='gNO89b']")).click();
		WebElement url = driver.findElement(By.xpath("//*[text()='https://www.selenium.dev']"));
		String Actualurl=url.getText();
		Assert.assertEquals(Actualurl, "https://www.selenium.dev");
	}
	
	@Test
	public void VerifySerachFieldWithMinText()
	{
		driver.findElement(By.xpath("//*[@class='gLFyf']")).sendKeys("c");
		driver.findElement(By.xpath("(//*[@class=\"gNO89b\"])[1]")).click();
		WebElement name=driver.findElement(By.xpath("(//*[@class=\"LC20lb MBeuO DKV0Md\"])[1]"));
		String nm=name.getText();
		Assert.assertEquals(nm,"C (programming language) - Wikipedia");
	}
	
	@Test
	public void VerifySerachFieldWithTextSpecialCharNumber()
	{
		driver.findElement(By.xpath("//*[@class='gLFyf']")).sendKeys("Ss@3!$5^");
		List<WebElement> ls =driver.findElements(By.xpath("//*[contains(text(),'ss ')]"));
		String s=ls.get(2).getText();
		System.out.println(s);
		Assert.assertEquals(s, "ss 3/55");
	}
	
	@Test
	public void VerifySearchBoxWithDifferentLanguage() throws InterruptedException
	{

		WebElement lang=driver.findElement(By.xpath("//*[@id=\"SIvCob\"]/a[1]"));
		lang.click();
		Thread.sleep(10);
		boolean flag=driver.findElement(By.xpath("//*[@class='RNmpXc']")).isDisplayed();
		Assert.assertTrue(flag);
		
	}
	
	@Test
	public void verifySearchCount()
	{
		  driver.findElement(By.xpath("//*[@class='gLFyf']")).sendKeys("selenium");
		  List<WebElement>	ls=driver.findElements(By.xpath("//*[text()='selenium']"));
		  int ctsize=ls.size();
		  Assert.assertEquals(ctsize, 10);
	}
	
	@Test
	public void VerifyLogo()
	{
		boolean flag=driver.findElement(By.xpath("//*[@class='lnXdpd']")).isDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void verifyMostSuitableResult()
	{
	  driver.findElement(By.xpath("//*[@class='gLFyf']")).sendKeys("selenium");
	  List<WebElement>	ls=driver.findElements(By.xpath("//*[text()='selenium']"));
	  String expectedResult=ls.get(2).getText();
	  Assert.assertEquals(expectedResult, "selenium dev");

	  
	}
	
	@Test
	public void verifySearchbutton()
	{
		boolean btn=driver.findElement(By.xpath("(//*[@value='Google Search'])[2]")).isDisplayed();
		Assert.assertTrue(btn);
	}
	
	@Test
	public void verifyMisspelledKeyword()
	{
		driver.findElement(By.xpath("//*[@class='gLFyf']")).sendKeys("seleniu");
		driver.findElement(By.xpath("(//*[@class=\"gNO89b\"])[1]")).click();
		boolean result=driver.findElement(By.xpath("//*[@class='p64x9c card-section KDCVqf']")).isDisplayed();
		Assert.assertTrue(result);
	}
	
	@Test
	public void verifySearchFieldWithInvalidData()
	{
		driver.findElement(By.xpath("//*[@class='gLFyf']")).sendKeys("@#@#");
		driver.findElement(By.xpath("(//*[@class=\"gNO89b\"])[1]")).click();
		boolean rs=driver.findElement(By.xpath("//*[contains(text(),'Your search')]")).isDisplayed();
		Assert.assertTrue(rs);
	}
	
	
}
