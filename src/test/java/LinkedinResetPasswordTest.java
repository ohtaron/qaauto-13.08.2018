import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinResetPasswordTest {

    WebDriver driver;
    LinkedinLoginPage linkedinLoginPage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void successfulResetPasswordTest() throws InterruptedException {
        String newPassword = "Test123!";

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "LoginPage is not loaded.");

        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage =
                linkedinLoginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isLoaded(),
                "RequestPasswordResetPage is not loaded.");

        LinkedinPasswordResetSubmitPage linkedinPasswordResetSubmitPage =
                linkedinRequestPasswordResetPage.findAccount("linkedin.tst.yanina@gmail.com");


        sleep(180000);
        Assert.assertTrue(linkedinPasswordResetSubmitPage.isLoaded(),
                "PasswordResetSubmitPage is not loaded.");

        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage =
                linkedinPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isLoaded(),
                "SetNewPasswordPage is not loaded.");

        LinkedinSuccessfulPasswordResetPage linkedinSuccessfulPasswordResetPage =
                linkedinSetNewPasswordPage.submitNewPassword(newPassword);
        Assert.assertTrue(linkedinSuccessfulPasswordResetPage.isLoaded(),
                "SuccessfulPasswordResetPage is not loaded.");

        LinkedinHomePage linkedinHomePage =
                linkedinSuccessfulPasswordResetPage.clickOnGoToHomeButton();
        //sleep(180000);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),
                "HomePage is not loaded.");


    }
}
