import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void checkLogin() {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com");
        browser.findElement(By.xpath("//*[@placeholder='Username']")).sendKeys("standard_user");
        browser.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("secret_sauce");
    }
}
