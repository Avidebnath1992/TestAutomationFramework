package com.automation.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Element extends SuperAction{
	
	/** Method to get an attribute value from any WebElement
	 * param attribute
	 * param elem <br> it returns the attribute value.
	 * @return
	 * @throws Throwable
	 */
	public static String getAttribute(By elem, String attribute)throws Throwable{
		try {
			WebElement element = getElem(elem);
			return element.getAttribute(attribute);
		}catch(Throwable th) {
			catchError(th, elem);
		}
		return null;
	}
	
	/** Method to execute javaScript on an element present on DOM
	 * param script
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static Object executeJavaScript(String script,By elem)throws Throwable{
		try {
			WebElement ele = getElem(elem);
			return ((JavascriptExecutor)driver.get()).executeScript(script, ele);
		}catch(Throwable th) {
			catchErrorWithMessage(th, " :: this javacript has not executed on the element");
		}
		return null;
	}
	
	/** Method to determine whether an element is selected in DOM
	 * 
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean isSelected(By elem)throws Throwable{
		try {
			WebElement ele = getElem(elem);
			if(ele.isSelected()) {
				return true;
			}else {
				infoReport(elem);
			}
		}catch(Throwable th) {
			catchError(th, elem);
		}
		return false;
	}
	
	/** Method to determine whether an element is enabled in DOM
	 * 
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean isEnabled(By elem)throws Throwable{
		try {
			WebElement ele = getElem(elem);
			if(ele.isEnabled()) {
				return true;
			}else {
				infoReport(elem);
			}
		}catch(Throwable th) {
			catchError(th, elem);
		}
		return false;
	}
	
	/** Method to determine whether an element is Visible in DOM
	 * 
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean isVisible(By elem)throws Throwable{
		try {
			WebElement ele = getElem(elem);
			if(ele.isDisplayed()) {
				return true;
			}else {
				infoReport(elem);
			}
		}catch(Throwable th) {
			catchError(th, elem);
		}
		return false;
	}
	
	/** Method to click Any WebElement
	 * 
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean click(By elem)throws Throwable{
		try {
			WebElement ele = getElem(elem);
			ele.click();
			Thread.sleep(2000);
			return true;
		}catch(Throwable th) {
			catchError(th, elem);
		}
		return false;
	}
	
	/** Method to click by element using JavaScript
	 * 
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean clickByJavaScript(By elem)throws Throwable{
		try {
			WebElement ele = getElem(elem);
			((JavascriptExecutor) driver.get()).executeScript("arguments[0].click();", ele);
			return true;
		}catch(Throwable th) {
			catchError(th, elem);
		}
		return false;
	}
	
	/** Method to get text from any particular element.
	 * 
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static String getText(By elem)throws Throwable{
		try {
			WebElement ele = getElem(elem);
			return ele.getText();
		}catch(Throwable th) {
			catchError(th, elem);
		}
		return null;
	}
	
	/** Method to get text value from any particular element where getText is not working.
	 * 
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static String getTextValue(By elem)throws Throwable{
		try {
			WebElement ele = getElem(elem);
			return ele.getAttribute("value");
		}catch(Throwable th) {
			catchError(th, elem);
		}
		return null;
	}
	
	/** Method to verify attribute value for an element
	 * param attribute
	 * param attributeValuetoValidate
	 * param elem 
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean verifyAttribute(By elem, String attribute, String attributeValuetoValidate)throws Throwable{
		try {
			WebElement ele = getElem(elem);
			if(ele.getAttribute(attribute).equalsIgnoreCase(attributeValuetoValidate)) {
				return true;
			}
			else {
				infoReport(elem);
			}
			
		}catch(Throwable th) {
			catchError(th, elem);
		}
		return false;
	}
}
