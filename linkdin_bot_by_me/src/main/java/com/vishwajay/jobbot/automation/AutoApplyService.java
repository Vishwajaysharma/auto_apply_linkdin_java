package com.vishwajay.jobbot.automation;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import com.vishwajay.jobbot.*;
import com.vishwajay.jobbot.utils.ExcelQuestionAnswerService;

import java.util.List;

public class AutoApplyService {

	ExcelQuestionAnswerService qaService = new ExcelQuestionAnswerService();

    public void applyToJobs(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initial wait for job cards to appear
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.cssSelector("div.job-card-container")
        ));

        int jobCount = driver.findElements(By.cssSelector("div.job-card-container")).size();

        for (int i = 0; i < jobCount; i++) {
            try {
                // Re-fetch job cards to avoid stale references
                List<WebElement> jobCards = driver.findElements(By.cssSelector("div.job-card-container"));
                WebElement card = jobCards.get(i);

                // Scroll and click job card
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
                wait.until(ExpectedConditions.elementToBeClickable(card)).click();

                // Locate Easy Apply button
                By byId = By.id("jobs-apply-button-id");
                By byAria = By.xpath("//div[@class='jobs-search__job-details--wrapper']/descendant::button[contains(@aria-label,'Easy Apply')]");
                WebElement applyBtn;

                try {
                    applyBtn = wait.until(ExpectedConditions.elementToBeClickable(byId));
                } catch (TimeoutException e) {
                    applyBtn = wait.until(ExpectedConditions.elementToBeClickable(byAria));
                }

                try {
                    applyBtn.click();
                } catch (ElementClickInterceptedException e) {
                    System.out.println("Apply button click intercepted—skipping.");
                    continue;
                }

                // Handle multi-step form until applied or skipped
                while (true) {
                    Thread.sleep(1000); // small pause for UI to settle

                    // Check for Applied confirmation
                    List<WebElement> applied = driver.findElements(By.xpath("//*[contains(text(),'Applied') or contains(text(),'Application submitted')]"));
                    if (!applied.isEmpty()) {
                        System.out.println("✅ Application submitted.");
                        break;
                    }

                    // Fill all question-based fields if present
                    List<WebElement> questionLabels = driver.findElements(By.cssSelector("label[for]"));
                    for (WebElement label : questionLabels) {
                        String question = label.getText().trim();
                        if (question.isEmpty()) continue;

                        try {
                            String inputId = label.getAttribute("for");
                            WebElement inputField = driver.findElement(By.id(inputId));
                            String answer = qaService.getOrAskAnswer(question);

                            inputField.clear();
                            inputField.sendKeys(answer);
                        } catch (NoSuchElementException ignored) {}
                    }

                    // Click Next or Submit
                    List<WebElement> nexts = driver.findElements(By.xpath("//button[normalize-space()='Next']"));
                    if (!nexts.isEmpty()) {
                        WebElement nxt = nexts.get(0);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nxt);
                        wait.until(ExpectedConditions.elementToBeClickable(nxt)).click();
                        continue;
                    }

                    List<WebElement> submits = driver.findElements(By.xpath("//button[normalize-space()='Submit application']"));
                    if (!submits.isEmpty()) {
                        WebElement sub = submits.get(0);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sub);
                        wait.until(ExpectedConditions.elementToBeClickable(sub)).click();
                        break;
                    } else {
                        System.out.println("Additional steps required—skipping this job.");
                        break;
                    }
                }

                // Close dialog after application
                List<WebElement> closes = driver.findElements(By.xpath("//button[@aria-label='Dismiss']"));
                if (!closes.isEmpty()) {
                    wait.until(ExpectedConditions.elementToBeClickable(closes.get(0))).click();
                }

            } catch (TimeoutException te) {
                System.out.println("Timeout—skipping job.");
            } catch (WebDriverException we) {
                System.out.println("WebDriver error: " + we.getMessage());
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            // Small delay before next application
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
