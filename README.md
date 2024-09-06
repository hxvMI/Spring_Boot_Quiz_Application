# QuizApp

## Reason for Project

I wanted to use everything I have been learning for the past couple of months to use and make something simple including as much of everything as I could.

## Overview

**QuizApp** is a Spring Boot-based web application designed to manage quizzes. The application allows users to create, retrieve, and submit quizzes. It uses a RESTful API with full CRUD operations and interacts with a MySQL database. This project also supports user quiz submissions and score calculation.

## Features

- Create quizzes from a set of random questions based on a specific category.
- Retrieve quizzes and questions from the database.
- Submit quiz responses and calculate the user’s score.
- Manage and add new questions categorized by difficulty and topic.

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate ORM**
- **MySQL** (Persistent data storage)
- **H2** (In-memory database for optional use)
- **Lombok** (Reduces boilerplate code)
- **REST API**
- **Maven**

## How to Run

1. Clone the repository
```bash
git clone https://github.com/hxvMI/Spring_Boot_Quiz_Application.git
```
3. Navigate to the project folder
```bash
cd quizapp
```
5. Modify application.properties to configure the database connection if necessary.
6. Run the application using Maven
```bash
mvn spring-boot:run
```
6. The app will be available at http://localhost:8083.

## Project Structure

```bash
src/main/java/com/vhashiro/quizapp
├── controller   # Contains API controllers
├── entity       # Defines JPA entities
├── repository   # Database access with JPA
├── service      # Business logic and services
├── QuizAppApplication.java  # Spring Boot main class
└── application.properties   # Configuration file 
```

## Database Configuration

### MySQL (Default)

Ensure your MySQL server is running, and configure the application.properties file for the database connection:
```properties
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/quiz_database
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### H2 (In-Memory Database)

For development and testing, you can switch to an H2 in-memory database by uncommenting the following lines in `application.properties`:
```properties
#spring.h2.console.enabled=true
#spring.datasource.url=jdbc:h2:mem:dcbapp
#spring.datasource.driver-class-name=org.h2.Driver
#spring.datasource.username=sa
#spring.datasource.password=password
#spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

## API Endpoints

### Question Endpoints

- **Get all questions**
  - **GET** `/question/allQuestions`  
    Retrieves all the questions stored in the database.
- **Get questions by category**
  - **GET** `/question/category/{categoryName}`
    Retrieves questions based on their category.
- **Add a new question**
  - **POST** `/questions/add`
    Adds a new question to the database.

 ### Quiz Endpoints

- **Create a new quiz**
  - **POST** `/quiz/create?questionCategory={category}&numQuestions={numQuestions}&title={title}`  
    Creates a quiz from randomly selected questions in the specified category.
- **Get quiz by ID**
  - **GET** `/quiz/get/{id}`
    Retrieves a quiz by its ID.
- **Submit a quiz**
  - **POST** `/quiz/submit/{id}/`
    Submits a quiz and calculates the score based on user responses.

## Testing the API

You can use **Postman** or **curl** to test the endpoints.

### **Postman** Link: https://www.postman.com/downloads/

### Example: Create a Quiz

```bash
curl -X POST "http://localhost:8083/quiz/create?questionCategory=java&numQuestions=5&title=JavaBasics"
```

### Example: Submit a Quiz

```bash
curl -X POST "http://localhost:8083/quiz/submit/1" \
-H "Content-Type: application/json" \
-d '[
    {"id": 1, "selectedOption": "option1"},
    {"id": 2, "selectedOption": "option3"}
]'
```
I have also included other examples in `quizapp/quizapp/src/main/java/com/vhashiro/quizapp/Request Testing Resources`


