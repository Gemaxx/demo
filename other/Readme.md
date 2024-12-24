## **Test SauceDemo Website with TestNG**

### **Project Members**

**Section**: 9

| **Name**                      | **ID**    |
| ----------------------------- | --------- |
| Ahmed Ibrahim Metwally Negm   | 322223887 |
| Ibrahim Abouzeid Hassan Ahmed | 318180097 |
| Amira Tawfik Mohamed          | 322223685 |

---

### **Project Description**

This project demonstrates the use of **TestNG** to automate testing of key functionalities in [**SauceDemo**](https://www.saucedemo.com), a fictional e-commerce website. The goal is to verify that users can log in, browse products, add them to the cart, and complete a checkout process. The tests will also include fetching test data from an **Excel sheet** using **Apache POI**.

---

### **Test Cases Table (Before Execution)**

| **Test Case ID** | **Test Case Description**               | **Input Data**      | **Expected Output**        | **Dependency** |
| ---------------- | --------------------------------------- | ------------------- | -------------------------- | -------------- |
| TC001            | Validate login with correct credentials | Username, Password  | Successful login message   | Excel data     |
| TC002            | Validate login with invalid credentials | Username, Password  | Error message displayed    | None           |
| TC003            | Verify adding products to the cart      | Product name        | Product added successfully | TC001          |
| TC004            | Check cart contents                     | None                | Correct items in the cart  | TC003          |
| TC005            | Test checkout process                   | Name, Address, Card | Order placed successfully  | TC004          |
| TC006            | Test logout functionality               | Logged-in session   | Redirected to login page   | TC001          |

---

### **Dependencies in `pom.xml`**

```xml
<dependencies>
    <!-- TestNG Dependency -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.7.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Apache POI for Excel -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>5.3.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.3.0</version>
    </dependency>

    <!-- Selenium for Browser Automation -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.27.0</version>
    </dependency>
</dependencies>
```

---

### **Sample TestNG Code**

#### **Login Test (TC001 and TC002)**

```java
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

    @DataProvider(name = "LoginData")
    public Object[][] fetchLoginData() {
        // Replace with code to fetch data from Excel
        return new Object[][] {
            {"standard_user", "secret_sauce"},
            {"locked_out_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "LoginData")
    public void validateLogin(String username, String password) {
        System.out.println("Logging in with: " + username);
        boolean loginSuccess = performLogin(username, password);
        if (username.equals("standard_user")) {
            Assert.assertTrue(loginSuccess, "Login failed for valid credentials.");
        } else {
            Assert.assertFalse(loginSuccess, "Login succeeded for invalid credentials.");
        }
    }

    private boolean performLogin(String username, String password) {
        // Simulated login logic (use Selenium for actual login)
        return username.equals("standard_user") && password.equals("secret_sauce");
    }
}
```

#### **Cart Test (TC003 and TC004)**

```java
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest {

    @Test(dependsOnMethods = {"validateLogin"})
    public void validateAddToCart() {
        System.out.println("Adding product to the cart.");
        boolean productAdded = addProductToCart("Sauce Labs Backpack");
        Assert.assertTrue(productAdded, "Failed to add product to cart.");
    }

    @Test(dependsOnMethods = {"validateAddToCart"})
    public void validateCartContents() {
        System.out.println("Checking cart contents.");
        boolean productInCart = isProductInCart("Sauce Labs Backpack");
        Assert.assertTrue(productInCart, "Product not found in cart.");
    }

    private boolean addProductToCart(String productName) {
        // Simulated logic for adding product to cart (use Selenium for actual interaction)
        return true;
    }

    private boolean isProductInCart(String productName) {
        // Simulated logic for verifying product in cart (use Selenium for actual interaction)
        return true;
    }
}
```

---

### **Screenshots**

- **Execution Output**: Attach screenshots of the test case execution.
- **Reports**: Include screenshots of generated **TestNG** reports showing passed/failed test cases.

---

### **Deliverables Checklist**

1. **Documentation**
   - Project name. ✅
   - Team members, section, and IDs. ✅
   - Detailed project description. ✅
   - Test case table (before execution). ✅
   - Code snippets. ✅
   - Screenshots of execution and reports.

