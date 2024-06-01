package com.automation.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InputBox extends SuperAction{

	/** Method to set Text or String for an Input Box
	 * param elem
	 * param text_to_enter
	 * @return
	 * @throws Throwable
	 */
	public static boolean setText(By elem, String value)throws Throwable{
		try {
			WebElement ele= getElem(elem);
			if(value!=null) {
				ele.clear();
				ele.sendKeys(value);
				Thread.sleep(3000);
				return true;
			}
			else {
				infoReport(elem,"Null value provided");
			}
			
		}catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	public static String getText(By elem)throws Throwable{
		try {
			WebElement ele=getElem(elem);
			return ele.getAttribute("value");
		}catch(Throwable th ) {
			catchError(th);
		}
		return (String) Element.executeJavaScript("return arguments[0].value", elem);
	}
}
