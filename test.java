package test;

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

public class edt {
	public static String getFileName() {
		    String[] array = new String[]{ "HH","mm","ss","dd","MM","yyyy" };
		    String file ="";
		    for (int i = 0; i < array.length; i++) {
			    Date date = new Date();  
			    SimpleDateFormat formatter = new SimpleDateFormat(array[i]);  
			    String strDate = formatter.format(date);  
			     file= file+strDate;
			     if(array[i]!="yyyy") {
			    	 file=file+"_";
			     }else {
			    	 file=file+"Test_Log.csv";
			     }
		  
		    }
		    return file;
		    
		}
	public static void main(String[] args) throws InterruptedException {
		String Origin,Destination, Log;
		System.setProperty("webdriver.chrome.driver","/home/ravali/Downloads/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://edyst-staging-platform.netlify.com/");
		String Parent = driver.getWindowHandle();
		Origin=driver.getCurrentUrl();
		
		driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div[1]/div[1]/div/div[3]/button")).click();
		
		WebElement ele=driver.findElement(By.xpath(
		 "/html/body/div[1]/div/div[3]/div/div[3]/div/div[2]/div/div[5]/div[2]/div/div[1]/div/div[1]/div/span[2]"));
		
		ele.click();
		Set<String> s=driver.getWindowHandles();
		System.out.println(s);
		
		for (String windowHandle : driver.getWindowHandles())  
	     {
			 if(!windowHandle.equals(Parent)) {
				try {
					driver.switchTo().window(windowHandle);
					Destination= driver.getCurrentUrl();
					WebElement err=driver.findElement(By.xpath("/html/body/div/div/div/div"));
					
					Log=err.getText();
					driver.close();
					//driver.switchTo().window(Parent);
					BufferedWriter br = new BufferedWriter(new FileWriter("/home/ravali/Desktop/"+getFileName()));
					StringBuilder sb = new StringBuilder();
					  
						sb.append("Test Name"+"\t"+"Origin" + "\t"+ "Destination"+"\t"+ "Log"+"\t"+ "Status");    
						sb.append("\n");
						sb.append("Login Test"+"\t"+Origin + "\t"+ Destination+"\t"+ Log+"\t"+ "Error");
					  
					br.write(sb.toString());
					br.close();
					  
				    Thread.sleep(1000 *5);
					//driver.quit();
				    
			    }
				catch(Exception e){
					driver.findElement(By.id("email")).sendKeys("ravsmeda456@gmail.com");
			        driver.findElement(By.id("pass")).sendKeys("ravali@21");
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
		driver.switchTo().window(Parent);
		driver.close();
		
	}
 
}
