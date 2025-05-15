package com.vishwajay.jobbot;

public class JobApplicationStarter {

    public static void main(String[] args) {
        // Your LinkedIn credentials
        String email = "!@##$$$$@gmail.com";
        String password = "!@@##$$$%%%%%";

        // Job preferences
        String jobTitle = "Quality assurance, testing ,testng";
        String location = "India";

        // Start the job application proces
        JobApplicationProcess process = new JobApplicationProcess();
        process.startApplicationProcess(email, password, jobTitle, location);
    }
}
