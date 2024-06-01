package com.automation.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Iframe extends SuperAction {

	/** Method to switch to a frame by index
	 * param index
	 * @return
	 * @throws Throwable
	 */
	public static void switchToFrame(int index)throws Throwable{
		try {
			driver.get().switchTo().frame(index);
			Thread.sleep(3000);
		}catch(Exception e) {
			catchErrorWithMessage(e, "SwitchToFrame failed by index");
		}
	}
	
	/** Method to switch to a frame by target locator
	 * param locator
	 * @return
	 * @throws Throwable
	 */
	public static void switchToFrame(String locator)throws Throwable{
		try {
			driver.get().switchTo().frame(locator);
			Thread.sleep(3000);
		}catch(Exception e) {
			catchErrorWithMessage(e, "SwitchToFrame failed by locator");
		}
		
	}
	
	/** Method to switch to a frame by Element
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static void switchToFrame(By elem)throws Throwable{
		try {
			WebWait.waitUntilElemVisible(elem);
			driver.get().switchTo().frame(getElem(elem));
			Thread.sleep(3000);
		}catch(Exception e) {
			catchErrorWithMessage(e, "SwitchToFrame failed by WebElement");
		}
		
	}
	
	/** Method to switch to default content
	 * @return
	 * @throws Throwable
	 */
	public static void switchTodefault()throws Throwable{
		try {
			driver.get().switchTo().defaultContent();
			Thread.sleep(3000);
		}catch(Exception e) {
			catchErrorWithMessage(e, "SwitchToDefault Frame failed");
		}
		
	}
}
