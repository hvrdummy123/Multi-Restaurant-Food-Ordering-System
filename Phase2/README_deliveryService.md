# **Delivery Management Microservice**  

## **Overview**  
The **Delivery Management Microservice** is responsible for managing deliveries and driver assignments within a logistics system. It provides **RESTful APIs** to perform **CRUD operations** on deliveries and drivers, ensuring efficient tracking and management of the delivery process.  

This microservice is built using **Spring Boot** and comes with **Swagger** documentation for easy API exploration.  

## **Features**  
- **Delivery Management**: Create, retrieve (all and by ID), update, and delete deliveries.  
- **Driver Management**: Create, retrieve (all and by ID), update, and delete drivers.  
- **Driver Assignment**: Assign drivers to deliveries dynamically.  
- **Delivery Tracking**: Fetch and monitor the status of deliveries.  
- **API Documentation**: Interactive API documentation available via Swagger.  

## **API Endpoints**  

### **Delivery API**  
- **GET** `/deliveries` – Retrieve all deliveries.  
- **GET** `/deliveries/{id}` – Fetch a specific delivery by ID.  
- **POST** `/deliveries` – Create a new delivery.  
- **PUT** `/deliveries/{id}` – Update an existing delivery.  
- **DELETE** `/deliveries/{id}` – Remove a delivery.  

### **Driver API**  
- **GET** `/drivers` – List all drivers.  
- **GET** `/drivers/{id}` – Retrieve a specific driver by ID.  
- **POST** `/drivers` – Add a new driver.  
- **PUT** `/drivers/{id}` – Update driver details.  
- **DELETE** `/drivers/{id}` – Remove a driver.  

## **Accessing API Documentation**  
Once the application is running, you can explore the API documentation using **Swagger** at:  
**[http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)**
