package test;

import java.io.PrintWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class edt {
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
		
		for (String Child : driver.getWindowHandles())  
	     {
				try {
					driver.switchTo().window(Child);
					Destination= driver.getCurrentUrl();
					WebElement err=driver.findElement(By.xpath("/html/body/div/div/div/div"));
					Log=err.getText();
					
				    PrintWriter writer = new PrintWriter("/home/ravali/Downloads/error.txt", "UTF-8");
				    
				    writer.println("Origin Url : " +Origin+"\nDestination Url: "+ Destination+"\n Log: "+ Log);
				    
				    writer.close();
				    //Thread.sleep(1000 *5);
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
		//driver.switchTo().window(Parent); 
		
	}

}
