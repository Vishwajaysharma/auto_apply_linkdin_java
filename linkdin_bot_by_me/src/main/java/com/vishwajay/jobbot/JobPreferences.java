package com.vishwajay.jobbot;

public class JobPreferences {
    private String jobTitle;
    private String location;
    private String industry;
    private String jobType;

    public JobPreferences(String jobTitle, String location, String industry, String jobType) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.industry = industry;
        this.jobType = jobType;
    }

    // Getters
    public String getJobTitle() {
        return jobTitle;
    }

    public String getLocation() {
        return location;
    }

    public String getIndustry() {
        return industry;
    }

    public String getJobType() {
        return jobType;
    }
}
