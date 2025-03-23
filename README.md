# Bookstore Inventory System

A Spring Boot application for managing a bookstore inventory with features like book search, shopping cart, checkout simulation, and purchase history.

##  Features
- **User Management** (Basic login session)
- **Shopping Cart** (Add, remove books)
- **Checkout Simulation** (Web, USSD, Transfer)
- **Purchase History**
- **Book Search**
- **Session-Based Authentication (HttpSession)**

##  Technologies Used
- **Spring Boot** (Java Backend)
- **MySQL** (Database)
- **Git** (Version Control)

Make sure MySQL is running and configured correctly.

Ensure Java 17+ is installed.

##  Setup Instructions

###  Clone the Repository
```sh
git clone https://github.com/Kaobimdiiwelumo/interswitch-test.git
cd INTERSWITCH
```

### Configure Application Properties
Create a src/main/resources/application.properties file (ignored in .gitignore):

spring.datasource.url=jdbc:mysql://localhost:3306/bookstore

spring.datasource.username=root

spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update

### Build and Run
```sh
mvn clean install
mvn spring-boot:run
```

### Application URLs

Once the application is running, access it at:

Base URL: http://localhost:8080

Swagger API Documentation: http://localhost:8080/swagger-ui/index.html

Make sure Swagger is enabled in your project by adding the springdoc-openapi-starter-webmvc-ui dependency in pom.xml:
```sh
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.2</version>
</dependency>
```
# License

This project is licensed under the MIT License. See the LICENSE file for more details.
