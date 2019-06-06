package avd;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestOne {
	static MobileDriver	driver;
	static WebDriverWait wait;
	public static void main(String[] args) throws IOException {
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "4.2.2");
		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
//		dc.setCapability("avd", "4.2.2");
		
		//url
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='9']")));
			Thread.sleep(2000);
			System.out.println("success");
		}
		catch(Exception ex){ System.out.println(ex.getMessage()); }
		//close app
		driver.closeApp();
		//close appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
