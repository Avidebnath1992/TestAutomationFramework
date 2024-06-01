package com.automation.framework;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebWait extends SuperAction {

	private static WebDriverWait wd;
	
	private static void wdWait() {
		wd = new WebDriverWait(driver.get(),  Duration.ofSeconds(10));
	}
	
	private static void wdWait(long timeOutInSecond) {
		wd= new WebDriverWait(driver.get(), Duration.ofSeconds(timeOutInSecond));
	}
	
	public static void waitUntilElemStale(By element, int timeOutInSecond) throws Throwable {
		try {
			WebElement ele =getElem(element);
			wdWait(timeOutInSecond);
			if(ele!=null) {
				wd.until(ExpectedConditions.stalenessOf(ele));
			}
			
		}catch(Throwable th) {
			infoError(th,element);
		}
	}
	
	public static void waitUntilElemStale(By element) throws Throwable {
		try {
			WebElement ele =getElem(element);
			wdWait();
			if(ele!=null) {
				wd.until(ExpectedConditions.stalenessOf(ele));
			}
			
		}catch(Throwable th) {
			infoError(th,element);
		}
	}
	
	public static void waitUntilElemVisible(By element,int timeOutInSeconds) throws Throwable {
		try {
		wdWait(timeOutInSeconds);
		wd.until(ExpectedConditions.visibilityOfElementLocated(element));
	   }catch(Throwable th) {
		infoError(th);
	   }
	}
	
	public static void waitUntilElemVisible(By element) throws Throwable {
		try {
		wdWait();
		wd.until(ExpectedConditions.visibilityOfElementLocated(element));
	   }catch(Throwable th) {
		infoError(th);
	   }
	}
	
	public static void waitUntilElemDisappears(By element,int timeOutInSeconds) throws Throwable {
		if(getElems(element).size()>0) {
		    try {
		        wdWait(timeOutInSeconds);
		        wd.until(ExpectedConditions.invisibilityOfElementLocated(element));
	       }catch(Throwable th) {
		        infoError(th);
	       }
	   }
	}
	public static void waitUntilElemDisappears(By element) throws Throwable {
		if(Element.isVisible(element)) {
		    try {
		        wdWait();
		        wd.until(ExpectedConditions.invisibilityOfElementLocated(element));
	       }catch(Throwable th) {
		        infoError(th);
	       }
	   }
	}
	
	public static void waitUntilElemEnabled(By element,int timeOutInSeconds) throws Throwable {
		    try {
		        wdWait(timeOutInSeconds);
		        wd.until(ExpectedConditions.elementToBeClickable(element));
	       }catch(Throwable th) {
		        infoError(th,element);
	       }
	   }
	
	public static void waitUntilElemEnabled(By element) throws Throwable {
		    try {
		        wdWait();
		        wd.until(ExpectedConditions.elementToBeClickable(element));
	       }catch(Throwable th) {
		        infoError(th,element);
	       }
	   }
	public static void waitUntilElemSelected(By element, int timeOutInSeconds ) throws Throwable {
	    try {
	        wdWait(timeOutInSeconds);
	        wd.until(ExpectedConditions.elementToBeSelected(element));
       }catch(Throwable th) {
	        infoError(th,element);
       }
   }
	
	public static void waitUntilElemSelected(By element ) throws Throwable {
	    try {
	        wdWait();
	        wd.until(ExpectedConditions.elementToBeSelected(element));
       }catch(Throwable th) {
	        infoError(th,element);
       }
   }
	
	public static void waitUntilUrlToBe(String url,int timeOutInSeconds) throws Throwable {
	    try {
	        wdWait(timeOutInSeconds);
	        wd.until(ExpectedConditions.urlToBe(url));
       }catch(Throwable th) {
	        infoError(th);
       }
   }
	public static void waitUntilUrlToBe(String url) throws Throwable {
	    try {
	        wdWait();
	        wd.until(ExpectedConditions.urlToBe(url));
       }catch(Throwable th) {
	        infoError(th);
       }
   }
	
	public static void waitUntilUrlContains(String urlpart,int timeOutInSecond) throws Throwable {
	    try {
	        wdWait(timeOutInSecond);
	        wd.until(ExpectedConditions.urlContains(urlpart));
       }catch(Throwable th) {
	        infoError(th);
       }
   }
	public static void waitUntilUrlContains(String urlpart) throws Throwable {
	    try {
	        wdWait();
	        wd.until(ExpectedConditions.urlContains(urlpart));
       }catch(Throwable th) {
	        infoError(th);
       }
   }
	
	public static void waitUntilTitleIs(String title,int timeOutInSecond) throws Throwable {
	    try {
	        wdWait(timeOutInSecond);
	        wd.until(ExpectedConditions.titleIs(title));
       }catch(Throwable th) {
	        infoError(th);
       }
   }
	public static void waitUntilTitleIs(String title) throws Throwable {
	    try {
	        wdWait();
	        wd.until(ExpectedConditions.titleIs(title));
       }catch(Throwable th) {
	        infoError(th);
       }
   }
	public static void waitUntilTitleContains(String titlePart, int timeOutInSecond) throws Throwable {
	    try {
	        wdWait(timeOutInSecond);
	        wd.until(ExpectedConditions.titleContains(titlePart));
       }catch(Throwable th) {
	        infoError(th);
       }
   }
	public static void waitUntilTitleContains(String titlePart) throws Throwable {
	    try {
	        wdWait();
	        wd.until(ExpectedConditions.titleContains(titlePart));
       }catch(Throwable th) {
	        infoError(th);
       }
   }
	
	public static void waitUntilAlertPresent( int timeOutInSecond) throws Throwable {
	    try {
	        wdWait(timeOutInSecond);
	        wd.until(ExpectedConditions.alertIsPresent());
       }catch(Throwable th) {
	        infoError(th);
       }
   }
	
	public static void waitUntilAlertPresent() throws Throwable {
	    try {
	        wdWait();
	        wd.until(ExpectedConditions.alertIsPresent());
       }catch(Throwable th) {
	        infoError(th);
       }
   }
	/**
	 * method to wait for DOM load after navigating to URL
	 * <br> it will wait until it gets true from a javascript executor
	 */
	public static void waitForLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return.document.readyState").equals("complete");
			}
		};
		wd=new WebDriverWait(SuperAction.driver.get(), Duration.ofSeconds(15));
		wd.until(pageLoadCondition);
	}
	/**
	 * Method to wait for page load for a specific amount of time
	 * @param timeOutInSec
	 * @throws Throwable
	 */

	@SuppressWarnings("deprecation")
	public static void waitUntilPageLoad(int timeOutInSec)throws Throwable{
		try {
			driver.get().manage().timeouts().pageLoadTimeout(timeOutInSec,TimeUnit.SECONDS);
		}catch(Exception e) {
			Reporter.warningStep("Failed to load page .. where, page title:: "+driver.get().getTitle()+"&& current Url :: "+driver.get().getCurrentUrl());
		}
	}
}
