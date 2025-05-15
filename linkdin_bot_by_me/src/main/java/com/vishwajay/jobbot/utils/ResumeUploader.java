package com.vishwajay.jobbot.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResumeUploader {
    public void uploadResume(WebDriver driver, String resumePath) {
        try {
            WebElement uploadInput = driver.findElement(By.xpath("//input[@type='file']"));
            uploadInput.sendKeys(resumePath);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Resume upload skipped or not required: " + e.getMessage());
        }
    }
}

