/*
 * JobPreferences.java - Model class for user preferences
 */
package com.vishwajay.jobbot.ai;

public class JobPreferences {
    private String title;
    private String location;
    private String industry;
    private String jobType;

    public JobPreferences(String title, String location, String industry, String jobType) {
        this.title = title;
        this.location = location;
        this.industry = industry;
        this.jobType = jobType;
    }

    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public String getIndustry() { return industry; }
    public String getJobType() { return jobType; }

    @Override
    public String toString() {
        return "Title: " + title + ", Location: " + location + ", Industry: " + industry + ", Type: " + jobType;
    }
}
