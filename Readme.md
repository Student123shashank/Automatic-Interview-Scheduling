# Automatic Interview Scheduling System

A full-stack **Interview Slot Scheduling System** developed using **Spring Boot, Java 17, Maven, and MySQL**.  
The application allows users to **sign up, log in, view available interview slots, book slots**, and supports **slot updates** with a modern animated UI.

---

## Tech Stack

- **Backend:** Spring Boot
- **Language:** Java 17
- **Build Tool:** Maven
- **Database:** MySQL
- **ORM:** Spring Data JPA (Hibernate)
- **Frontend:** HTML, CSS, JavaScript
- **Authentication:** Session-based login

---


---

## Prerequisites

Ensure the following are installed:

- **Java 17**
- **Maven**
- **MySQL**

Check versions:
```bash
java -version
mvn -version
mysql --version




Database Setup (MySQL)
CREATE DATABASE interview_scheduler;





application.yml Configuration
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/interview_scheduler
    username: root
    password: Shashank@2024
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

server:
  port: 8080





How To RUN PROJECT
cd scheduler
mvn clean install
mvn spring-boot:run
http://localhost:8080/index.html
http://localhost:8080/api/slots?cursor=2025-01-01T00:00
