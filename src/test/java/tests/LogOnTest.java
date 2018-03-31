package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Base;
import pages.HomePage;
import pages.PasswordPage;
import pages.SignInPage;
import pages.SignedOnPage;

public class LogOnTest extends Base {

	public LogOnTest() {
		super();
	}

	@Test
	public void successfulLogOn() {
		test = extent.createTest("Log On -Successfull");
		HomePage home = new HomePage(driver);
		home.clickSignInTooltip();
		test.log(Status.PASS, "Sign-In Button Clicked");

		SignInPage signIn = new SignInPage(driver);
		signIn.verifySignInLable();
		String signlable = signIn.verifySignInLable();
		test.log(Status.PASS, "The Lable: " + signlable + " is present.");

		signIn.enterUserName();
		test.log(Status.PASS, "User Name: " + signIn.getUserNameValue() + " entered");

		signIn.clickSignInContinueButton();
		test.log(Status.PASS, "Sign-In Page Continue Button clicked.");

		PasswordPage passwrdPage = new PasswordPage(driver);
		passwrdPage.verifyPasswordPage();
		test.log(Status.PASS, "The Lable: " + passwrdPage.verifyPasswordPage() + " is present.");

		passwrdPage.enterPassword();
		test.log(Status.PASS, "User Name: " + passwrdPage.getPasswordValue() + " entered");

		passwrdPage.clickPasswordSignInButton();
		test.log(Status.PASS, "Password Page Sign-In Button clicked.");

		
		SignedOnPage signOnPage = new SignedOnPage(driver);
		signOnPage.verifyLogIn();
		test.log(Status.PASS, "The Lable: " + signOnPage.verifyLogIn() + " is present.");
	}
	
	//Invalid UserName
	
	//Invalid Password

}
