package com.automation.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button extends SuperAction {

	/** Method to validate whether the button is enabled or disabled.
	 * 
	 * @return
	 * @throws Throwable
	 */
	public static boolean validateButtonEnabled(By elem) throws Throwable{
		try {
			WebElement ele=getElem(elem);
			if(ele.isEnabled()) {
				infoReport(elem);
			    return true;
			}
		    else
			    infoReport(elem);
		
		}catch(Throwable th) {
			catchError(th);}
		return false;
		}

	/** Method to validate the state of the button when enabled property doesn't work.
	 * 
	 * @return
	 * @throws Throwable
	 */
   public static boolean validateButtonEnabled(By elem, String attribute)throws Throwable {
	   try {
		   if(Element.getAttribute(elem,attribute).equalsIgnoreCase("true")) {
			    infoReport(elem);
			    return true;
		   }else {
			   infoReport(elem);
		   }
		   }catch(Throwable th) {
			   catchError(th);
		   }
	   return false;
	   }
   /** Method to click a Button if it is enabled
	 * 
	 * @return
	 * @throws Throwable
	 */
   public static boolean click(By elem) throws Throwable{
	   try {
		   WebElement ele = getElem(elem);
		   if(ele.isEnabled()) {
			   ele.click();
			   Thread.sleep(2000);
			   infoReport(elem);
			   return true;
		   }else {
			   infoReport(elem);
		   }
		   }catch(Throwable th) {
			   catchError(th);
		   }
		   return false;
   }
   }