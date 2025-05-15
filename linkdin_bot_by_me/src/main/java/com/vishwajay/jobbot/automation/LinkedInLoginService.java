package com.vishwajay.jobbot.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedInLoginService {

    public void login(WebDriver driver, String email, String password) throws InterruptedException {
        driver.get("https://www.linkedin.com/login");

        Thread.sleep(2000); // Simulate human delay
        
        WebElement emailField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Simulate human-like typing
        HumanSimulator.typeLikeHuman(emailField, email);
        HumanSimulator.typeLikeHuman(passwordField, password);

        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        HumanSimulator.clickLikeHuman(signInButton);  // Simulate human click

        Thread.sleep(3000); // Wait for login process to complete
    }
}
