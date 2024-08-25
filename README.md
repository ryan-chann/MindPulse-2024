# MindPulse  
## General Information 
**Project type:** Prototype  
**Project description:** A booking system for individuals seeking mental health services  
**Owner:** Lee Ming Jun, Ryan Chan  
**Date:** 01 August 2024
  
## Technology Used  
**Programming Language:** JavaScript & Java  
**Framework:** Spring Boot  
**Libraries:** React.js, Ant Design, Axios, AWS SDK, Lombok  
**Development Tools:** Nginx  
**Database:** AWS S3, AWS DynamoDB  
**Deployment:** AWS EC2  
  
## Features  
**Customer:**  
- Browse list of therapist  
- Retrieve therapist infomation  
- Book therapist appointment  
- Receive email notification   

**Web Application**  
- Create therapist appointment  
- Update therapist availability  
  
## Project Retrospective  
### What went well?  
- The project was successfully designed, developed, tested, and deployed in 1 month.  
- The project was completed within budget.  
- The author have learned and proved the concepts of cloud computing integration.  
  
### What didn't went well?
- The author loses focus in executing software processes due to inadequate project planning.
- Most of the resources were used to learn the technologies.  
- The deployed application were too simple and lack of functionalities.
- The lifespan of the credentials to AWS Single Sign On (SSO) is 12 hours at maximum, which means every 12 hours the credentials needs to be reset. This jeopardized the availability of the application.
  
### What can be improved?
- Instead of using 2 different programming languages to build the frontend and backend, a single language such as Javascript can be solely used for full-stack development due to limited resources.
- Testing can be automated with Selenium/Webdriverio/Puppeteer.
- Deployment can be automated with Jenkins.
- A script can be added into the application to automate the process of resetting the AWS SSO credentials.
- Project planning should include the findings of tools required to build the application and training for it.
- The executed processes must be documented to increase maintainability, code quality can be improved.
- Every written code should be documented to increase clarity  
  
## Video Demo  
[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/Venvnqz646s/0.jpg)](https://www.youtube.com/watch?v=Venvnqz646s)
