# User Management Microservice

## Overview

This is a Spring Boot-based User Management System with RESTful APIs, built using Java and MySQL. It allows users to register, update their profiles, and delete accounts while ensuring authentication, role-based access, and user preferences management. The system leverages **Spring Boot** for backend functionality, **Spring Data JPA** for seamless database interaction, and **Swagger documentation** for easy API exploration.

## Features

- **User registration, profile management, and account deletion:** Users can create an account, update their profile details, and delete their accounts when needed.  
- **Role-based authentication and access control:** The system enforces role-based permissions to restrict access to specific functionalities based on user roles.  
- **Secure password hashing and authentication:** User passwords are securely hashed using industry-standard encryption techniques, ensuring protection against unauthorized access. 
- **User preference management for customization:** Users can customize their experience by setting and updating preferences, which are stored securely in the database.
- **RESTful API endpoints for seamless integration:** All functionalities are exposed via RESTful APIs, making integration with frontend applications seamless and efficient.

## API Endpoints

### User Management
- **POST** `/users/register` – Register a new user  
- **GET** `/users/{id}` – Get user details by ID  
- **PUT** `/users/{id}` – Update user details  
- **DELETE** `/users/{id}` – Delete user account  
- **GET** `/users` – Retrieve all users  
- **GET** `/users/email/{email}` – Get user details by email  
- **PUT** `/users/{id}/role` – Update user role (**Admin/Owner/User**) 

### User Preferences
- **POST** `/users/{id}/preferences` – Add preferences for a user  
- **GET** `/users/{id}/preferences` – Retrieve user preferences  
- **PUT** `/users/{id}/preferences` – Update user preferences  

## Accessing API Documentation
Once the application is running, you can explore the API documentation using Swagger at:
http://localhost:8080/swagger-ui.html

