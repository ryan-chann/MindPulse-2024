# MindPulse Web API

## Getting Started

### Pre-requisites

Ensure your systems meets the following requirements:

1. **Java JDK**, v22

   * JAVA_HOME environment variables configured
2. **Maven**, v3.13.0

   * MAVEN_HOME environment variabled configured
3. **AWS CLI**, v2.16.6
4. **AWS Credentials** (Must update before running application)

   * aws_access_key_id configured
   * aws_secret_access_key configured
   * aws_session_token configured

To check if your local machine has active connection: `aws sts get-caller-identity`

To check your AWS credentials (Linux): `vim ~/.aws/credentials`

### Dependencies

This project uses the following dependencies:

1. **Spring Boot Starter Web**, v3.3.0
2. **Spring Context**, v6.1.8
3. **AWS SDK for Java**, v2.26.0

   * **DynamoDB**
   * **DynamoDB Enhanced Client**
   * **SSO**
   * **SSO OIDC**
   * **Apache Client**
4. **Project Lombok**, v1.18.32
