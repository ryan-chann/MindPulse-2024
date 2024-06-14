# MindPulse Web API

## Description

## Getting Started

### Dependencies

This project uses the following dependencies:

- **Spring Boot Starter Web**: Simplifies the development of new Spring Web applications.
- **Spring Context, Version: 6.1.8**: Provides runtime support for executing Spring applications.
- **AWS SDK for Java - DynamoDB, Version: 2.26.0**
  - *Exclusions*:
    - `software.amazon.awssdk:netty-nio-client`
    - `software.amazon.awssdk:apache-client`
- **AWS SDK for Java - DynamoDB Enhanced Client, Version: 2.26.0**: Facilitates easier interaction with DynamoDB.
- **AWS SDK for Java - SSO**: Supports single sign-on capabilities for AWS services.
- **AWS SDK for Java - SSO OIDC**: Provides OpenID Connect support for AWS SSO.
- **AWS SDK for Java - Apache Client**
  - *Exclusions*:
    - `commons-logging:commons-logging`
- **Project Lombok, Version: 1.18.32**: Simplifies Java code and reduces boilerplate.

### Installing

To set up your development environment:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/mindpulse-web-api.git