package scripts;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import driver.EnvironmentVariables;
import driver.GenericMethods;
import driver.GenericMethods2;
import methods.ClientManagement;
import methods.Login;
import methods.SystemParameterMethod;
import methods.UserManagementMethod;
import pageObjects.UserManagemnt;


public class UserManagement {
	
	WebDriver driver;
	Login l=new Login();

	UserManagementMethod un=new UserManagementMethod();
	GenericMethods2 gm = new GenericMethods2();
	SystemParameterMethod sm=new SystemParameterMethod();
	ClientManagement cm=new ClientManagement();
	ExtentReports reports ;
	ExtentTest logger ;
	@BeforeTest
	//This part will initialize the driver,navigate to the URL and logging in the application.
	public void Open() throws InterruptedException 
	{		
		
		reports=new ExtentReports(EnvironmentVariables.extendReportsPath);									               
		logger=reports.startTest("Tesitng the Extend Reports");
		
		System.setProperty(EnvironmentVariables.driverType, EnvironmentVariables.driverPath);
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("user-data-dir=C:\\Users\\abhimanyu.raj\\AppData\\Local\\Google\\Chrome\\ automation");
//		options.addArguments("--start-maximized");
		driver = new ChromeDriver();


//		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://atcattest-us.accenture.com/ATCAT_UI1/#");
		JOptionPane.showMessageDialog(null, "Please press okay when the url is loaded");
		l.login(driver);
	}
//	,dataProvider="TestData"
	@Test(description ="UserMangement",dataProvider="TestData" )
	public void UserMangementt(String MandatoryFieldErrorMessage,String username,String email,String password,String passwordErrorMessage,String formattedPassword,String firstName,String lastName,String active,String userRole,String UserRoleValues,String addUserMessage,String zip,String incorrectPasswordMessage,String editFormButton,String resetPasswordMessage,String testMailMeassge,String editSaveMessage) throws InterruptedException
//	String username,String email,String password,String firstName,String lastName,String active,String userRole,String editFormButton,String resetPasswordMessage,String testMailMeassge,String editSaveMessage
//String mandatoryField,String clientProfileLabels,String itDetailsLabels,String ClientDomain,String companyName,String industryType,String isActive,String reportingCurrency,String addressLine1,String addressLine2,String city,String state,String country,String IsMultiCurrency,String zip,String phone1,String phone2,String fax,String webURL,String disclaimer
//	,String	databaseServerName,String ftpServer,String jobServiceMachine,String dataLoadFileServerName
	{
//un.AddUser(driver, logger,MandatoryFieldErrorMessage,username, email, password,passwordErrorMessage,formattedPassword, firstName, lastName, active, userRole,UserRoleValues,addUserMessage,zip,incorrectPasswordMessage);
un.editUser(driver, logger, username,editFormButton, resetPasswordMessage, testMailMeassge, editSaveMessage);
//	cm.addUser(driver, logger, mandatoryField, clientProfileLabels, itDetailsLabels, ClientDomain, companyName, industryType,isActive,reportingCurrency, addressLine1, addressLine2, city, state, country,IsMultiCurrency, zip, phone1, phone2, fax, webURL, disclaimer, databaseServerName, ftpServer, jobServiceMachine, dataLoadFileServerName);
//		cm.searchandClickClient(logger, driver, ClientDomain,"edit");
		un.countUser(driver,logger);
//		un.sort(driver, logger);
	}
//	@Test(description ="sort")
//	public void script(String editFormButton)throws InterruptedException
//	{
////	un.sort(driver, logger);
////		un.editUser(driver,logger,editFormButton);
////		un.delete(driver,"test");
////		sm.verifyLabels(driver,"8");
//	}
	@DataProvider(name = "TestData")
	public Object[][] getValueFromExcel(ITestContext context)
	{
		String sheetName = "UserManagement";// context.getCurrentXmlTest().getParameter("sheetName").toString().trim();
		Object[][] data = gm.getExcelData(EnvironmentVariables.dataPoolPath, sheetName);		
		return data;
	}
	@AfterClass
	public void tearDown()
	{
		reports.endTest(logger);
		reports.flush();
	}
}
