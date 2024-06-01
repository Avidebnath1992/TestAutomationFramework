package com.automation.framework;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverWrapper {
public static ThreadLocal<WebDriver> driver =new ThreadLocal<>();

/**
 * Method to return a WebDriver Object
 * @return
 */
public static WebDriver getWebDriver() {
	return driver.get();
}

/**
 * Method to initialize browser in Test Suite
 * <br> we need to call this typically in the before suite section. 
 * @param browserName
 * @throws Throwable
 */
@SuppressWarnings("deprecation")
public static void initBrowser(String browserName) throws Throwable{
	//DesiredCapabilities caps=null;
	switch(browserName.toLowerCase()) {
	        case "internetexplorer":
	        	System.setProperty("webdriver.ie.driver", Utilities.def_Loc+"drivers\\IEDriverServer.exe");
//	        	caps= DesiredCapabilities.internetExplorer();
//	        	caps.setCapability("ignoreZoomSetting", true);
//	        	caps.setCapability("ignoreProtectedModeSettings", true);
//	        	caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//	        	caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
//	        	caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
//	        	caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
	        	try {
	        		Runtime.getRuntime().exec("RunD1132.exe InetCpl.cpl,ClearMyTracksByProcess 255");
	        	}catch(IOException e) {
	        		Reporter.warningStep(("failed to set Capabilities: Internet Explorer :: Error Log: " +e.getMessage()));
	        		e.printStackTrace();
	        	}
	        	driver.set(new InternetExplorerDriver());
	        	driver.get().manage().deleteAllCookies();
	        	driver.get().manage().window().maximize();
	        	Reporter.passStep(":: Launching Selenium Driver: Internet Explorer");
	        	break;
	        case "firefox":
	        	try {
	        		System.setProperty("webdriver.gecko.driver", Utilities.def_Loc+ "drivers\\geckodriver.exe");
	        		//System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
	        		FirefoxProfile profile=new FirefoxProfile();
	        		profile.setPreference("browser.download.folderlist", 2);
	        		profile.setPreference("browser.download.dir", System.getProperty("user.dir")+"\\downloads");
	        		profile.setPreference("browser.download.useDownloadDir", true);
	        		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/json,application/pdf,application/x-pdf,application/octet-stream");
	        		profile.setPreference("pdfjs.disabled", true);
	        		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
	        		
	        		FirefoxOptions optionFF=new FirefoxOptions();
	        		optionFF.setProfile(profile);
	        		if(Utilities.checkFilePresent("C:/Program Files (x86)/Mozila Firefox/firefox.exe","","")) {
	        			optionFF.setBinary("C:/Program Files (x86)/Mozila Firefox/firefox.exe");
	        			driver.set(new FirefoxDriver(optionFF));
	        		}else if(Utilities.checkFilePresent("C:/Program Files/Mozila Firefox/firefox.exe","","")) {
	        		    optionFF.setBinary("C:/Program Files/Mozila Firefox/firefox.exe");
	        		    driver.set(new FirefoxDriver(optionFF));
	        		}
	        		driver.get().manage().deleteAllCookies();
	        		driver.get().manage().window().maximize();
	        		Reporter.passStep(":: Launching Selenium Driver:Firefox");
	         }catch(Exception e) {
	        	 Reporter.failStep(":: Launching Selenium Driver failed: Firefox");
	         }
	        	break;
	        case "microsoftedge":
	        	try {
	        		System.setProperty("webdriver.edge.driver", Utilities.def_Loc+"drivers\\MicrosoftWebDriver.exe");
	        		EdgeOptions edgOpt = new EdgeOptions();
	        		//edgOpt.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
	        		
	        		//edgOpt.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
	        		driver.set(new EdgeDriver(edgOpt));
	        		driver.get().manage().deleteAllCookies();
	        		driver.get().manage().window().maximize();
	        		Reporter.passStep("Launching Selenium Driver : Microsoft Edge");
	        	}catch(Exception err) {
	        		Reporter.failStep(" Launching Selenium Driver failed: Microsoft Edge" + " :: and the error is :: " + err.getMessage());
	        	}
	        	break;
	        default:
	        	try {
	        		DesiredCapabilities caps =new DesiredCapabilities();
	        		
		        		 ChromeOptions options = new ChromeOptions();
		        		 Map<String, Object> prefs = new HashMap<String, Object>();
		        		 Map<String, Object> profile = new HashMap<String, Object>();
		        		 Map<String, Object> contentSettings = new HashMap<String, Object>();
		        		    		
		        		 contentSettings.put("notifications", 1);
		        		 profile.put("managed_default_content_settings", contentSettings);
		        		 prefs.put("profile", profile);
		        		 options.setExperimentalOption("prefs", prefs);
		        		    
		        		caps.setCapability(ChromeOptions.CAPABILITY, options);
		        		options.addArguments("disable-infobars");
		        		options.addArguments("--disable-popup-blocking");
		        		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
		        		options.merge(caps); 
		        		System.setProperty("webdriver.chrome.driver", Utilities.def_Loc + "drivers\\chromedriver.exe");
		        		
		        		driver.set(new ChromeDriver(options));
		        		driver.get().manage().deleteAllCookies();
		        		driver.get().manage().window().maximize();
		        		Reporter.passStep("Launching Selenium Driver : Google Chrome");
	        	}catch(Exception e) {
	        		Reporter.failStep(" Launching Selenium Driver failed: Google Chrome" + " :: and the error is :: " + e.getMessage());
	        	}
	        	}
	}
/**
 * Method to close Current Browser.
 */

    public static void closeBrowser() {
			if(driver !=null) {
				try {
					driver.get().close();
					Reporter.infoStep("Driver closed successfully");
				}catch(Exception e) {
					Reporter.warningStep("Driver didn't closed successfully and the error is :: + " + e.getMessage());
				}
			}
}
/**
 * Method get browser name and version
 * <br> please put key "browsername" for "Browser Name"
 * <br> please put key "browserversion" for "Browser Version"
 * @return the browser version
 * @throws Throwable
 */

 public static Map<String, String> getBrowserNameAndVersion() throws Throwable
     { 
	     Map<String, String> values = new HashMap<>(); 
	     if(driver!=null) {
     try { 
    	 Capabilities cap= ((RemoteWebDriver) driver.get()).getCapabilities();
    	 values.put("browsername", cap.getBrowserName());
    	 values.put("browserversion", cap.getBrowserVersion());
    			 }catch(Exception err) {
    				 SuperAction.catchError(err);
    			 }
     }
	     return values;
	 }
 
}

