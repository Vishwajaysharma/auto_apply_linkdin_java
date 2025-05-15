package com.vishwajay.jobbot;

import com.vishwajay.jobbot.*;
import com.vishwajay.jobbot.automation.AutoApplyService;
import com.vishwajay.jobbot.automation.JobSearchService;
import com.vishwajay.jobbot.automation.LinkedInLoginService;
import com.vishwajay.jobbot.automation.StealthDriverFactory;
import com.vishwajay.jobbot.question.QuestionAnswerService;
import com.vishwajay.jobbot.utils.ExcelQuestionAnswerService;

//import com.vishwajay.jobbot.utils.QuestionAnswerService;
//import com.vishwajay.jobbot.utils.question.;
import org.openqa.selenium.WebDriver;

public class JobApplicationProcess {

    private LinkedInLoginService loginService = new LinkedInLoginService();
    private JobSearchService jobSearchService = new JobSearchService();
    private AutoApplyService autoApplyService = new AutoApplyService();
   // private QuestionAnswerService qaService = new QuestionAnswerService();
    ExcelQuestionAnswerService qaService = new ExcelQuestionAnswerService();
    
    
    public void startApplicationProcess(String email, String password, String jobTitle, String location) {
        // Create a stealth browser driver
        StealthDriverFactory.createStealthDriver();
        WebDriver driver = StealthDriverFactory.createStealthDriver();
        
        try {
            // Login to LinkedIn
            loginService.login(driver, email, password);

            // Search for jobs
            jobSearchService.searchJobs(driver, jobTitle, location);

            // Apply to jobs
            autoApplyService.applyToJobs(driver);

            // Handle questions asked during the process
            handleQuestions();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           // driver.quit();
        }
    }

   
    private void handleQuestions() {
        // Example questions to be handled automatically
    	 String[] questions = {
    	    	    "What is your expected salary?",
    	    	    "Why do you want to work here?",
    	    	    "What are your strengths?",
    	    	    "What are your weaknesses?"
    	    	};

    	    	for (String question : questions) {
    	    	    String answer = qaService.getOrAskAnswer(question);
    	    	    // Use the answer where needed...
    	    	}
//        String[] questions = {
//            "What is your expected salary?",
//            "Why do you want to work here?",
//            "What are your strengths?",
//            "What are your weaknesses?"
//        };
//
//        for (String question : questions) {
//            qaService.answerQuestion(question);
      //  }
    }
}
