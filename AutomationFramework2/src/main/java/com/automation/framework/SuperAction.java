package com.automation.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class SuperAction extends WebDriverWrapper {
	/**Method to get Web Element object Name
	 * 
	 * @param identifier
	 * @return Locator Name as String
	 * @throws Throwable
	 */

	protected static String getLocatorName(By identifier) throws Throwable{
		if(identifier!=null) {
			return "<strong style=\"font-weight:600 !important\">" + identifier.toString().split(":")[1].toString().trim()+"</strong>";
		}
		else
			return null;
	}
	
	/**Method to get WebElement by their object Name
	 * 
	 * @param elem
	 * @return A particular WebElement
	 * @throws Throwable
	 */
	
	public static WebElement getElem(By elem)throws Throwable {
		if(elem!=null) {
			return driver.get().findElement(elem);
		}
		return null;
	}
	/**
	 * Method to get List<WebElement> by their object Name
	 * @param elem 
	 * @return List<WebElement>
	 * @throws Throwable
	 */
	public static List<WebElement> getElems(By elem)throws Throwable {
		if(elem!=null) {
			return driver.get().findElements(elem);
		}
		return null;
	}
	// Error and Report handling
	protected static void infoReport(By elem) throws Throwable {
		String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
		Reporter.infoStep(methodName+ " :: executed for element - " + getLocatorName(elem));
	}
	
	protected static void infoReport(By elem,String msg) throws Throwable {
		String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
		Reporter.infoStep(msg+ " :: " + Reporter.boldTxt(methodName)+"executed for element - " + getLocatorName(elem));
	}
	
	protected static void catchError(Throwable th) throws Throwable {
		String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
		String errorBody= "Exception Occured in the method :: "+ Reporter.boldTxt(methodName) + "&& The error message is :: " + th.getMessage();
		Reporter.add2LogNote("SEVERE", "FAIL",errorBody);
		Reporter.warningStep(errorBody);
	}
	
	protected static void catchError(Throwable th, By elem) throws Throwable {
		String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
		String errorBody= "Exception Occured in the method :: "+ Reporter.boldTxt(methodName) + ":: for element :: " + getLocatorName(elem) + "&& The error message is" + th.getMessage();
		Reporter.add2LogNote("SEVERE", "FAIL",errorBody);
		Reporter.warningStep(errorBody);
	}
	
	protected static void catchErrorWithMessage(Throwable th, String message)throws Throwable{
		String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
		String errorBody=message+ ":: Exception Occured in the method :: "+ Reporter.boldTxt(methodName) + "&& The error message is :: " + th.getMessage();
		Reporter.add2LogNote("SEVERE", "FAIL",errorBody);
		Reporter.warningStep(errorBody);
	}
	protected static void catchErrorWithMessage(Throwable th, String message,By elem)throws Throwable{
		String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
		String errorBody=message+ ":: Exception Occured in the method :: "+ Reporter.boldTxt(methodName)  + ":: for element :: " + getLocatorName(elem) + "&& The error message is :: " + th.getMessage();
		Reporter.add2LogNote("SEVERE", "FAIL",errorBody);
		Reporter.warningStep(errorBody);
	}
	protected static void infoError(Throwable th)throws Throwable{
		String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
		String errorBody= "Exception Occured in the method :: "+ Reporter.boldTxt(methodName) + "&& The error message is :: " + th.getMessage();
		Reporter.add2LogNote("SEVERE", "FAIL",errorBody);
		Reporter.infoStep(errorBody);
	}
	
	protected static void infoError(Throwable th,By elem)throws Throwable{
		String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
		String errorBody= "Exception Occured in the method :: "+ Reporter.boldTxt(methodName) + ":: for element :: " + getLocatorName(elem) + "&& The error message is" + th.getMessage();
		Reporter.add2LogNote("SEVERE", "FAIL",errorBody);
		Reporter.infoStep(errorBody);
	}
	
}
