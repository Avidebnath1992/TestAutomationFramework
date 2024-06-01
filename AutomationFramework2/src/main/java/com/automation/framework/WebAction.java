package com.automation.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebAction extends SuperAction{
	private static Actions action;
	
	public static boolean click(By elem)throws Throwable{
		try {
			action=new Actions(driver.get());
			WebElement ele =getElem(elem);
			action.moveToElement(ele).click().perform();
			WebWait.waitForLoad();
			infoReport(elem);
			return true;
		}catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	
	/**
	 * Method to hover Mouse on a WebElement
	 * @param elem
	 * @throws Throwable 
	 */
	public static void mouseHover(By elem) throws Throwable {
		try {
			action=new Actions(driver.get());
			WebElement ele=getElem(elem);
			action.moveToElement(ele).build().perform();
			WebWait.waitForLoad();
			infoReport(elem);
		}catch(Throwable th) {
			catchError(th);
		}
	}
	/**
	 * Method to Right Click on a WebElement
	 * @param elem
	 * @throws Throwable 
	 */
	public static void mouseRightClick(By elem) throws Throwable {
		try {
			action=new Actions(driver.get());
			WebElement ele=getElem(elem);
			action.moveToElement(ele).contextClick().perform();
			WebWait.waitForLoad();
			infoReport(elem);
		}catch(Throwable th) {
			catchError(th);
		}
	}

	/**
	 * Method to Double Click on a WebElement
	 * @param elem
	 * @throws Throwable 
	 */
	public static void mouseDoubleClick(By elem) throws Throwable {
		try {
			action=new Actions(driver.get());
			WebElement ele=getElem(elem);
			action.doubleClick(ele).build().perform();
			WebWait.waitForLoad();
			infoReport(elem);
		}catch(Throwable th) {
			catchError(th);
		}
	}
	
	/**
	 * Method to Click a keyboard key on a WebElement
	 * @param elem
	 * @throws Throwable 
	 */
	public static void keyPress(By elem,Keys key) throws Throwable {
		try {
			action=new Actions(driver.get());
			getElem(elem).sendKeys(key);;
			Thread.sleep(3000);
			infoReport(elem);
		}catch(Throwable th) {
			catchError(th);
		}
	}
	
	/**
	 * Method to perform key operation in DOM
	 * @param key
	 * @throws Throwable 
	 */
	public static void keyPress(Keys key) throws Throwable {
		try {
			action=new Actions(driver.get());
			action.sendKeys(key).perform();
			Thread.sleep(3000);
		}catch(Throwable th) {
			catchError(th);
		}
	}
}
