package com.automation.framework;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Elements extends SuperAction{
	/** Method to determine whether an element with index is selected in DOM
	 * param index
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static boolean isSelected(By elems, int index)throws Throwable{
		try {
			WebElement element = getElems(elems).get(index);
			if(element.isSelected()) {
				return true;
			}
			else {
				infoReport(elems);
			}
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return false;
	}
	
	/** Method to determine whether an element with index is Enabled in DOM
	 * param index
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static boolean isEnabled(By elems, int index)throws Throwable{
		try {
			WebElement element = getElems(elems).get(index);
			if(element.isEnabled()) {
				return true;
			}
			else {
				infoReport(elems);
			}
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return false;
	}
	
	/** Method to check whether an element with index is Visible in DOM
	 * param index
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static boolean isVisible(By elems, int index)throws Throwable{
		try {
			WebElement element = getElems(elems).get(index);
			if(element.isDisplayed()) {
				return true;
			}
			else {
				infoReport(elems);
			}
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return false;
	}
	
	/** Method to Click any WebElement
	 * param index
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static boolean click(By elems, int index)throws Throwable{
		try {
			WebElement element = getElems(elems).get(index);
			element.click();
			Thread.sleep(2000);
			return true;
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return false;
	}
	
	/** Method to Click any WebElement using JavaScript
	 * param index
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static boolean clickByJavaScript(By elems, int index)throws Throwable{
		try {
			WebElement element = getElems(elems).get(index);
			((JavascriptExecutor)driver.get()).executeScript("argument[0].click();", element);
			return true;
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return false;
	}
	
	/** Method to get text from any particular element
	 * param index
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static String getText(By elems, int index)throws Throwable{
		try {
			WebElement element = getElems(elems).get(index);
			return element.getText();
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return null;
	}
	
	/** Method to get text value from any particular element where getText is not working
	 * param index
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static String getTextValue(By elems, int index)throws Throwable{
		try {
			WebElement element = getElems(elems).get(index);
			return element.getAttribute("value");
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return null;
	}
	
	/** Method to get an attribute value from any WebElement
	 * <br>it returns the attribute value
	 * param attribute
	 * param index
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static String getAttribute(By elems, int index,String attribute)throws Throwable{
		try {
			WebElement element = getElems(elems).get(index);
			return element.getAttribute(attribute);
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return null;
	}
	
	/** Method to verify attribute value from an Element
	 * param attribute
	 * param index
	 * param elem
	 * param attributeValuetoValidate
	 * @return
	 * @throws Throwable
	 */
	public static boolean verifyAttribute(By elems, int index,String attribute,String attributeValuetoValidate)throws Throwable{
		try {
			WebElement element = getElems(elems).get(index);
			if(element.getAttribute(attribute).equalsIgnoreCase(attributeValuetoValidate)) {
				return true;
			}else {
				infoReport(elems);
			}
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return false;
	}
	
	/** Method to click any particular element in a list of elements by name
	 * param name
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static boolean selectByName(By elems, String name)throws Throwable{
		try {
			List<WebElement> element = getElems(elems);
			for(WebElement ele:element) {
				if(ele.getText().trim().equalsIgnoreCase(name)) {
					ele.click();
					Thread.sleep(2000);
					infoReport(elems);
					return true;
				}
			}
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return false;
	}
	
	/** Method to click any particular element in a list of elements by partial name
	 * param name
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static boolean selectByPartialName(By elems, String name)throws Throwable{
		try {
			List<WebElement> element = getElems(elems);
			for(WebElement ele:element) {
				if(ele.getText().trim().contains(name)) {
					ele.click();
					Thread.sleep(2000);
					infoReport(elems);
					return true;
				}
			}
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return false;
	}
	/** Method to get texts from list of web element and stored in a list and return that list
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static ArrayList<String> getTexts(By elems)throws Throwable{
		ArrayList<String> data=new ArrayList<String>();
		try {
			List<WebElement> elements = getElems(elems);
			for(WebElement ele:elements) {
				data.add(ele.getText().trim());
			}
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return data;
	}
	
	/** Method to get attribute from list of web element and stored in a list and return that list
	 * param attribute
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static ArrayList<String> getAttributes(By elems,String attribute)throws Throwable{
		ArrayList<String> data=new ArrayList<String>();
		try {
			List<WebElement> elements = getElems(elems);
			for(WebElement ele:elements) {
				data.add(ele.getAttribute(attribute));
			}
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return data;
	}
	
	/** Method to return the size of list of Element
	 * param attribute
	 * param elem
	 * @return
	 * @throws Throwable
	 */
	public static int size(By elems)throws Throwable{
		try {
			List<WebElement> elements = getElems(elems);
			return elements.size();
		}catch(Throwable th) {
			catchError(th, elems);
		}
		return -1;
	}
	
}
