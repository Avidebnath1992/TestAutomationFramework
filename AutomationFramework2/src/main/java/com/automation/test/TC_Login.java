package com.automation.test;


import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.framework.Reporter;
import com.automation.framework.Utilities;
import com.automation.framework.WebDriverWrapper;
import com.automation.modules.LoginPage;
public class TC_Login extends Utilities{
    @BeforeClass(groups = "regression")
    @Parameters({"suitename","OS","browserName","userName","reportTitle"})
	public void initialize(String suitename,String OS,String browserName,String userName,String reportTitle) throws Throwable{
		String reportName=suitename +"_"+ browserName;
		String logloc=def_Loc+"reports\\"+reportName+"\\"+reportName+".txt";
		System.setOut(new PrintStream(new FileOutputStream("output.txt")));
		
		Reporter.initReport(suitename+"_"+browserName,reportTitle);
		Reporter.initLogging("ALL", logloc);
		WebDriverWrapper.initBrowser(browserName);
		//String browserVersion=WebDriverWrapper.getBrowserNameAndVersion().get("browserversion");
		
		Map<String,String> params=new HashMap<String, String>();
		params.put("OS", OS);
		params.put("Browser Name", browserName);
		//params.put("Browser Version", browserVersion);
		params.put("User Name", userName);
		params.put("Suite Name", suitename);
		
		Reporter.addInfo(params);
	}
    
    @Test(groups= {"regression"})
    public void LoginToFacebook()throws Throwable{
    	String tc_title="Login to Facebook with valid credential";
    	String tc_desc="This test case will test the Facebook login feature with valid user credential";
    	
    	//Get Data
    	Map<String, String> data=Utilities.readPropertyFile("testdata/data.properties","=");
    	
    	//Create test case feature in report
        Reporter.createTC(tc_title, tc_desc, "Login");
        
        //Step1
        Reporter.initModule("Given the Facebook login page Appears");
        LoginPage.loginToFacebook(data.get("url"));
    }
    
    @AfterClass(groups= {"regression"})
    @Parameters({"suitename","browserName"})
    public void TearDownTest(String suitename,String browserName) throws Throwable{
    	String reportname=suitename+"_"+browserName;
    	String reportloc=def_Loc+"reports\\"+reportname+"\\"+reportname+".html";
    	if(browserName.equalsIgnoreCase("firefox")) {
    		Reporter.endReport("file:///"+reportloc, true);
    	}
    	else {
    		Reporter.endReport(reportloc, true);
    	}
    	Thread.sleep(3000);
    	Utilities.deleteFile(def_Loc+"reports",reportname+".zip");
    	WebDriverWrapper.getWebDriver().quit();
    }
    	
    }
