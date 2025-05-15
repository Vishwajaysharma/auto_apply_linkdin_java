package com.vishwajay.jobbot.automation;

import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.Random;

public class HumanSimulator {

    private static final Random random = new Random();

    // Simulate typing like a human (randomized typing speed)
    public static void typeLikeHuman(WebElement element, String text) throws InterruptedException {
        for (char c : text.toCharArray()) {
            element.sendKeys(Character.toString(c));
            // Random delay between each character (simulate human typing speed)
            Thread.sleep(100 + random.nextInt(200));
        }
    }

    // Simulate clicking like a human (with a random delay)
    public static void clickLikeHuman(WebElement element) throws InterruptedException {
        // Random delay before click
        Thread.sleep(200 + random.nextInt(500));
        element.click();
    }

   
   
    // Simulate pressing a key like a human (e.g., Enter, Tab, etc.)
    public static void pressKeyLikeHuman(int keyCode) throws InterruptedException, AWTException {
        Robot robot = new Robot();
        robot.keyPress(keyCode);
        Thread.sleep(100 + random.nextInt(150));  // Random delay
        robot.keyRelease(keyCode);
    }

    // Simulate mouse hover like a human (hover over an element)
    public static void hoverOverElementLikeHuman(WebElement element) throws InterruptedException, AWTException {
        org.openqa.selenium.Point location = element.getLocation();
        int x = location.getX();
        int y = location.getY();
        moveMouseLikeHuman(x, y);
        Thread.sleep(500 + random.nextInt(500)); // Random delay for hover
    }

 // Simulate moving mouse like a human (random movement)
    public static void moveMouseLikeHuman(int x, int y) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        int moveX = x + random.nextInt(50) - 25;
        int moveY = y + random.nextInt(50) - 25;
        robot.mouseMove(moveX, moveY);
        Thread.sleep(100 + random.nextInt(300));
    }

    // Simulate scrolling like a human
    public static void scrollLikeHuman(int scrollAmount) throws InterruptedException, AWTException {
        Robot robot = new Robot();
        robot.mouseWheel(scrollAmount);
        Thread.sleep(200 + random.nextInt(400)); // Random delay
    }
}
