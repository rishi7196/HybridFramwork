package testpages;


import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class VerifyToastMessage extends BaseClass
{
	@Test
	public void VerifyToast() throws IOException, InterruptedException {
		
		service=startServer();
		
		AndroidDriver<AndroidElement>driver=capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Actual Script
		
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("shuv");
		//driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		
		//use scroaable
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Australia']")).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		
		String toastMessage=driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		
		System.out.println(toastMessage);
		
		Assert.assertEquals("Please enter your name", toastMessage);
		
		service.stop();
		
		
		
	}

	   @BeforeTest
	   public void killAllnode() throws InterruptedException, IOException
	   {
		   Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		   Thread.sleep(3000);
		   
	   }

}
