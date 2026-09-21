package baseTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//Log4j
import org.apache.logging.log4j.Logger;//Log4j
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;



public class BaseTest {
	
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	public Logger logger;
	public Properties property;
	
	
	public static WebDriver getDriver() {
        return tlDriver.get();
    }
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"browser","OS"})
	public void setUp(@Optional("chrome")String br,@Optional("windows")String os) throws IOException {
		
		ChromeOptions options= new ChromeOptions();
		// Disable password saving & leak detection prompts
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);
		options.setExperimentalOption("prefs", prefs);

		// Disable automation banners and extra bubbles
		options.addArguments("--disable-save-password-bubble");
		
		//config.properties
		FileReader reader = new FileReader("./src/test/resources/config.properties");
		property = new Properties();
				property.load(reader);
		
		//log4j
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		File file = new File("src/test/resources/log4j2.xml");
		context.setConfigLocation(file.toURI());
		logger = LogManager.getLogger(this.getClass());
		
		
		
		/*
		switch(br.toLowerCase()) {
		case "chrome": driver= new ChromeDriver(options);break;
		case "edge": driver= new EdgeDriver();break;
		case "firefox": driver= new FirefoxDriver();break;
		default: System.out.println("invalid browser"); return;
		//or
		//default: throw new IllegalArgumentException("Invalid browser"+br);
		}
		
		
		
		
		driver.manage().deleteAllCookies();//delete all cookies from webpage
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(property.getProperty("baseURL"));
		driver.manage().window().maximize();
		*/
		
		
		
	        // Line 3: Declare a local variable initialized to null
	        WebDriver driver = null;
	        
	        if(property.getProperty("execution_env").equalsIgnoreCase("remote")) {
	        	DesiredCapabilities capabilities = new DesiredCapabilities();
	        	//OS
	        	if(os.equalsIgnoreCase("Windows")) {
	        		capabilities.setPlatform(Platform.WINDOWS);
	        	}else if(os.equalsIgnoreCase("Mac")) {
	        		capabilities.setPlatform(Platform.MAC);
	        	}else {
	        		System.out.println("Mismatch operationg system");
	        		return;
	        	}
	        	
	        	//browser
	        	switch(br.toLowerCase()) {
	        	case "chrome": capabilities.setBrowserName("chrome");break;
	        	case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
	        	case "firefox": capabilities.setBrowserName("firefox");break;
	        	default: System.out.println("Mismatch browser");return;
	        	}
	        	driver= new RemoteWebDriver(new URL(property.getProperty("grid")),capabilities);
	        }

	        if(property.getProperty("execution_env").equalsIgnoreCase("local")) {
	        // Line 4: Switch statement to create the specific browser instance
	        switch (br.toLowerCase()) {
	            case "chrome":
	                driver = new ChromeDriver(options);
	                break;
	            case "edge":
	                driver = new EdgeDriver();
	                break;
	            case "firefox":
	                driver = new FirefoxDriver();
	                break;
	            default:
	                System.out.println("Invalid browser name provided.");
	                return;
	        }
	        }

	        // Line 5: Store the newly created driver inside the ThreadLocal box for this thread
	        tlDriver.set(driver);

	        // Line 6: Fetch the driver for this thread and interact with the browser
	        driver.manage().deleteAllCookies();
	        getDriver().manage().window().maximize();
	        getDriver().get(property.getProperty("baseURL"));
	    
	}
	
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		//driver.quit();
		getDriver().quit();
        tlDriver.remove();
	}
	
	
	public String randomString() {
		String randomstring= RandomStringUtils.insecure().nextAlphabetic(5);
		return randomstring;
	}
	
	
	public String randomNumber() {
		String randomnum= RandomStringUtils.insecure().nextNumeric(10);
		return randomnum;
	}
	
	public String randomAlphaNumberic() {
		/*String randomalphanum= RandomStringUtils.insecure().nextAlphanumeric(6);
		return randomalphanum;*/
		//or
		String randomalpha= RandomStringUtils.insecure().nextAlphabetic(5);
		String randomnumeric= RandomStringUtils.insecure().nextNumeric(2);
		return randomalpha+"@"+randomnumeric;
	}
	
	//different ways to get dynamic data for email
	/*
	public String dynamicEmail(String prefix) {
		String email= prefix + "@gmail.com";
		return email;
	}
	
	public String dynamicEmail1() {
		String email= randomString()+ "@gmail.com";
		return email;
	}
	
	public String dynaminEmail2() {
		String email= "user" + System.currentTimeMillis()+"@gmail.com";
		return email;
	}
	*/
	
	public void captureBrowserConsoleLogs() {
        try {
            LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
            logger.error("--- CAPTURING CHROME BROWSER CONSOLE LOGS ---");
            for (LogEntry entry : logEntries) {
                logger.error("[BROWSER " + entry.getLevel() + "] " + entry.getMessage());
            }
        } catch (Exception e) {
            logger.error("Could not fetch browser logs: " + e.getMessage());
        }
    }
	
	public String captureScreenshot(String tName) {
		/*
		SimpleDateFormat df = new SimpleDateFormat("YYYY.MM.DD.HH.MM.SS");
		Date dt= new Date();
		String timeStamp= df.format(dt);
		*/
		
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ts= (TakesScreenshot)getDriver();
		File sourceFile= ts.getScreenshotAs(OutputType.FILE);
		String targetPath= System.getProperty("user.dir")+"\\screenshots\\"+tName+"_"+timeStamp+".png";
		File targetFile= new File(targetPath);
		sourceFile.renameTo(targetFile);
		
		return targetPath; 
				
	}

}
