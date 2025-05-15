package com.vishwajay.jobbot;

import com.vishwajay.jobbot.ai.AIJobMatcher;
import com.vishwajay.jobbot.ai.AIUtils;
//import com.vishwajay.jobbot.ai.JobPreferences;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class JobSearchApplication {
    public static void main(String[] args) {
        // User preferences
        JobPreferences preferences = new JobPreferences("Software Quality Engineer", "Remote", "Tech", "Full-time");

        // Sample job descriptions
        List<String> jobDescriptions = Arrays.asList(
        	    "Software Quality Engineer, Remote, Tech, Full-time",
        	    "Frontend Developer at Amazon in Seattle, Part-time, Retail",
        	    "Data Scientist at Facebook in New York, Full-time, Tech industry",
        	    "QA Analyst at Infosys, Remote, Full-time, Tech"
        	);

      

        // AI Matcher
        AIJobMatcher matcher = new AIJobMatcher();
       // matcher.matchJobsWithPreferences(jobDescriptions, preferences);
        List<String> matchedJobs = matcher.matchJobsWithPreferences(jobDescriptions, preferences);

        if (matchedJobs.isEmpty()) {
            System.out.println("No jobs matched the preferences.");
        } else {
            System.out.println("Matched Jobs:");
            for (String job : matchedJobs) {
                System.out.println(job);
            }
        }
    }
}
