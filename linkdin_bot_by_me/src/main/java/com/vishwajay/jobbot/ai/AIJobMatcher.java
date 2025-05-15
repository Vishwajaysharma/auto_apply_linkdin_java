package com.vishwajay.jobbot.ai;

import com.vishwajay.jobbot.JobPreferences;
import java.util.List;
import java.util.ArrayList;

public class AIJobMatcher {

    // Now returns the list of matched jobs
    public List<String> matchJobsWithPreferences(List<String> jobDescriptions, JobPreferences preferences) {
        List<String> matchedJobs = new ArrayList<>();

        for (String jobDescription : jobDescriptions) {
            if (isJobMatching(jobDescription, preferences)) {
                matchedJobs.add(jobDescription);
            }
        }

        return matchedJobs;
    }

    // Matching logic
    private boolean isJobMatching(String jobDescription, JobPreferences preferences) {
        return jobDescription.contains(preferences.getJobTitle()) &&
               jobDescription.contains(preferences.getLocation()) &&
               jobDescription.contains(preferences.getIndustry()) &&
               jobDescription.contains(preferences.getJobType());
    }
}
