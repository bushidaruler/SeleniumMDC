package com.mws.tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MWS {

	public WebDriver driver;
	public String mws;
	public String user;
	public String password;
	
	@Before
	public void setUp() {
		mws = "http://10.240.116.77:8080/mws/";
		user = "mdc";
		password = "mdc";
		
		//Create firefox driver
		driver = new FirefoxDriver();
	}
	
	public void login() {
		//Open mws home page
		driver.get(mws);
		
		//Login to MWS
		driver.findElement(By.name("user")).sendKeys(user);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@class='Navig_button']")).click();
	}
	
	public void logout() {
		//Logout
		driver.findElement(By.xpath("//img[@title='logout']")).click();
		driver.close();
	}
	
	@Test
	public void Dashboard() { // MDC_TC_949	
		
		login();
		
		//Verify that a dashboard interface is provided by a web browser.
		Assert.assertTrue(driver.findElement(By.xpath("//input[@onclick='openCurrentData();']")) != null);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Historical Data']")) != null);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Maintenance']")) != null);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='User Admin']")) != null);
				
		logout();
	}
	
	@Test
	public void Login () { // MDC_TC_311
		
		driver.get(mws);
		
		//Check if username and password fields exist
		Assert.assertTrue(driver.findElement(By.name("user")) != null);
		Assert.assertTrue(driver.findElement(By.name("password")) != null);
		
		driver.close();
	}
	
	@Test
	public void Wrong_Password() { //	MDC_TC_345
		
		//Open mws home page
		driver.get(mws);
		
		//Try logging to MWS in with empty logon and password fields 
		driver.findElement(By.xpath("//input[@class='Navig_button']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Wrong password or username!"));
		
		driver.close();
	}
	
	
	
}
