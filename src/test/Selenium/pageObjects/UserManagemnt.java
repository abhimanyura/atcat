package pageObjects;

import org.openqa.selenium.By;

public class UserManagemnt {
	public static By admin=By.xpath("//a//span[text()='Administration']");
	public static By userManagement=By.xpath("//a/span[text()='User Management']");
	public static By manageUser=By.xpath("//*[@id='manageUser']");
	public static By addUser=By.xpath("//button[@type='submit' and @class='btn btn-add-user btn-add menu-filter-table']");
	public static By userName=By.xpath("//*[@id='inputSuccess username']");
	public static By email=By.xpath("//*[@id='inputSuccess email']");
	public static By password=By.xpath("//*[@id='inputSuccess password']");
	public static By confirmPassword=By.xpath("//*[@id='inputSuccess confirm']");
	public static By firstName=By.xpath("//*[@id='inputSuccess firstname']");
	public static By lastname=By.xpath("//*[@id='inputSuccess lastname']");
	public static By jobTitle=By.xpath("//*[@id='inputSuccess jobtitle']");
	public static By companyName=By.xpath("//*[@id='inputSuccess companyname']");
	public static By addressLine1=By.xpath("//*[@id='inputSuccess addressline1']");
	public static By addressline2=By.xpath("//*[@id='inputSuccess addressline2']");
	public static By city=By.xpath("//*[@id='inputSuccess city']");
	public static By state=By.xpath("//*[@id='inputSuccess state']");
	public static By country=By.xpath("//*[@id='country']");
	public static By zip=By.xpath("//*[@id='inputSuccess zip']");
	public static By phone1=By.xpath("//*[@id='inputSuccess phone1']");
	public static By phone2=By.xpath("//*[@id='inputSuccess phone2']");
	public static By fax=By.xpath("//*[@id='inputSuccess fax']");
	public static By isActive=By.xpath("//*[@id='inputSuccess Active']");
	public static By lastLogon=By.xpath("//*[@id='inputSuccess lastlogon']");
	public static By passwordNeverExpires=By.xpath("//*[@id='inputSuccess passwordneverexpires']");
	public static By userRole=By.xpath("//*[@id='role']");
	public static By addUserButton=By.xpath("//button[@class='btn btn-primary' and text()='Add User']");
	public static By sortBy=By.xpath("//*[@class='form-control menu-filter-select '] ");
	public static By uesrListTable=By.xpath("//table[@id='user']");
	public static By editedEmail=By.xpath("//*[@class='form-control Exp-input' and @id='inputSuccess email']");
	public static By editSaveButton=By.xpath("//*[@id='Save']");
	public static By closeButton=By.xpath("//div[@class='modal-header']//button[@class='close']");
	public static By closeButton1=By.xpath("//h4[@id='UserDetailsLabel']//following::button[@class='close']/span[text()='×']");
	public static By searchButton=By.xpath("//*[@id='searchUserText']");
	public static By activeUser=By.xpath("//span[text()='Active Users']//preceding-sibling::span");
	public static By msgPanel=By.xpath("//div[@role='alert']");
	public static By viewEmail=By.xpath("//h4[@class='modal-title pageHeader']//following::input[@id='inputSuccess email']");
	public static By viewUserRole=By.xpath("//h4[@class='modal-title pageHeader']//following::select[@id='role']");
	public static By viewFirstName=By.xpath("//h4[@class='modal-title pageHeader']//following::input[@id='inputSuccess firstname']");
	public static By viewuserName=By.xpath("//h4[@class='modal-title pageHeader']//following::input[@id='inputSuccess username']");
	public static By totalUserCount=By.xpath("//span[text()='Total Users']//preceding-sibling::span[@class='username']");
	public static By activeUserCount=By.xpath("//span[text()='Active Users']//preceding-sibling::span[@class='username']");
	public static By inactiveUserCount=By.xpath("//span[text()='Inactive Users']//preceding-sibling::span[@class='username']");
	public static By pazeSizeDropdown=By.xpath("//select[@name='datatable1_length']");
	public static By pazeForward=By.xpath("//li/a[text()='7']//following::li/a[text()='⟩']");
	public static By messagePanel=By.xpath("//div[@id='validate']//div");
	public static By searchIcon=By.xpath("//span[@class='glyphicon glyphicon-search']");
	public static By edit_Btn(int i) 
	{ 
		return By.xpath("//*[@id='Edit"+i+"']"); 
		
	} 

}
