package com.edyst.automation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.edyst.utils.CSVUtils;

public class test {
	public static void signIn(WebDriver driver,String Parent, String Origin) throws InterruptedException{
		String Destination, Log;
		WebElement ele=driver.findElement(By.xpath(
				 "/html/body/div[1]/div/div[3]/div/div[3]/div/div[2]/div/div[5]/div[2]/div/div[1]/div/div[1]/div/span[2]"));
				
		ele.click();
		//System.out.println(s);
		
		for (String windowHandle : driver.getWindowHandles())  
	     {
			 if(!windowHandle.equals(Parent)) {
				try {
					driver.switchTo().window(windowHandle);
					Destination= driver.getCurrentUrl();
					WebElement err=driver.findElement(By.xpath("/html/body/div/div/div/div"));
					
					Log=err.getText();
					driver.close();
					BufferedWriter br = new BufferedWriter(new FileWriter("/home/ravali/Desktop/"+CSVUtils.getFileName()));
					StringBuilder sb = new StringBuilder();
					  
						sb.append("Test Name"+","+"Origin"+","+ "Destination"+","+ "Log"+","+ "Status");    
						sb.append("\n");
						sb.append("Login Test"+","+Origin + ","+ Destination+","+ Log+","+ "Error");
					  
					br.write(sb.toString());
					br.close();
					  
				    Thread.sleep(1000 *5);
					
				    
			    }
				catch(Exception e){
					driver.findElement(By.id("email")).sendKeys("ravsmeda456@gmail.com");
			        driver.findElement(By.id("pass")).sendKeys("***");
			        driver.findElement(By.id("loginbutton")).submit();
			        System.out.println("Successfully logged in");
			        
			        Thread.sleep(3000);
			        WebElement logOut = driver.findElement(By.id("userNavigationLabel"));
			        logOut.click();
			        
			        Thread.sleep(5000);
			        WebElement signOut = driver.findElement(By.partialLinkText("Log Out"));
			        signOut.click();
			        
					Thread.sleep(1000 *2);
					//driver.navigate().back();
					
			    }	
			 }	
			
	     }
		
	}
	public static void write() {
		
	}
	public static void main(String[] args) throws InterruptedException {
		final String SERVER_URL = "https://edyst-staging-platform.netlify.com/";
		String Origin;
		System.setProperty("webdriver.chrome.driver","/home/ravali/Downloads/chromedriver");
		
		WebDriver driver = new ChromeDriver();	
		driver.get(SERVER_URL);
		String Parent = driver.getWindowHandle();
		Origin=driver.getCurrentUrl();
		
		driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div[1]/div[1]/div/div[3]/button")).click();
		signIn(driver,Parent,Origin);
		
		
		driver.switchTo().window(Parent);
		driver.close();
		
	}
 
}
