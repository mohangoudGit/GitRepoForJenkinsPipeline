package com.qa.hvcom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.hvcom.base.BaseTest;
import com.qa.hvcom.constants.AppConstants;
import com.qa.hvcom.pages.HomePage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("EPIC")  // you can just give the reference of the JIR EPIC
@Story("Story-100")// You can aslo give your reference to JIRA story
@Feature("Feature-50")// You can aslo give your reference to JIRA story
public class HomePageTest extends BaseTest{
	@Description("Verifying Product Tab") /* All annottaions are optional*/

	@Severity(SeverityLevel.MINOR)
	//@Test(priority=Short.MAX_VALUE)
	@Test(priority=1)
	public void HomePageTitletest() throws InterruptedException {

		

	//	String Text=homepage.getProductText();
		
		String ProductText=homepage.goToProducts();
		
		
		// since homepage is portected in base class
		//	we are able to access this method
		//System.out.println(Text);
		//Assert.assertEquals(Text,"Products");
		Assert.assertEquals(ProductText,"Block Storage");

	}

	
	@Description("Verifying Solutions Tab") /* All annottaions are optional*/
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void goToSolutionsTest() throws InterruptedException {



		String Text=homepage.goToSolutions();
		//	we are able to access this method
		//System.out.println(Text);
		Assert.assertEquals(Text,"Solutions");
		//Assert.assertEquals(Text,AppConstants.PRODUCT_NAME);

	}
	
	@Description("Verifying Services Tab") /* All annottaions are optional*/
	@Severity(SeverityLevel.MINOR)
	@Test(priority=3)
	public void goToServicesTest() throws InterruptedException {



		String Text=homepage.goToServices();
		
		homepage.goToServices();
		//	we are able to access this method
		//System.out.println(Text);
		Assert.assertEquals(Text,"Sapport Portal");
		//Assert.assertEquals(Text,AppConstants.PRODUCT_NAME);

	}

	
	@Description("Verifying Partners Tab") /* All annottaions are optional*/
	@Severity(SeverityLevel.MINOR)
	@Test(priority=4)
	public void goToPartnersTest() throws InterruptedException {


		Thread.sleep(5000);
		String Text=homepage.goToPartners();
		
		homepage.goToPartners();
		//	we are able to access this method
		//System.out.println(Text);
		Assert.assertEquals(Text,"Our Partner Ecosystem");
		//Assert.assertEquals(Text,AppConstants.PRODUCT_NAME);
		
		

	}

}
