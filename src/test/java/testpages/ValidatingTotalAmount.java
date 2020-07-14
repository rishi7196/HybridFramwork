package testpages;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import utlize.TestData;
import utlize.Utilities;

public class ValidatingTotalAmount extends BaseClass
{
	   @Test(dataProvider="inputData",dataProviderClass=TestData.class)
       public void ValidateAmount(String input) throws InterruptedException, IOException 
       {
		
		   service=startServer();
		AndroidDriver<AndroidElement>driver=capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Actual Script
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys(input);
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		
		Utilities u= new Utilities(driver);
		u.scrollToText("Australia");
	
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Australia']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		
		Thread.sleep(5000);
		
		
		int count= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		
		double sum=0;
		
		for(int i=0;i<count;i++)
		{

			String amount1=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();		
			double amount=getAmount(amount1);
			sum=sum+amount;
		}		
		
		
		//String amount1=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();		
		//double amountvalue1=getAmount(amount1);		
		//amount1=amount1.substring(1);		
		//double amountvalue1=Double.parseDouble(amount1);
		
		String amount2=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
		double amountvalue2=getAmount(amount2);
		
	//	amount1=amount2.substring(1);		
		//double amountvalue2=Double.parseDouble(amount1);		
		//Double sumOfProducts=amountvalue1+amountvalue2;
		System.out.println(sum+ " sum of products");
		
		
		String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		
		total=total.substring(1);
		double totalvalue=Double.parseDouble(total);
		System.out.println(totalvalue +"total value of product");
		
		Assert.assertEquals(sum, totalvalue);
		
		service.stop();
       }
	   
	   
	   @BeforeTest
	   public void killAllnode() throws InterruptedException, IOException
	   {
		   Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		   Thread.sleep(3000);
		   
	   }
       
       
       public static  double getAmount(String value)
       {
    	   value=value.substring(1);
   		
   		double amountvalue2=Double.parseDouble(value);   		
   		return amountvalue2;
       }
}
















