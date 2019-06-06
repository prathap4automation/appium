package vokqa;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class OptionsList {
	static MobileDriver	driver;
	static WebDriverWait wait;
	public static void main(String[] args) throws IOException {
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "5e16cef7d630");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "6.0.1");
		dc.setCapability("appPackage", "com.vodqareactnative");
		dc.setCapability("appActivity", "com.vodqareactnative.MainActivity");
		
		// url
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//start appium
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"" );
		//assign driver
		while(1==1){
			try{
				driver = new AndroidDriver(u,dc);
				wait =new WebDriverWait(driver, 20);
				break;
			}
			catch(Exception e){}
		}
		//automation code
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']")));
			driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Samples List']")));
			//way-1
//			List<WebElement> rootElement = driver.findElements(By.xpath("//*[@class='android.widget.ScrollView']/*[@class='android.view.ViewGroup']/*[@class='android.view.ViewGroup']/*[@class='android.view.ViewGroup']"));
			//way-2
			List<WebElement> rootElement = driver.findElements(By.xpath("//android.widget.ScrollView[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[*]"));
			System.out.println("List size: "+rootElement.size());
			//click on double tap
			for(WebElement ele: rootElement){
				System.out.println(ele.findElement(By.xpath(".//android.widget.TextView")).getText());
				if(ele.findElement(By.xpath(".//android.widget.TextView")).getText().equals("Double Tap")){
					ele.click();
					break;
				}
			}
		}
		catch(Exception ex){ System.out.println(ex.getMessage()); }
		//close app
		driver.closeApp();
		//close appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
