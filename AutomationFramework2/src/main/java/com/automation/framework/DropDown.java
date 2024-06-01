package com.automation.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDown extends SuperAction {

	private static Select sel;
	/** Method to select item from a List where there is no Select Tag
	 * 
	 * Need to click to the element to open Dropdown.
	 * @return
	 * @throws Throwable
	 */
	public static boolean listSelectedByItemName(By elems,String Name) throws Throwable {
		try {
			List<WebElement> elements= getElems(elems);
			for(WebElement ele:elements) {
				if(ele.getText().trim().equalsIgnoreCase(Name)) {
					ele.click();
					Thread.sleep(2000);
					return true;
				}
			}
		}catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	
	/** Method to select a drop-down option from Options by index No.
	 * 
	 * param elem <br> select tag element
	 * param indexNum
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean selectDropDownByIndex(By elem, int indexNum) throws Throwable{
		try {
			WebElement ele = getElem(elem);
			sel=new Select(ele);
			sel.selectByIndex(indexNum);
			Thread.sleep(2000);
			WebWait.waitForLoad();
			infoReport(elem);
			return true;
		}
		catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	
	/** Method to select a drop-down option from Options by name.
	 * 
	 * param elem <br> select tag element
	 * param name
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean selectDropDownByName(By elem, String name) throws Throwable{
		try {
			WebElement ele = getElem(elem);
			sel=new Select(ele);
			sel.selectByVisibleText(name);
			Thread.sleep(2000);
			WebWait.waitForLoad();
			infoReport(elem);
			return true;
		}
		catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	/** Method to select a drop-down option from Options by Value.
	 * 
	 * param elem <br> select tag element
	 * param value
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean selectDropDownByValue(By elem, String value) throws Throwable{
		try {
			WebElement ele = getElem(elem);
			sel=new Select(ele);
			sel.selectByValue(value);
			Thread.sleep(2000);
			WebWait.waitForLoad();
			infoReport(elem);
			return true;
		}
		catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	
	/** Method to De-select a drop-down option from Options by Index number.
	 * 
	 * param elem <br> select tag element
	 * param indexNum
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean deselectDropDownByIndex(By elem, int indexNum) throws Throwable{
		try {
			WebElement ele = getElem(elem);
			sel=new Select(ele);
			sel.deselectByIndex(indexNum);
			Thread.sleep(2000);
			WebWait.waitForLoad();
			infoReport(elem);
			return true;
		}
		catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	
	/** Method to De-select a drop-down option from Options by name.
	 * 
	 * param elem <br> select tag element
	 * param name
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean deselectDropDownByName(By elem, String name) throws Throwable{
		try {
			WebElement ele = getElem(elem);
			sel=new Select(ele);
			sel.deselectByVisibleText(name);
			Thread.sleep(2000);
			WebWait.waitForLoad();
			infoReport(elem);
			return true;
		}
		catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	
	/** Method to De-select a drop-down option from Options by value.
	 * 
	 * param elem <br> select tag element
	 * param value
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean deselectDropDownByValue(By elem, String value) throws Throwable{
		try {
			WebElement ele = getElem(elem);
			sel=new Select(ele);
			sel.deselectByValue(value);
			Thread.sleep(2000);
			WebWait.waitForLoad();
			infoReport(elem);
			return true;
		}
		catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	
	/** Method to De-select All
	 * 
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean deselectDropDownByValue(By elem) throws Throwable{
		try {
			WebElement ele = getElem(elem);
			sel=new Select(ele);
			sel.deselectAll();
			Thread.sleep(2000);
			WebWait.waitForLoad();
			infoReport(elem);
			return true;
		}
		catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	
	/** Method to get all dropdown options
	 * 
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static List<WebElement> getAllOptions(By elem) throws Throwable{
		try {
			WebElement ele = getElem(elem);
			sel=new Select(ele);
			infoReport(elem);
			return sel.getOptions();
		}
		catch(Throwable th) {
			catchError(th);
		}
		return null;
	}
	
	/** Method to get all selected options
	 * 
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static List<WebElement> getAllSelectedOptions(By elem) throws Throwable{
		try {
			WebElement ele = getElem(elem);
			sel=new Select(ele);
			infoReport(elem);
			return sel.getAllSelectedOptions();
		}
		catch(Throwable th) {
			catchError(th);
		}
		return null;
	}
	
	/** Method to check whether a drop-down supports multiSelect
	 * 
	 * param elem <br> select tag element
	 * @return
	 * @throws Throwable
	 */
	
	public static boolean isMultiSelect(By elem) throws Throwable{
		try {
			WebElement ele = getElem(elem);
			sel=new Select(ele);
			infoReport(elem);
			return sel.isMultiple();
		}
		catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
}
