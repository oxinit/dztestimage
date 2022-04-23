import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class Main {
    public static void main(String[] args) throws IOException {
        chromedriver().setup();//before chromeDriver musthave
        WebDriver iniDriver =new ChromeDriver();
 ;
        iniDriver.manage().window().maximize();
        iniDriver.get("https://www.google.com");//prestep
        iniDriver.findElement(By.name("q")).sendKeys("cheese"+Keys.ENTER);//action1
        iniDriver.findElement(By.xpath("//a[contains(@data-hveid,\"CAMQA\")]")).click();//action2
        if(iniDriver.findElement(By.xpath("//img[contains(@src,'jpeg')][contains(@alt,'Cheese Kiev')]")).isDisplayed()) {
            //  TakesScreenshot scrShot =((TakesScreenshot)iniDriver);step1
            //  File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);step2
            //  File destFile= new File("/c:testCheese.png");//step3
            //  FileUtils.copyFile(SrcFile, destFile);/step4
            File scrFile = ((TakesScreenshot) iniDriver).getScreenshotAs(OutputType.FILE);//step1+2
            FileUtils.copyFile(scrFile, new File("c:\\temp\\screenshot.png"));//step3+4 path cant be in c root needs folder
            iniDriver.close();//teardown
        }
        else throw new IOException("element nod displayed");
        iniDriver.close();
    }
}
