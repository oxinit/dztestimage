import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class Main {
    public static void main(String[] args) throws IOException {
        chromedriver().setup();
        WebDriver iniDriver =new ChromeDriver();
        iniDriver.manage().window().maximize();
        iniDriver.get("https://www.google.com");
        iniDriver.findElement(By.name("q")).sendKeys("cheese"+Keys.ENTER);
        iniDriver.findElement(By.xpath("//a[contains(@data-hveid,\"CAMQA\")]")).click();
        if(iniDriver.findElement(By.xpath("//img[contains(@src,'jpeg')][contains(@alt,'Cheese Kiev')]")).isDisplayed()) {
            File scrFile = ((TakesScreenshot) iniDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("c:\\temp\\screenshot.png"));
            iniDriver.close();
        }
        else {iniDriver.close();
              throw new IOException("element nod displayed");
        }
    }
}
