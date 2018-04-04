package tests;

import java.io.IOException;

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

	@Test(priority=0)
	public void successfulLogOn() {
		test = extent.createTest("Log On -Successfull");
		test.log(Status.PASS, "Navigate to Test URL");
		HomePage home = new HomePage(driver);
		home.clickSignInTooltip();
		test.log(Status.PASS, "Sign-In Button Clicked");

		SignInPage signIn = new SignInPage(driver);
		signIn.verifySignInLable();
		String signlable = signIn.verifySignInLable();
		test.log(Status.PASS, "The Lable: " + signlable + " is present.");

		String validuser=prop.getProperty("expecteduser");	
		signIn.enterUserName(validuser);
		test.log(Status.PASS, "User Name: " + signIn.getUserNameValue() + " entered");

		signIn.clickSignInContinueButton();
		test.log(Status.PASS, "Sign-In Page Continue Button clicked.");

		PasswordPage passwrdPage = new PasswordPage(driver);
		passwrdPage.verifyPasswordPage();
		test.log(Status.PASS, "The Lable: " + passwrdPage.verifyPasswordPage() + " is present.");

		String psswrd = prop.getProperty("password");
		passwrdPage.enterPassword(psswrd);
		test.log(Status.PASS, "User Name: " + passwrdPage.getPasswordValue() + " entered");

		passwrdPage.clickPasswordSignInButton();
		test.log(Status.PASS, "Password Page Sign-In Button clicked.");

		
		SignedOnPage signOnPage = new SignedOnPage(driver);
		signOnPage.verifyLogIn();
		test.log(Status.PASS, "The Lable: " + signOnPage.verifyLogIn() + " is present.");
	}
	
	//Invalid UserName
	@Test(priority=1)
	public void invalidUserName() throws IOException {
		test = extent.createTest("Invalid UserName - Verify Error Message Displayed");
		test.log(Status.PASS, "Navigate to Test URL");
		HomePage home = new HomePage(driver);
		home.clickSignInTooltip();
		test.log(Status.PASS, "Sign-In Button Clicked");

		SignInPage signIn = new SignInPage(driver);
		signIn.verifySignInLable();
		String signlable = signIn.verifySignInLable();
		test.log(Status.PASS, "The Lable: " + signlable + " is present.");
		
		String invaliduser=prop.getProperty("incorrectuser");
		signIn.enterUserName(invaliduser);
		test.log(Status.PASS, "User Name: " + signIn.getUserNameValue() + " entered");

		signIn.clickSignInContinueButton();
		test.log(Status.PASS, "Sign-In Page Continue Button clicked.");
		
		String screenshotname="invalidUserName";
		signIn.verifyErrorMessage();
		test.log(Status.PASS, "Error message displayed: " +signIn.getErrorUserMessage());
		test.addScreenCaptureFromPath(capture(driver,screenshotname));
		
	}
		
	//Invalid Password
	@Test(priority=3)
	public void invalidPassword() throws IOException {
		test = extent.createTest("Invalid Password - Verify Error Message Displayed");
		test.log(Status.PASS, "Navigate to Test URL");
		HomePage home = new HomePage(driver);
		home.clickSignInTooltip();
		test.log(Status.PASS, "Sign-In Button Clicked");

		SignInPage signIn = new SignInPage(driver);
		signIn.verifySignInLable();
		String signlable = signIn.verifySignInLable();
		test.log(Status.PASS, "The Lable: " + signlable + " is present.");

		String validuser=prop.getProperty("expecteduser");	
		signIn.enterUserName(validuser);
		test.log(Status.PASS, "User Name: " + signIn.getUserNameValue() + " entered");

		signIn.clickSignInContinueButton();
		test.log(Status.PASS, "Sign-In Page Continue Button clicked.");

		PasswordPage passwrdPage = new PasswordPage(driver);
		passwrdPage.verifyPasswordPage();
		test.log(Status.PASS, "The Lable: " + passwrdPage.verifyPasswordPage() + " is present.");
		
		String invalidpsswrd = prop.getProperty("invlaidPassword");
		passwrdPage.enterPassword(invalidpsswrd);
		test.log(Status.PASS, "User Name: " + passwrdPage.getPasswordValue() + " entered");

		passwrdPage.clickPasswordSignInButton();
		test.log(Status.PASS, "Password Page Sign-In Button clicked.");
		
		String screenshotname="invalidPassword";
		signIn.verifyErrorMessage();
		test.log(Status.PASS, "Error message displayed: " +signIn.getErrorUserMessage());
		test.addScreenCaptureFromPath(capture(driver,screenshotname));		
	}

}
