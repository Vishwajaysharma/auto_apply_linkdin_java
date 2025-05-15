# auto_apply_linkdin_java
LinkedIn Job Auto Apply Bot

This project is an automated bot that logs in to LinkedIn, searches for jobs based on your preferences, and applies to suitable jobs. It also includes AI-based job matching and automatic answer generation for common application questions. i want all to contribute to this project 

---

Features
- Stealth browser automation using Selenium
- AI-powered job matching
- Human-like interaction simulation (typing, mouse movement)
- Auto-answering to application questions

---

Requirements

- Java 17+
- Maven
- Chrome Browser (v136 or compatible with your Selenium DevTools dependency)
- ChromeDriver matching your Chrome version (make sure it's accessible in PATH)

---

Setup Instructions

1. Clone the repository
   ```bash
   git clone https://github.com/your-repo/jobbot.git
   cd jobbot
   ```

2. Configure Credentials and Preferences
   Update the `JobApplicationStarter.java` file with your LinkedIn login and preferred job title/location.

   ```java
   String email = "your-email@example.com";
   String password = "your-password";
   String jobTitle = "Software Engineer";
   String location = "New York";
   ```



3. Fix Browser DevTools Warning (Optional)
   If you get warnings about CDP versions, add this to your dependencies:
   ```xml
   <dependency>
       <groupId>org.seleniumhq.selenium</groupId>
       <artifactId>selenium-devtools-v136</artifactId>
       <version>4.12.1</version>
   </dependency>
   ```
   Or match the version with your Chrome version.

---

AI Matching

The bot uses basic keyword matching to filter job descriptions that align with your preferences (e.g., title, location, industry, job type).

---

Known Issues

- SLF4J warning: Safe to ignore, logging will fallback to NOP.
- `NoSuchElementException`: Make sure the LinkedIn layout has not changed; update XPaths if needed.

---

Directory Structure Overview

```
com.vishwajay.jobbot
├── automation               # Selenium-based browser interaction
├── ai                      # AI job matcher logic
├── question                # Auto-answering application questions
├── utils                   # Utilities (e.g., logger)
├── JobApplicationProcess   # Orchestrates the full application flow
├── JobApplicationStarter   # Entry point (main method)
```

---

## 💖 Support This Project

If you find this LinkedIn Job Auto Apply Bot useful and would like to support its development:

**Donate via UPI:** `vishwajay123456@okhdfc`

Your support is appreciated! 🙏
