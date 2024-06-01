package com.automation.framework;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page extends SuperAction {
	/** Method to execute javascript in a page
	 * param script
	 * @throws Throwable
	 */

	public static void executeJavascript(String script) throws Throwable{
		try {
			((JavascriptExecutor)driver.get()).executeScript(script, "");
		}catch(Throwable th) {
			catchErrorWithMessage(th, script+":: this javascript has not executed");
		}
	}
	
	/** Method to get title of a page
	 * @return 
	 * @throws Throwable
	 */

	public static String getTitle() throws Throwable{
		try {
			return driver.get().getTitle();
		}catch(Throwable th) {
			catchErrorWithMessage(th, "unable to get title");
		}
		return null;
	}
	
	/** Method to get the current Url of a page
	 * @return 
	 * @throws Throwable
	 */

	public static String getCurrentUrl() throws Throwable{
		try {
			return driver.get().getCurrentUrl();
		}catch(Throwable th) {
			catchError(th);
		}
		return null;
	}
	
	/** Method to validate a page title.<br>
	 * it return true if matches
	 * param title
	 * @return true/false
	 * @throws Throwable
	 */

	public static boolean validateTitle(String title) throws Throwable{
		try {
			WebWait.waitForLoad();
			WebWait.waitUntilTitleIs(title);
			if(driver.get().getTitle().equalsIgnoreCase(title)) {
				return true;
			}else {
				Reporter.infoStep("There is a mismatch in title.");
			}
		}catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	
	/** Method to Open a URL.<br>
	 * param URL<br>
	 * For Example "https:google.com"<br>
	 * please use "http" or "https" before your URL
	 * @return 
	 * @throws Throwable
	 */

	public static boolean openUrl(String url) throws Throwable{
		try {
			driver.get().get(url);
			WebWait.waitForLoad();
			Reporter.infoStep(url+":: Loaded :");
			Reporter.add2LogNote("INFO", "PASS", "URL loaded && the URL is :: "+url);
		}catch(Throwable th) {
			catchError(th);
		}
		return false;
	}
	
	/** Method to directly navigate to any given URL.
	 * param URL<br>
	 * The URL to which it has to be navigated.
	 * @throws Throwable
	 */

	public static void navigateUrl(String url) throws Throwable{
		try {
			driver.get().navigate().to(url);
			WebWait.waitForLoad();
			Reporter.infoStep("Successfully navigated to URL :: "+url);
		}catch(Throwable th) {
			catchError(th);
		}
	}
	
	/** Method to open a URL in new tab using Javascript Executor
	 * param URL<br>
	 * @throws Throwable
	 */

	public static void openURLinNewTab(String url) throws Throwable{
		try {
			((JavascriptExecutor)driver.get()).executeScript("window.open('about:blank','_blank')", "");
			Window.switchTo(99);
			driver.get().manage().window().maximize();
			navigateUrl(url);
		}catch(Throwable th) {
			catchError(th);
		}
	}
	
	/** Method to refresh currently opened web page
	 * 
	 * @throws Throwable
	 */

	public static void refresh() throws Throwable{
		try {
			driver.get().navigate().refresh();
			WebWait.waitForLoad();
		}catch(Throwable th) {
			catchError(th);
		}
	}
	
	/** Method to refresh currently opened web page
	 * param WaitTime - explicit time to wait after page refresh 
	 * @throws Throwable
	 */

	public static void refresh(int waitTime) throws Throwable{
		try {
			driver.get().navigate().refresh();
			Thread.sleep(waitTime);
			WebWait.waitForLoad();
		}catch(Throwable th) {
			catchError(th);
		}
	}
}
