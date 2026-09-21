package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseTest.BaseTest;

public class ExtentReportsWithListener implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String reportName;
	
	public void onStart(ITestContext context) {
		/*
		SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.DD.HH.MM.SS");
		Date dt= new Date();
		String timeStamp= df.format(dt);
		*/
		
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		reportName = "Report-"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter("./reports/"+reportName);
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation");
		sparkReporter.config().setReportName("Automation report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application","OpenCart");
		extent.setSystemInfo("Environmenet", "QA");
		extent.setSystemInfo("User", System.getProperty("user.name"));
		
		String OS= context.getCurrentXmlTest().getParameter("OS");
		extent.setSystemInfo("Operating syatem", OS);
		
		String browser= context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups= context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
		
		
	}
	
	public void onTestSuccess(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "The method executed pass is"+result.getName());
	
		
	}
	
	
	public void onTestFailure(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "The method executed failed is"+result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {
			
			String path= new BaseTest().captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(path);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "The method executed skipped is"+result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
		String report= System.getProperty("user.dir")+"/reports/"+reportName;
		File file= new File(report);
		try {
			Desktop.getDesktop().browse(file.toURI());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
