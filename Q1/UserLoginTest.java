import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class UserLoginTest {
	
	WebDriver driver = new ChromeDriver();
	@BeforeTest
	public void loadUserPage() {
		String url="http://localhost/employee/admin/users.php";
		driver.get(url);
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
	}
	
	@Test(priority=1)
	public void userLogin() {
		
		driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/form/div[1]/input")).sendKeys("admin");
		driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/form/div[2]/input")).sendKeys("admin123");	
		
		String inputUserName = driver.findElement(By.name("username")).getAttribute("value");
		String inputUserPassword = driver.findElement(By.name("password")).getAttribute("value");
		
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
	
		if(inputUserName.isEmpty() )
			System.out.println("Username is Empty!");
		else if(!inputUserName.equals("admin"))
			System.out.println("Wrong Username!");
		else if(inputUserPassword.isEmpty())
			System.out.println("Password is Empty!");
		else if(!inputUserPassword.equals("admin123"))
			System.out.println("Wrong Password!");
		else
			driver.findElement(By.xpath("//*[@id=\"login-frm\"]/button[1]")).click();
		
	}
	
	@Test(priority=2, dependsOnMethods = "userLogin")
	public void searchUser()
	{
		
		driver.findElement(By.xpath("//*[@id=\"Employee\"]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/label/input")).sendKeys("ab");
		List<WebElement> rows = driver.findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr"));
		
		if(rows.size()==1) {
			if(driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr/td")).getText().equals("No matching records found")) {
				System.out.println("data undetected");
			}
			else {
				System.out.println("data detected");
			}
		}
		else {
			System.out.println("data detected");
		}
		
	
	}
	
	@Test(priority=5, dependsOnMethods = "userLogin")
	public void deleteUser() {
		driver.findElement(By.xpath("//*[@id=\"Employee\"]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[1]/td[7]/center/button[2]/i")).click();
		
		driver.switchTo().alert().accept();
		
	}
	
	
	@Test(priority=3, dependsOnMethods = "userLogin")
	public void updateUser() {

		driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td[4]/center/button[1]/i")).click();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[1]/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[1]/input")).sendKeys("admin");
		driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[1]/div[2]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[1]/div[2]/input")).sendKeys("admin123");
		driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[3]/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[3]/input")).sendKeys("admin");
		driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[4]/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[4]/input")).sendKeys("admin");
		
		driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[2]/button")).click();
		
		driver.switchTo().alert().accept();
			
	}
	
	@Test(priority=4, dependsOnMethods = "userLogin")
	public void addNewUser() 
	{
		
		driver.findElement(By.xpath("//*[@id=\"new_user\"]")).click();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[1]/input")).sendKeys("admin");
		driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[1]/div[2]/input")).sendKeys("admin123");
		driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[3]/input")).sendKeys("admin");
		driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[4]/input")).sendKeys("admin");
		
		driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[2]/button")).click();
		driver.switchTo().alert().accept();
		
	}
	
	@AfterTest
	public void logoutUser()
	{
		System.out.println("logout");
	    driver.quit();
	}
}
