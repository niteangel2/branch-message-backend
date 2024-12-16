# Messaging App - Backend

This is the backend for the Messaging App. It is built using **Spring Boot** and **Gradle**, and it provides REST APIs for managing messages, including sending and responding to messages. This backend can be connected to a database (MySQL) to store and retrieve messages.

Frontend Github link: https://github.com/niteangel2/messaging-app-frontend

## Prerequisites

1. **Java Development Kit (JDK)**:
   - You need **Java 11 or higher** to run this project.

2. **Gradle**:
   - Gradle is used to manage the project's dependencies and build tasks.
   - After installation, verify it by running `gradle -v`.

3. **Git**:
   - Git is needed for version control and to clone the repository.

4. **IDE (I used)**:
   - Java-compatible IDE: **IntelliJ IDEA**.
   - **IntelliJ IDEA** is recommended for Spring Boot projects.

5. **Database**:
   - For database like MySQL, make sure it's installed and running.
   - Modify the `src/main/resources/application.properties` file to configure the database connection.

## Configure Application Properties

spring.datasource.url=jdbc:mysql://localhost:3306/messaging_app
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

## Install Dependencies
gradle build

## Access the Application
Once we run the application, it will be available at:

Backend API: http://localhost:8080
Swagger UI (API documentation): http://localhost:8080/swagger-ui/index.html (For testing apis and better ui)

## API Endpoints
1. **Home Page** (Displays unread messages)
Method: GET
URL: http://localhost:8080/
This endpoint retrieves all unread messages from the database.

2. **Respond to Message**
Method: POST
URL: http://localhost:8080/respond/{id}
Request Parameters:
id: The ID of the message to respond to.
response: The response text.
This endpoint allows you to respond to a specific message by its ID.

## Project Structure
1. **Controller**: MessageController.java - Contains API endpoints for managing messages and responses.
2. **Service**: MessageService.java - Contains the logic for handling message-related operations.
3. **Repository**: MessageRepository.java - Interface for database operations related to messages.
4. **Entity**: Message.java - Represents the Message entity in the database.
5. GlobalCorsConfig.java : To allow the frontend (running on `http://localhost:3000`) to make requests to the backend, CORS (Cross-Origin Resource Sharing) is configured in the Spring Boot application.
6. MessagingApplication.java: Entry of Springboot apllication

## Data
This Spring Boot-based messaging app reads data from a data.csv file located in the resources folder and loads it into the database, enabling efficient message management and interactions via RESTful APIs.
