package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class LinkedinRequestPasswordResetPage extends LinkedinBasePage {

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userEmailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    public LinkedinRequestPasswordResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        return findAccountButton.isDisplayed()
                && getCurrentTitle().equals("Reset Password | LinkedIn")
                && getCurrentUrl().contains("uas/request-password-reset");
    }

    public LinkedinPasswordResetSubmitPage findAccount(String userEmail) {
        gMailService.connect();

        userEmailField.sendKeys(userEmail);
        findAccountButton.click();

        return new LinkedinPasswordResetSubmitPage(driver);
    }
}
