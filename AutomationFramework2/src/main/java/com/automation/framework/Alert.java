package com.automation.framework;

public class Alert extends SuperAction {
	
	/** Method to get Alert Message
	 * 
	 * @return
	 * @throws Throwable
	 */

	public static String getMessage()throws Throwable {
		try {
			return driver.get().switchTo().alert().getText();
		}catch(Throwable th) {
			catchError(th);
		}
		return null;
	}
	
	/** Method to send text to Alert
	 * 
	 * @return
	 * @throws Throwable
	 */
	public static void sendTXT(String txt) throws Throwable {
		try {
			driver.get().switchTo().alert().sendKeys(txt);
		}catch(Throwable th) {
			catchError(th);
		}
	}
	
	/** Method to Accept an Alert
	 * 
	 * @return
	 * @throws Throwable
	 */
	public static boolean accept()throws Throwable{
		
		try {
			driver.get().switchTo().alert().accept();
			return true;
		}catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	
	/** Method to dismiss an Alert
	 * 
	 * @return
	 * @throws Throwable
	 */
    public static boolean dismiss()throws Throwable{
		
		try {
			driver.get().switchTo().alert().dismiss();
			return true;
		}catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
}
