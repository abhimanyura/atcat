package methods;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import driver.GenericMethods;
import driver.GenericMethods2;
import pageObjects.UserManagemnt;

public class UserManagementMethod {
	UserManagemnt um=new UserManagemnt();
	GenericMethods2 gm=new GenericMethods2();

	//	This method will add a user.
	/**
	 * 
	 * @param driver
	 * @param username It will store the user name of new user.
	 * @param email It will store the email id of new user.
	 * @param password It will store the Password.
	 * @param firstName It will store the first name of new user.
	 * @param lastName It will store the last name of new user.
	 * @param active It will store the value as yes/no .based on this user will be marked as active.
	 * @param userRole It will store the role which needs to be assigned to the particular user.
	 */
	public void AddUser(WebDriver driver,ExtentTest logger,String MandatoryFieldErrorMessage,String username,String email,String password,String passwordErrorMessage,String formattedPassword,String firstName,String lastName,String active,String userRole,String UserRoleValues,String addUserMessage,String zip,String incorrectPasswordMessage)
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(um.admin).click();
		driver.findElement(um.userManagement).click();
		WebElement userManagement=driver.findElement(um.manageUser);
		userManagement.click();
		String beforAddTotalCount=driver.findElement(um.totalUserCount).getText().toString();
		String intialActiveUserCount=driver.findElement(um.activeUserCount).getText().toString();
		String intialInactiveUserCount=driver.findElement(um.inactiveUserCount).getText().toString();
		driver.findElement(um.addUser).click();
		boolean flg=driver.findElement(um.lastLogon).isEnabled();
		System.out.println(flg);
		if(!flg)
		{
			gm.logPass(logger, "Last log on field is disabled");
		}
		else
		{
			gm.logFail(logger, "Last log on field is enabled");
		}
		driver.findElement(um.addUserButton).click();
		System.out.println(driver.findElement(um.messagePanel).getText().toString());
		//This part will verify the error message getting displayed after clicking the add user without entering the mandatory field.		
		if(driver.findElement(um.messagePanel).getText().toString().contains(MandatoryFieldErrorMessage))
		{
			gm.logPass(logger,"Correct error message is shown when user click the add user button without entering the mandatory fields.");
		}
		else
		{
			gm.logFail(logger,"Incorrect error message is shown when user click the add user button without entering the mandatory fields.",driver);
		}
		//This list will contain the mandatory fields displayed on add user form		
		List<WebElement> mandatoryFields=driver.findElements(By.xpath("//div[@class='col-xs-4 col-md-2 txt-align-right  pad10'and contains(text(),'*')]"));
		//This array will contain the expected mandatory fields.			
		String[] mandatoryFieldsValue={"UserName","Email","Password","Confirm Password","User Role"};
		//This loop will validate whether expected mandatory fields are actually mandatory or not. 		
		for(int i=0;i<mandatoryFields.size();i++)
		{
			if(mandatoryFields.get(i).getText().toString().contains(mandatoryFieldsValue[i]))
			{
				gm.logPass(logger,mandatoryFieldsValue[i]+" is a mandatory field");
			}
			else
			{
				gm.logFail(logger, mandatoryFieldsValue[i]+" is not a mandatory field", driver);	
			}	
		}
		driver.findElement(um.userName).sendKeys(username);
		//Here we are passing the wrong email id 
		driver.findElement(um.email).sendKeys("abhimanyu@xyz.com");
		driver.findElement(um.password).sendKeys(password);
		//Here we are passing the wrong password in confirm password field		
		driver.findElement(um.confirmPassword).sendKeys("abhimanyu");
		driver.findElement(um.addressLine1).click();
		//This part will handle the alert pop up and verify the message displayed which will get displayed after entering the wrong password. 		
//		Alert alert=driver.switchTo().alert();		 
//		//		String alertMessage=driver.switchTo().alert().getText().toString();
//		//		System.out.println(alertMessage);
//		//		if(alertMessage.contains("Password you entered doesn't match!! Please Re-enter"))
//		//		{
//		//			System.out.println("in if ");
//		//			gm.logScreenshot("Pass", "Correct message is getting dsipalyed after entering the wrong password", driver);
//		//		}
//		//
//		//		else
//		//		{
//		//			System.out.println("else");
//		//			gm.logScreenshot("Fail", "Correct message is not getting dsipalyed after entering the wrong password", driver);
//		//		}
//		alert.dismiss();
		
		driver.findElement(um.addUserButton).click();
		if(driver.findElement(um.messagePanel).getText().toString().contains(passwordErrorMessage))
		{
			gm.logPass(logger,"Correct error message is shown when user Enters different value in passowrd and confirm password filed");
		}
		else
		{
			gm.logFail(logger,"Incorrect error message is shown when user Enters different value in passowrd and confirm password field",driver);
		}
		driver.findElement(um.confirmPassword).clear();
		driver.findElement(um.confirmPassword).sendKeys(password);		
		driver.findElement(um.email).clear();
		driver.findElement(um.email).sendKeys("abhimanyu");
		driver.findElement(um.firstName).sendKeys(firstName);       		
		if(driver.findElement(um.isActive).isSelected())
		{
			gm.logPass(logger, "Active radio button is clicked on opening add user form");
		}
		else
		{
			gm.logFail(logger, "Active radio button is not clicked on opening add user form");
		}
		if(active.contains("yes"))
		{
			if(driver.findElement(um.isActive).isSelected())
			{
				gm.logPass(logger, "Active radio button is already clicked on opening add user form");
				
			}
			else
			{
				driver.findElement(um.isActive).click();
				gm.logFail(logger, "Active radio button is not checked on opening add user form");
			}
		}
		else
		{
			if(driver.findElement(um.isActive).isSelected())
			{
				gm.logPass(logger, "Active radio button is already clicked on opening add user form");
				driver.findElement(um.isActive).click();	
			}
			else
			{
				gm.logFail(logger, "Active radio button is not checked on opening add user form");
				gm.logPass(logger, "User is set as inactive");
			}
			gm.logInfo(logger,"User is set as inactive");
		}
		driver.findElement(um.addUserButton).click();
		String incorrectEmailMessage=driver.findElement(um.messagePanel).getText().toString();
		System.out.println(incorrectEmailMessage);
		if(incorrectEmailMessage.contains("Email address must be in the format a@b.c"))
		{
			gm.logPass(logger,"Correct message is getting shown when we enter the wrong email");
		}
		else
		{
			gm.logFail(logger,"InCorrect message is getting shown when we enter the wrong email",driver);
		}
		driver.findElement(um.email).clear();
		driver.findElement(um.email).sendKeys(email);
		Select userRoleUI=new Select(driver.findElement(um.userRole));
		List<WebElement> role=userRoleUI.getOptions();
		String actualUserRole[]=UserRoleValues.split(",");
		for(int x=0;x<role.size();x++)
		{
			if((role.get(x).getText().toString().contains(actualUserRole[x])))
			{
				gm.logPass(logger, actualUserRole[x]+" is displayed inside clint adminstartor dropdown");
			}
			else
			{
				gm.logFail(logger, actualUserRole[x]+" is not displayed inside clint adminstartor dropdown",driver);
			}
		}
		userRoleUI.selectByVisibleText(userRole);
		driver.findElement(um.addUserButton).click();
		String incorrectPasswordMessageUi=driver.findElement(um.messagePanel).getText().toString().trim();
		System.out.println(incorrectPasswordMessageUi);
		if(incorrectPasswordMessageUi.contains(incorrectPasswordMessage))
		{
			gm.logPass(logger, "The meesage shown after entering the password format is correct");
		}
		
		else
		{
			gm.logFail(logger, "The meesage shown after entering the  password format is correct"); 
		}
		driver.findElement(um.password).clear();
		driver.findElement(um.password).sendKeys(formattedPassword);
		driver.findElement(um.confirmPassword).clear();
		driver.findElement(um.confirmPassword).sendKeys(formattedPassword);
		driver.findElement(um.zip).sendKeys("1234567878910");
		driver.findElement(um.addUserButton).click();
		String zipMesaage=driver.findElement(um.messagePanel).getText().toString();
		if(zipMesaage.contains("ZIP code should contain only numbers and should be between 5-9 digits"))
		{
			gm.logPass(logger,"Correct message is getting displayed when we enter the more number of character in zip code");
		}
		else
		{
			gm.logFail(logger,"InCorrect message is getting displayed when we enter the more number of character in zip code");	
		}
		driver.findElement(um.zip).clear();
		driver.findElement(um.zip).sendKeys(zip);
		driver.findElement(um.addUserButton).click();
		String addUserMessageUi=driver.findElement(um.messagePanel).getText().toString();
		System.out.println(addUserMessageUi+"added user ui message");
//		JOptionPane.showMessageDialog(null, "please hit the add button manually");
		if(addUserMessage.contains(addUserMessage))
		{
			gm.logPass(logger,"Correct Successful message is getting displayed after adding the user");
		}
		else
		{
			gm.logFail(logger,"Incorrect message is getting displayed after adding the user", driver);
		}
		driver.findElement(um.closeButton).click();
		String afterAddUserTotalCount=driver.findElement(um.totalUserCount).getText().toString();
		String afterAddingUserAciveUserCount=driver.findElement(um.activeUserCount).getText().toString();
		String afterAddUserInactiveUserCount=driver.findElement(um.inactiveUserCount).getText().toString();
		System.out.println(Integer.parseInt(beforAddTotalCount));
		if(Integer.parseInt(afterAddUserTotalCount)>Integer.parseInt(beforAddTotalCount))
		{
			gm.logPass(logger, "Total count is getting updated after addition of user");
		}
		else
		{
			gm.logFail(logger, "Total count is not getting updated after addition of user");
		}
		if(active.equals("yes"))
		{
			if(Integer.parseInt(intialActiveUserCount)<Integer.parseInt(afterAddingUserAciveUserCount))
			{
				gm.logPass(logger, "Total count of active user is getting updated after addition of user");
			}
			else
			{
				gm.logFail(logger, "Total count active user is not getting updated after addition of user");
			}	
		}
		else
		{
			if(Integer.parseInt(intialInactiveUserCount)<Integer.parseInt(afterAddUserInactiveUserCount))
			{
				gm.logPass(logger, "Total count of inactive user is getting updated after addition of user");
			}
			else
			{
				gm.logFail(logger, "Total count inactive user is not getting updated after addition of user");
			}	
			
		}
		driver.findElement(um.searchButton).click();
		driver.findElement(um.searchButton).sendKeys(username);
		driver.findElement(um.searchIcon).click();
		boolean flag=searchUser(driver,username,"view");
		System.out.println("flag value is " +flag);
		if(flag)
		{
			gm.logPass(logger, "Added user is present in the User table on UI.");
		}
		else
		{
			gm.logFail(logger,"Added user is not present in the User table on UI.",driver);
		}
		if(driver.findElement(um.viewuserName).isEnabled())
		{
			gm.logFail(logger, "User is able to edit the username");
		}
		else
		{
			gm.logPass(logger, "User is not able to edit the username");
		}
		if(driver.findElement(um.viewEmail).getAttribute("value").toString().equals(email))
		{
			gm.logPass(logger, "The email field is having the same value which is enterd while adding the user");
		}
		else
		{
			gm.logFail(logger, "The email field is not having the same value which is enterd while adding the user",driver);
		}
		
		if(driver.findElement(um.viewFirstName).getAttribute("value").toString().equals(firstName))
		{
			
			gm.logPass(logger, "The First name field is having the same value which is enterd while adding the user");
		}
		else
		{
			gm.logFail(logger, "The First name field is not having the same value which is enterd while adding the user",driver);
		}
		if(driver.findElement(um.zip).getAttribute("value").toString().equals(zip))
		{
			gm.logPass(logger, "The zip  field is having the same value which is enterd while adding the user");	
		}
		else
		{
			gm.logFail(logger, "The zip field is not having the same value which is enterd while adding the user",driver);
		}
		Select userRoleUI1=new Select(driver.findElement(um.viewUserRole));
		
		if(userRoleUI1.getFirstSelectedOption().getText().equals(userRole))
		{
			gm.logPass(logger, "In user role dropdown the same option is slected which is selected while adding the user");
		}
		else
		{
			gm.logFail(logger, "In user role dropdown the different option is slected other than the selected while adding the user",driver);
		}
		driver.findElement(um.closeButton1).click();
	}
	public void countUser(WebDriver driver,ExtentTest logger) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(um.admin).click();
		driver.findElement(um.userManagement).click();
		driver.findElement(um.manageUser).click();
		String beforAddTotalCount=driver.findElement(um.totalUserCount).getText().toString();
		Select pazesize=new Select(driver.findElement(um.pazeSizeDropdown));
		pazesize.selectByVisibleText("10");
		int totalCount=Integer.parseInt(beforAddTotalCount);
		int pagenumeber=totalCount/10;
		String finalPageNumber;
		if(totalCount%10==0)
		{
			finalPageNumber=Integer.toString(pagenumeber);
		}
		else
		{
			finalPageNumber=Integer.toString(pagenumeber+1);
		}
		System.out.println("Final page number is "+finalPageNumber);
		int i=1;
		if(Integer.parseInt(finalPageNumber)>7)
		{
			int extrapage=Integer.parseInt(finalPageNumber)%7;
			while(i<8)
			{
			driver.findElement(um.pazeForward).click();
			i++;
			}
			while(extrapage!=0)
			{
				driver.findElement(um.pazeForward).click();
				extrapage--;
			}
			boolean flag1=driver.findElement(By.xpath("//li/a[text()="+finalPageNumber+"]//following::li[@class='disabled']/a[text()='⟩']")).isDisplayed();			
			if(flag1)
			{
				gm.logPass(logger,"Page number  displayed is correct and after last page the '>' button is displayed and it is disabled");
				
			}
			else
			{
				gm.logFail(logger,"Page number  displayed is correct but after last page the '>' button is enabled",driver);
			}
		}
		else
		{
			boolean flag=driver.findElement(By.xpath("//li/a[text()="+finalPageNumber+"]")).isDisplayed();
			boolean flag1=driver.findElement(By.xpath("//li/a[text()="+finalPageNumber+"]//following::li/a[text()='⟩']")).isDisplayed();
			if(flag&&flag1)
			{
				gm.logPass(logger,"Page number  displayed is correct and after last page the '>' button is displayed and it is disabled");
				
			}
			else
			{
				gm.logFail(logger,"Page number  displayed is correct but after last page the '>' button is enabled",driver);
			}
		}
	
	}
	public boolean searchUser(WebDriver driver,String username,String action )
	{
		WebElement mytable = driver.findElement(um.uesrListTable);
		//To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();
		boolean flag=false;
		//Loop will execute till the last row of table.
		for (int row=1; row<rows_count; row++)
		{
			//To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			//To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();	
			System.out.println(columns_count+"column count"+Columns_row+"row count");
			System.out.println(Columns_row.get(1).getText().toString().trim());
			if(action.equalsIgnoreCase("search"))
			{ if(Columns_row.get(1).getText().toString().trim().contains(username))
			 {
				 flag=true;
			 }
				
			}
			if(action.equalsIgnoreCase("view"))
			{ if(Columns_row.get(1).getText().toString().trim().contains(username))
			 {
				 flag=true;
				 Columns_row.get(1).click();
				 Columns_row.get(1).findElement(By.tagName("a")).click();
			 }
				
			}
			if(action.equalsIgnoreCase("edit"))
			{ if(Columns_row.get(1).getText().toString().trim().contains(username))
			 {
				 flag=true;
				 Columns_row.get(1).click();
				 Columns_row.get(1).findElement(By.tagName("a")).click();
			 }
				
			}
	}
	return flag;
	}
	public void userDetails(WebDriver driver)
	{
		
	}
	
	public void sort(WebDriver driver,ExtentTest logger) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a//span[text()='Administration']")).click();
		driver.findElement(By.xpath("//a/span[text()='User Management']")).click();
		WebElement userManagement=driver.findElement(By.id("manageUser"));
		userManagement.click();
////		driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-trash']")).click();	
//		Select sortBySelect=new Select(driver.findElement(um.sortBy));
//		sortBySelect.selectByVisibleText("UserName (A-Z)");
//		sortByName(driver,logger);
//		sortBySelect.selectByVisibleText("Roles");
//		sortByRole(driver,logger);
		clickAtTable(driver, logger, 1);
//		sortByName(driver, logger);
//		clickAtTable(driver, logger, 5);
		sortByRole(driver, logger);
		
		
	}
	public void clickAtTable(WebDriver driver,ExtentTest logger,int col)
	{
		WebElement mytable = driver.findElement(um.uesrListTable);
		//To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();
		System.out.println(rows_count);
		List<WebElement> Columns_row = rows_table.get(0).findElements(By.tagName("th"));
		Columns_row.get(col).click();	
		
	}
	public void delete(WebDriver driver,String userName,ExtentTest logger) throws InterruptedException
	{
		driver.findElement(um.admin).click();
		WebElement userManagement=driver.findElement(By.id("manageUser"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",userManagement);
		driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-trash']")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//div[@class='alert alert-invalid1']")).getText().toString().trim().contains("No Users selected !!"))
		{
			gm.logPass(logger,"Correct message is getting displayed when we click delete button without slecting any user ",driver);
		}
		else
		{
			gm.logFail(logger,"Correct message is not getting displayed when we click delete button without slecting any user ",driver);
		}
		deleteByUsername(driver,userName);
		driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-trash']")).click();
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.xpath("//div[@class='alert alert-invalid1']")).getText().toString().trim());


	}
	public void deleteByUsername(WebDriver driver,String userName)
	{
		WebElement mytable = driver.findElement(um.uesrListTable);
		//To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();

		String firstName[] = new String[rows_count] ;	  
		//Loop will execute till the last row of table.
		for (int row=1; row<rows_count; row++)
		{
			//To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			//To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();		

			if(Columns_row.get(1).getText().toString().trim().contains(userName))
			{
				Columns_row.get(0).click();
			}

		}
	}
	public void userTable(WebDriver driver)
	{
		
	}
	public void sortByName(WebDriver driver,ExtentTest logger)
	{
		WebElement mytable = driver.findElement(um.uesrListTable);
		//To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();

		String userName[] = new String[rows_count] ;	  
		//Loop will execute till the last row of table.
		for (int row=1; row<rows_count; row++)
		{
			//To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			//To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();		

			//Loop will execute till the last cell of that specific row.
			//	   for (int column=0; column<columns_count; column++)
			//	   {
			//To retrieve text from that specific cell.
			userName[row-1]= Columns_row.get(1).getText().toString().trim();
			//System.out.println("Cell Value Of row number "+row+" and column number "+"column"+" Is "+firstName[row-1]);
		}

		//FirstLetter will store the record first name first letter.
		String firstLetter;
		//We will store the First letter in character.		
		char a;
		//This array will store the ASCII value of all the first letter of First name of records 	
		int[] firstLetterAscii=new int[userName.length];
		//This Loop will iterate through the first name of every record  find its first letter and store its ASCII value in array.		
		for(int j=0;j<rows_count-1;j++)
		{
			if(userName[j]!= null || !userName[j].equals(""))
			{
				System.out.println(userName[j]);
				try
				{
					firstLetter = String.valueOf(userName[j].charAt(0)).toLowerCase();
					a=firstLetter.charAt(0);
					firstLetterAscii[j]=(int)a; 
				}
				catch(Exception e)
				{
					firstLetter=null;
					firstLetterAscii[j]=0; 
				}
			}
		}
		System.out.println(rows_count);
		//This for loop will compare the ASCII value of all the first letter stored and find wether it is sorted or not. 
		for(int j=0;j<rows_count-1;j++)
		{
			System.out.println(firstLetterAscii[j]);
			//This condition will test whether the first letter is not null	
			if(j<rows_count-2)
			{
			if(firstLetterAscii[j]!=0)
			{
				System.out.println("the value at "+j+" is"+firstLetterAscii[j]+"and at i+1 is  "+firstLetterAscii[j+1]);
				//This condition will compare the ASCII value of array elements 				
				if(firstLetterAscii[j]<=firstLetterAscii[j+1])
				{
						gm.logPass(logger,"The Records are sorted correctly according to Username.");
				}
				else
				{
						gm.logFail(logger,"The Records is not sorted correctly according to Username.",driver);
				}
			}

		}
		}
	}
	public void sortByRole(WebDriver driver,ExtentTest logger)
	{
		WebElement mytable = driver.findElement(um.uesrListTable);
		//To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();
		int roleId[]=new int[rows_count];
		String role[] = new String[rows_count] ;	  
		//Loop will execute till the last row of table.
		for (int row=1; row<rows_count; row++)
		{
			//To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			//To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();		

			//Loop will execute till the last cell of that specific row.
			//	   for (int column=0; column<columns_count; column++)
			//	   {
			//To retrieve text from that specific cell.

			role[row-1]= Columns_row.get(5).getText().toString().trim();
			System.out.println("Cell Value Of row number "+row+" and column number "+"column"+" Is "+role[row-1]);
			if(role[row-1]=="Client Administrator"||role[row-1]=="ServiceUser")
			{
				roleId[row-1]=2;
			}
			if(role[row-1]=="supervisor"||role[row-1]=="SuperUser")
			{
				roleId[row-1]=1;
			}
			if(role[row-1]=="reader"||role[row-1]=="ServiceReader")
			{
				roleId[row-1]=3;
			}
			if(role[row-1]=="auditor")
			{
				roleId[row-1]=4;
			}
		}
		for(int j=0;j<rows_count-1;j++)
		{
			if(roleId[j]<=roleId[j+1])
			{
				gm.logPass(logger,"Records are sorted correclty on role basis");
			}
		}
	}
	public void editUser(WebDriver driver,ExtentTest logger,String userName,String editFormButton,String resetPasswordMessage,String testMailMeassge,String editSaveMessage) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(um.admin).click();
		driver.findElement(um.userManagement).click();
		driver.findElement(um.manageUser).click();
		
		searchUser(driver, userName, "edit");
		Thread.sleep(2000);
		try
		{
			if(!driver.findElement(By.xpath("//input[@value='"+userName+"']")).isEnabled())
			{
					gm.logPass(logger,"The User name field is disabled");
			}
		}
		catch(Exception e)
		{
			gm.logFail(logger,"The User name field is Enabled",driver);
		}
		String Email=driver.findElement(um.editedEmail).getAttribute("value");
		driver.findElement(um.editedEmail).click();
		driver.findElement(um.editedEmail).sendKeys(Keys.CONTROL + "a");
		driver.findElement(um.editedEmail).sendKeys(Keys.DELETE);
		driver.findElement(um.editedEmail).sendKeys("edited"+Email);
		String []button=editFormButton.split(",");
		List<WebElement> uiEditFormtbutton=driver.findElements(By.xpath("//div[@class='modal-footer']/button[@class='btn btn-primary']"));
		for(int x=0;x<button.length;x++)
		{
			if(uiEditFormtbutton.get(x).getText().toString().equalsIgnoreCase(button[x]))
			{
				gm.logPass(logger,uiEditFormtbutton.get(x).getText().toString()+"Button is present on Edit User form");
			}
			else
			{
				gm.logFail(logger,uiEditFormtbutton.get(x).getText().toString()+"Button is present on Edit User form");
			}
		}
		uiEditFormtbutton.get(0).click();
		String resetPasswordMessageUI=driver.findElement(um.msgPanel).getText().toString();
		if(resetPasswordMessageUI.contains(resetPasswordMessage))
		{
			gm.logPass(logger, "Correct message is getting displayed After clicking the reset password ");
		}
		else
		{
			gm.logFail(logger, "Correct message is not getting displayed After clicking the reset password ");	
		}
		uiEditFormtbutton.get(1).click();
		String testMailMeassgeUI=driver.findElement(um.msgPanel).getText().toString();
		if(testMailMeassgeUI.contains(resetPasswordMessage))
		{
			gm.logPass(logger, "Correct message is getting displayed After clicking the Tset Mail button");
		}
		else
		{
			gm.logFail(logger, "InCorrect message is not getting displayed After clicking the Tset Mail button",driver);	
		}
		String beforeEditActiveusercount=driver.findElement(um.activeUserCount).getText();
		List<WebElement> activeCheckbox=driver.findElements(By.xpath("//input[@type='checkbox'][@id='inputSuccess Active']"));
		
		boolean flag=activeCheckbox.get(0).isSelected(); 
		System.out.println("Flag value is"+flag);
		if(flag)
		{
			activeCheckbox.get(0).click();
			driver.findElement(um.editSaveButton).click();
			String editSuccessMsgUi=driver.findElement(um.msgPanel).getText().toString().trim();
			System.out.println("Message dispalyed is"+editSuccessMsgUi);
			if(editSuccessMsgUi.contains(editSaveMessage))
			{
				gm.logPass(logger, "Correct messge is getting displayed after saving the edited user");
			}
			else
			{
				gm.logFail(logger, "Correct messge is not getting displayed after saving the edited user", driver);
			}
			String afterEditActiveusercount=driver.findElement(um.activeUser).getText();
			if(Integer.parseInt(afterEditActiveusercount)<Integer.parseInt(beforeEditActiveusercount))
			{
				gm.logPass(logger, "Active user count is getting updated correctly after editing the user");
			}
			else
			{
				gm.logFail(logger, "Active user count is not getting updated correctly after editing the user",driver);
			}
		}
		else
		{
			activeCheckbox.get(0).click();
			driver.findElement(um.editSaveButton).click();
			String editSuccessTextUi=driver.findElement(um.msgPanel).getText().toString().trim();
			System.out.println("Message dispalyed is"+editSuccessTextUi);
			if(editSuccessTextUi.contains(editSaveMessage))
			{
				gm.logPass(logger, "Correct messge is getting displayed after saving the edited user");
			}
			else
			{
				gm.logFail(logger, "Correct messge is not getting displayed after saving the edited user", driver);
			}
			Thread.sleep(50000);
			String afterEditActiveusercount=driver.findElement(um.activeUser).getText();
			System.out.println("After edit user count is "+Integer.parseInt(afterEditActiveusercount)+"Before"+beforeEditActiveusercount);
			if(Integer.parseInt(afterEditActiveusercount)>Integer.parseInt(beforeEditActiveusercount))
			{
				gm.logPass(logger, "Active user count is getting updated correctly after editing the user");
			}
			else
			{
				gm.logFail(logger, "Active user count is not getting updated correctly after editing the user",driver);
			}
		}
		driver.findElement(um.closeButton1).click();
	}
	public String editTable(WebDriver driver)
	{
		WebElement mytable = driver.findElement(um.uesrListTable);
		//To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();

		String userName = null;	  
		//Loop will execute till the last row of table.
		for (int row=1; row<2; row++)
		{
			//To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			//To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();		

			//Loop will execute till the last cell of that specific row.
			//	   for (int column=0; column<columns_count; column++)
			//	   {
			//To retrieve text from that specific cell.

			userName= Columns_row.get(1).getText().toString().trim();
			//			if(tempUserName.contains(userName))
			//			{
			//				Columns_row.get(1).click();
			//				break;
			//			}

		}
		return userName;


	}
	public void facebook(WebDriver driver) throws InterruptedException
	{
		//		driver.findElement(By.xpath("//*[@id='email']")).click();
		//		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("arclk500winie@gmail.com");
		//		driver.findElement(By.xpath("//*[@id='pass']")).click();
		//		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("mathematics@4");
		//		
		//		driver.findElement(By.xpath("//input[@value='Log In']")).click();
		//	Thread.sleep(4000);

	}
	public void doDoubleClick(WebDriver driver){
		WebElement body = driver.findElement(By.tagName("body"));
		Actions actions= new Actions(driver).doubleClick(body);
		actions.build().perform();
	}
}
