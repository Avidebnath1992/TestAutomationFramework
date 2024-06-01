package com.automation.modules;

import com.automation.framework.Page;
import com.automation.framework.Reporter;

public class LoginPage {
public static void loginToFacebook(String url) throws Throwable {
	if(Page.openUrl(url)) {
		Thread.sleep(3000);
		Reporter.passStep("FaceBook Portal Loaded", Reporter.takeScreenshot() );
	}
	else {
		Reporter.failStep("FaceBook Portal is not loaded" ,Reporter.takeScreenshot());
	}
}
}
