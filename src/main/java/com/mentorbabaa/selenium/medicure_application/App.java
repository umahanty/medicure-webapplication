package com.mentorbabaa.selenium.medicure_application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class App 
{
	private WebDriver driver;

    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "D:\\Software\\chromedriver-win64\\chromedriver.exe");

        // Set Chrome options for headless mode
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless"); // Add headless argument
        driver = new ChromeDriver(options);
        
        driver.get("http://52.91.30.29:8086");
    }

    public void testFormSubmission() {
        // Valid input test
        testForm(driver, "Alice Smith", "1234567890", "alice.smith@example.com", "This is a test message.");

        // Invalid input tests
        testForm(driver, "Alice123", "1234567890", "alice.smith@example.com", "This is a test message."); // Invalid name
        testForm(driver, "Alice Smith", "12345abc", "alice.smith@example.com", "This is a test message."); // Invalid mobile number
        testForm(driver, "Alice Smith", "1234567890", "alice.smith@example", "This is a test message."); // Invalid email
        testForm(driver, "Alice Smith", "1234567890", "alice.smith@example.com", ""); // Invalid message
    }

    public void testForm(WebDriver driver, String name, String mobile, String email, String message) {
        driver.navigate().refresh(); // Refresh the page before each test

        WebElement nameField = driver.findElement(By.id("Your Name"));
        WebElement mobileField = driver.findElement(By.id("Phone Number"));
        WebElement emailField = driver.findElement(By.id("Email"));
        WebElement messageField = driver.findElement(By.id("Message"));
        WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));

        nameField.clear();
        mobileField.clear();
        emailField.clear();
        messageField.clear();

        StringBuilder errorDescription = new StringBuilder("No_Errors");

        if (isValidName(name)) {
            nameField.sendKeys(name);
        } else {
            errorDescription.append("_Invalid_Name_").append(name);
            System.out.println("Invalid name: " + name);
        }

        if (isValidMobile(mobile)) {
            mobileField.sendKeys(mobile);
        } else {
            errorDescription.append("_Invalid_Mobile_").append(mobile);
            System.out.println("Invalid mobile number: " + mobile);
        }

        if (isValidEmail(email)) {
            emailField.sendKeys(email);
        } else {
            errorDescription.append("_Invalid_Email_").append(email);
            System.out.println("Invalid email: " + email);
        }

        if (isValidMessage(message)) {
            messageField.sendKeys(message);
        } else {
            errorDescription.append("_Invalid_Message_").append(message);
            System.out.println("Invalid message: " + message);
        }

        // Take a screenshot after filling the form fields
        takeScreenshot(errorDescription.toString());

        sendButton.click();

        // Optionally, verify if the page scrolled to the top
        long scrollPosition = (Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;");
        if (scrollPosition == 0) {
            System.out.println("Page scrolled to the top successfully.");
        } else {
            System.out.println("Page did not scroll to the top.");
        }
    }

    public void takeScreenshot(String errorDescription) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filename = "screenshot_" + errorDescription + "_" + timestamp + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destDir = new File("screenshots");

        if (!destDir.exists()) {
            destDir.mkdirs(); // Create the screenshots directory if it doesn't exist
        }

        try {
            FileUtils.copyFile(srcFile, new File(destDir, filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidName(String name) {
        return name.matches("[a-zA-Z\\s]+");
    }

    public boolean isValidMobile(String mobile) {
        return mobile.matches("\\d+");
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    public boolean isValidMessage(String message) {
        return !message.trim().isEmpty();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.setUp();
        app.testFormSubmission();
        app.tearDown();
    }
}
