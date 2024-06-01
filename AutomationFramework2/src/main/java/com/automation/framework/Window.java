package com.automation.framework;

import java.util.Set;

public class Window extends SuperAction{
	
	/**
	 * Method to switch to a window by handle
	 * @param handle
	 * @throws Throwable
	 */
	public static void switchByHandle(String handle) throws Throwable{
		try {
			driver.get().switchTo().window(handle);
		}catch(Throwable th) {
			catchError(th);
		}
	}
	/**
	 * Method to switch to a window by Index
	 * index start from 0
	 * @param index
	 * @throws Throwable
	 */

	public static void switchByIndex(int index) throws Throwable{
		try {
		Set<String> handles =driver.get().getWindowHandles();
		driver.get().switchTo().window((String)handles.toArray()[index-1]);
	   }catch(Throwable th) {
		   catchError(th);
	   }
	}
	
	/**
	 * Method to switch to a window by window title contains a string
	 * @param Title
	 * @return
	 * @throws Throwable
	 */
	public static boolean switchByTitle(String Title) throws Throwable{
		try {
			String currentHandle=driver.get().getWindowHandle();
			Set<String> handles =driver.get().getWindowHandles();
			for(String handle: handles) {
				driver.get().switchTo().window(handle);
				if(driver.get().getTitle().contains(Title)) {
					return true;
				}
				driver.get().switchTo().window(currentHandle);
			}
		}catch(Throwable th) {
			 catchError(th);
		}
		return false;
	}
	
	/**
	 * Method to switch to a window by URL contains
	 * @param url
	 * @return
	 * @throws Throwable
	 */
	public static boolean switchByUrl(String url) throws Throwable{
		try {
			String currentHandle=driver.get().getWindowHandle();
			Set<String> handles =driver.get().getWindowHandles();
			for(String handle: handles) {
				driver.get().switchTo().window(handle);
				if(driver.get().getTitle().contains(url)) {
					return true;
				}
				driver.get().switchTo().window(currentHandle);
			}
		}catch(Throwable th) {
			 catchError(th);
		}
		return false;
	}
	
	/**
	 * Method to switch to newly opened browser instances
	 * @param childInstance <br>
	 *                     Index as Integer of the new instances to be switched to.
	 *                     parent instance is 0
	 * @throws Throwable
	 */
	
	public static void switchTo(int childInstance) throws Throwable {
		try {
			Set<String> allWindowHandles = driver.get().getWindowHandles();
			if((allWindowHandles.size()-1)<childInstance)
				childInstance=allWindowHandles.size()-1;
			String window=(String) allWindowHandles.toArray()[childInstance];
			driver.get().switchTo().window(window);
		}catch(Throwable th) {
			catchError(th);
		}
	}
}
