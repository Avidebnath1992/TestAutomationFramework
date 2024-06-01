package com.automation.framework;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sun.tools.sjavac.Log;

public class Reporter {
	private static ExtentSparkReporter sparkReporter = null;
	private static ExtentReports extentReports = null;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private static ExtentTest initModule = null;
	
	private static Logger log= Logger.getLogger(Reporter.class.getName());
	private static Handler consoleHandler=null;
	private static Handler fileHandler=null;
	private static SimpleFormatter simpleFormatter=null;
	
	private static String activeReportDir=null;
	
	/**Method for Normal Screenshot
	 * @return
	 * @throws Throwable
	 */
	public static String takeScreenshot() throws Throwable{
		try {
			Date date=new Date();
			SimpleDateFormat formatter =new SimpleDateFormat("dd.MM.yyyy hh-mm-ss");
			String formattedDate = formatter.format(date);
			File srcFile= ((TakesScreenshot)WebDriverWrapper.getWebDriver()).getScreenshotAs(OutputType.FILE);
			String scrnFolder=Utilities.def_Loc+"reports\\" + "\\Screenshots";
			String curImagePath=scrnFolder+"\\"+formattedDate+".png";
			String pngRelativePath=Utilities.def_Loc+"reports\\"+"\\Screenshots\\"+formattedDate+".png";
			FileUtils.copyFile(srcFile, new File(curImagePath));
			return pngRelativePath;
		}catch(Exception err) {
			System.out.println(err.toString());
		}
		return null;
	}
	
	/**Method for Highlighted Screenshot
	 * @return
	 * @throws Throwable
	 */
	public static String takeScreenshot(By elem) throws Throwable{
		WebElement ele=SuperAction.getElem(elem);
		try {
			JavascriptExecutor js= (JavascriptExecutor)WebDriverWrapper.getWebDriver();
			js.executeScript("arguments[0].setAttribute('style','background: yellow;border: 2px solid red;');", ele);
			Date date=new Date();
			SimpleDateFormat formatter =new SimpleDateFormat("dd.MM.yyyy hh-mm-ss");
			String formattedDate = formatter.format(date);
			File srcFile= ((TakesScreenshot)WebDriverWrapper.getWebDriver()).getScreenshotAs(OutputType.FILE);
			String scrnFolder=Utilities.def_Loc+"reports\\" + "\\Screenshots";
			String curImagePath=scrnFolder+"\\"+formattedDate+".png";
			String pngRelativePath=Utilities.def_Loc+"reports\\"+"\\Screenshots\\"+formattedDate+".png";
			FileUtils.copyFile(srcFile, new File(curImagePath));
			return pngRelativePath;
		}catch(Exception err) {
			System.out.println(err.toString());
		}
		return null;
	}
	
	/**
	 * Method to initialize a Report on Suite Level
	 * @param reportName
	 * @param reportTitle
	 * @throws Throwable
	 */

	public static synchronized void initReport(String reportName, String reportTitle) throws Throwable {
        try {
			String path = Utilities.def_Loc + "reports\\" + reportName;
			FileUtils.deleteDirectory(new File(path));
			Utilities.createDir(path);
			activeReportDir=path;
			sparkReporter= new ExtentSparkReporter(path + "\\"+reportName+".html");
			sparkReporter.config().setDocumentTitle(reportTitle);
			sparkReporter.config().setReportName(reportTitle);
			sparkReporter.config().setCss(reportTitle);
			extentReports=new ExtentReports();
			extentReports.attachReporter(sparkReporter);
			Reporter.createTC("======= Test Suite Initialization ====== ", "Test case for Enviornment Initialization.","");
			initModule("Initializing Report");
			passStep("Report has been initialized with report name as :: " + boldTxt(reportName)+".html");
	    }catch(Exception e) {
		    System.out.println(e.getMessage());
	    }
	}
	/**
	 * Method to add Report Info as "Enviornment", "Testers", "OS" as Key, Value basis.
	 * @param systemInfo
	 */
	public static void addInfo(Map<String,String> systemInfo) {
		for(Map.Entry<String, String> entry : systemInfo.entrySet()) {
			String key=entry.getKey();
			String value=entry.getValue();
			extentReports.setSystemInfo(key, value);
		}
	}
	/**
	 * Method to Create a Test Cases in Report
	 * @param name<br> Proper test Case which is easy to read
	 * <br> Eg. Login To FaceBook with right UserName
	 * @param tc_desc<br> Test Case description to justify the name
	 * @param category<br> Assign a category like "authentication","search"
	 * <br> it will help to organize the test cases
	 */
	public static void createTC(String name, String tc_desc, String category) {
		extentTest.set(extentReports.createTest(name,tc_desc));
		extentTest.get().assignCategory(category);
	}
	/**Method to create test node under a Test Case
	 * 
	 * @param ModuleName
	 */
	public static void initModule(String ModuleName) {
		initModule = extentTest.get().createNode(ModuleName);		
	}

	public static void passStep(String step) {
		initModule.pass(step);
	}
	
	public static void passStep(String step, String imagePath) {
		initModule.pass(step, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
	}
	/**Method to make any text bold on the HTML report
	 * @param txt
	 * @return
	 */
	public static String boldTxt(String txt) {
		return "<span style=\"font-weight:600 ! important;\">"+txt+"</span>";
	}
	public static void warningStep(String step) {
		initModule.warning(step);
	}
	public static void warningStep(String step, String imagePath) {
		initModule.warning(step, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
	}
	public static void failStep(String step) {
		initModule.fail(step);
	}
	public static void failStep(String step, String imagePath) {
		initModule.fail(step, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
	}
	public static void infoStep(String step) {
		initModule.info(step);
	}
	public static void infoStep(String step, String imagePath) {
		initModule.info(step, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
	}
	/**
	 * Method to build the HTML report
	 * <br> We will use after suite execution to build the report.
	 * @param url
	 * @param openReport
	 * @throws Throwable
	 */
	public static synchronized void endReport(String url,boolean openReport)throws Throwable{
		extentReports.flush();
		String filePath=url;
		if(filePath.contains("file:///")) {
			filePath=url.replace("file:///","");
		}
		Thread.sleep(1000);
		closeLogging();
	}
	
	/**Method to format the test data in HTML Format
	 * @param txt
	 * @return
	 */
	public static String testData(String txt) {
		return "<span style=\"color:blue ! important;font-weight:600!important;\">"+txt+"</span>";
	}
	
	/**Method to Initialize Java Logging.<p>
	 * @param logLevel
	 * <br> Log level as String
	 * <p><b>Notes:</p></br>
	 * Log Level hierarchy:<b>SEVERE,WARN,INFO,CONFIH,FINE</b></br>
	 * If user chooses to set any log level, it suppresses all level below it.<br>
	 * <b>Example: </b>Report.initLogging("WARN") would disable logs for INFO, CONFIG and FINE<br>
	 * user can choose to set the logging off:Report.initLogging("OFF");<br>
	 * user can choose to set all logging levels: Report.initLogging("ALL");<br>
	 * @throws IOException
	 */
	public static void initLogging(String logLevel, String logLoc)throws IOException{
		try {
			consoleHandler =new ConsoleHandler();
			fileHandler =new FileHandler();
			simpleFormatter=new SimpleFormatter();
			
			log.addHandler(consoleHandler);
			log.addHandler(fileHandler);
			fileHandler.setFormatter(simpleFormatter);
			
			switch(logLevel) {
			case "ALL":
				consoleHandler.setLevel(Level.ALL);
				fileHandler.setLevel(Level.ALL);
				log.setLevel(Level.ALL);
				break;
			case "CONFIG":
				consoleHandler.setLevel(Level.CONFIG);
				fileHandler.setLevel(Level.CONFIG);
				log.setLevel(Level.CONFIG);
				break;
			case "INFO":
				consoleHandler.setLevel(Level.INFO);
				fileHandler.setLevel(Level.INFO);
				log.setLevel(Level.INFO);
				break;
			case "WARN":
				consoleHandler.setLevel(Level.WARNING);
				fileHandler.setLevel(Level.WARNING);
				log.setLevel(Level.WARNING);
				break;
			case "FINE":
				consoleHandler.setLevel(Level.FINE);
				fileHandler.setLevel(Level.FINE);
				log.setLevel(Level.FINE);
				break;
			case "OFF":
				consoleHandler.setLevel(Level.OFF);
				fileHandler.setLevel(Level.OFF);
				log.setLevel(Level.OFF);
				break;
			case "SEVERE":
				consoleHandler.setLevel(Level.SEVERE);
				fileHandler.setLevel(Level.SEVERE);
				log.setLevel(Level.SEVERE);
				break;
			default:
				consoleHandler.setLevel(Level.ALL);
				fileHandler.setLevel(Level.ALL);
				log.setLevel(Level.ALL);
				break;
			}
		}catch(IOException exception) {
			log.log(Level.SEVERE,"Error occur in FileHandler.",exception);
		}
	}
	/**
	 * Method to add any log statement.Logs will be saved at 'ProjectDirectory/execLog.log'<p>
	 * @param logLevel
	 * <br> Level of Log as String. Should be in <b> SEVERE, WARN, INFO, CONFIG,FINE</b>
	 * @param Status
	 * <br> The status of the step; (if any) could be 'Pass', 'Fail' etc.
	 * @param msg
	 * <br>the log message as String
	 * <p><b>Example:</b>
	 * <br> Report.add2LogNote("CONFIG","Pass","Configuration Completed");
	 * @throws Exception
	 */
	public static void add2LogNote(String logLevel, String Status, String msg) {
		Date date= new Date();
		String timeStamp=date.toString();
		
		String updateMsg=timeStamp+ " : " +Status + " : " + msg;
		
		switch(logLevel) {
		case "ALL":
			log.log(Level.ALL, updateMsg);
			break;
		case "CONFIG":
			log.log(Level.CONFIG,updateMsg);
			break;
		case "INFO":
			log.log(Level.INFO,updateMsg);
			break;	
		case "WARN":
			log.log(Level.WARNING,updateMsg);
			break;
		case "FINE":
			log.log(Level.FINE,updateMsg);
			break;
		case "OFF":
			log.log(Level.OFF,updateMsg);
			break;
		case "SEVERE":
			log.log(Level.SEVERE,updateMsg);
			break;
		default:
			log.log(Level.ALL, updateMsg);
			break;
		}
	}
	public static void closeLogging() {
		fileHandler.close();
	}
}
