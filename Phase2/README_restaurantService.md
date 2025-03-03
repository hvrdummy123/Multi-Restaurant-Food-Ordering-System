# Restaurant Management Microservice  

## Overview  
The **Restaurant Management Microservice** is responsible for managing restaurants and their menu items. It provides RESTful APIs to perform CRUD operations on restaurants and menu items while ensuring proper authorization for modifying data.  

This microservice is built using **Spring Boot** and integrates with a **User Service** via Feign Client for role-based access control. It also includes **Swagger documentation** for easy API exploration.  

## Features  
- **Restaurant Management**: Create, retrieve (all and by ID), update, and delete restaurants.  
- **Menu Management**: Add, retrieve, and delete menu items for specific restaurants.  
- **Role-Based Authorization**: Only authorized users (Admins & Owners) can modify restaurant and menu details.  
- **API Documentation**: Interactive API documentation available via Swagger.  

## API Endpoints  

### **Restaurant API**  
- **GET /restaurants** – Retrieve all restaurants.  
- **GET /restaurants/{id}** – Fetch details of a specific restaurant by ID.  
- **POST /restaurants** – Create a new restaurant (**Only Admin/Owner**).  
- **PUT /restaurants/{id}** – Update an existing restaurant (**Only Admin/Owner**).  
- **DELETE /restaurants/{id}** – Remove a restaurant (**Only Admin**).  

### **Menu API**  
- **GET /menu/{restaurantId}** – Retrieve all menu items for a specific restaurant.  
- **POST /menu** – Add a new menu item (**Only Admin/Owner**).  
- **DELETE /menu/{id}** – Remove a menu item (**Only Admin/Owner**).  

## Accessing API Documentation  
Once the application is running, you can explore the API documentation using Swagger at:  
http://localhost:8081/swagger-ui.html
