package base;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;


public class BaseClass 
{
	public static AndroidDriver<AndroidElement> driver;
	public static AppiumDriverLocalService service;
	 
	
	public AppiumDriverLocalService startServer()
	{
		//
	boolean flag=	checkIfServerIsRunnning(4723);
	if(!flag)
	{
		
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
	}
		return service;
		
	}
	
public static boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

public static void startEmulator() throws InterruptedException, IOException
{
	Runtime.getRuntime().exec("E:\\workspace\\appium\\ciber\\src\\main\\java\\resources\\emulator.bat");
	Thread.sleep(6000);
}
	public static AndroidDriver<AndroidElement>capabilities(String appName) throws IOException, InterruptedException
	{
		
		//System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\global.properties");
		Properties prop= new Properties();
		prop.load(fis);
		 File f= new File("src");
		 File fs= new File(f,(String) prop.get(appName));
		 
		
		DesiredCapabilities dc= new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		
		 String device=(String) prop.get("device");
		 if(device.contains("AndroidEmulator"))
		 {
			 startEmulator();
		 }
		
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		
		dc.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		return driver;
		
	}
	
	public  static void getScreenshot(String s) throws IOException
	{
		File screen= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen,new File(System.getProperty("user.dir")+"\\"+s+".png"));
	}

}
