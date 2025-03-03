# **Order Management Microservice**

## **Overview**  
The **Order Management Microservice** handles food order creation, updates order statuses, and communicates with other microservices like **User Service** and **Restaurant Service** via **OpenFeign**. This microservice is built using **Spring Boot** and provides **RESTful APIs** for order processing. Swagger is integrated for easy API exploration.

## **Features**  
- **Order Management**: Create, retrieve, update, and delete orders.
- **Menu Integration**: Fetch menu items from Restaurant Service.
- **User Management**: Validate users via User Service.
- **API Documentation**: Interactive API documentation via Swagger.

## **API Endpoints**  
### **Order API**  
- **GET** `/orders/{id}` – Retrieve order by ID.  
- **POST** `/orders` – Create a new order.  
- **PUT** `/orders/{id}` – Update an order.  
- **DELETE** `/orders/{id}` – Remove an order.  

## **Accessing API Documentation**  
Swagger UI is available at:  
**[http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)**  

