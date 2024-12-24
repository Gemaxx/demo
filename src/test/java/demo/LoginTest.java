import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;

    @Test
    public void setup() {
        // Set up Edge WebDriver using WebDriverManager
        // This will automatically download the correct version of the Edge WebDriver
        System.setProperty("webdriver.edge.driver", "c:\\edgedriver_win64\\msedgedriver.exe"); // Optional, WebDriverManager will handle this

        // Set up Edge options (for headless mode, if you need it)
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");  // Run browser in headless mode if needed
        driver = new EdgeDriver(options);
        
        // Navigate to SauceDemo
        driver.get("https://www.saucedemo.com");
    }

    @DataProvider(name = "LoginData")
    public Object[][] fetchLoginData() {
        // Test data for valid and invalid login attempts
        return new Object[][] {
            {"standard_user", "secret_sauce", true}, // Valid credentials
            {"locked_out_user", "secret_sauce", false}, // Invalid credentials
            {"invalid_user", "wrong_password", false}  // Invalid credentials
        };
    }

    @Test(dataProvider = "LoginData")
    public void validateLogin(String username, String password, boolean expectedLoginSuccess) {
        // Locate username and password fields and login button
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Input username and password and click login
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        // Check the outcome based on expected login success
        if (expectedLoginSuccess) {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed!");
        } else {
            WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for invalid credentials!");
        }
    }

    @Test(dependsOnMethods = "validateLogin")
    public void tearDown() {
        // Close the browser after test execution
        driver.quit();
    }
}
