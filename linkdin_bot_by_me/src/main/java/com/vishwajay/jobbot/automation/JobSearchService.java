package com.vishwajay.jobbot.automation;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class JobSearchService {

    public void searchJobs(WebDriver driver, String jobTitle, String location) {
        // 1) Navigate to LinkedIn Jobs
        driver.get("https://www.linkedin.com/jobs/");

        // 2) Set up an explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 3) Wait for and fill in the keyword box
        By keywordLocator = By.cssSelector("input[aria-label='Search by title, skill, or company']");
        WebElement keywordInput = wait.until(ExpectedConditions.elementToBeClickable(keywordLocator));
        keywordInput.clear();
        keywordInput.sendKeys(jobTitle);

        // 4) Wait for and fill in the location box
        By locationLocator = By.cssSelector("input[aria-label='City, state, or zip code']");
        WebElement locationInput = wait.until(ExpectedConditions.elementToBeClickable(locationLocator));
        //locationInput.clear();
        //locationInput.sendKeys(location);

        // 5) Submit the search
        locationInput.sendKeys(Keys.ENTER);

        // 6) Click "All filters" button
        By allFilterBtn = By.xpath("//button[contains(.,'All filters')]");
        WebElement allFilter = wait.until(ExpectedConditions.elementToBeClickable(allFilterBtn));
        allFilter.click();

        // 7) Use JS to find and toggle "Easy Apply" if it's OFF
        String jsScript = """
            let toggle = document.evaluate(
                "//h3[normalize-space()='Easy Apply']/following::input[@role='switch' and @type='checkbox'][1]",
                document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;

            if (toggle && toggle.getAttribute('aria-checked') === 'false') {
                toggle.click();
                return 'Toggled ON';
            } else if (toggle) {
                return 'Already ON';
            } else {
                return 'Toggle not found';
            }
        """;

        String toggleResult = (String) js.executeScript(jsScript);
        System.out.println("JS Toggle result: " + toggleResult);

        // 8) Click "Show results" or close filter pane if needed
        try {
            WebElement showResults = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Show results')]")
            ));
            showResults.click();
            
            
            
            
            
            
        } catch (TimeoutException ignored) {
            // If no "Show results" button is there, click "All filters" again to close
            allFilter.click();
        }

        // 9) Wait until job cards are loaded
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.cssSelector("div.job-card-container")
        ));
    }
}
