CREATE DATABASE MultiRestaurantOrdering; 
USE MultiRestaurantOrdering;

CREATE TABLE Users ( id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL, email VARCHAR(100) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL, address TEXT, phone_number VARCHAR(15) UNIQUE NOT NULL ); 

CREATE TABLE UserPreferences ( id INT PRIMARY KEY AUTO_INCREMENT, user_id INT NOT NULL, preferred_cuisine VARCHAR(50), FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE );

CREATE TABLE Restaurants ( id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL, location VARCHAR(255), contact VARCHAR(20), rating DECIMAL(3,2) DEFAULT 0.0 ); 

CREATE TABLE Menu_Items ( id INT PRIMARY KEY AUTO_INCREMENT, restaurant_id INT NOT NULL, name VARCHAR(100) NOT NULL, price DECIMAL(10,2) NOT NULL, category VARCHAR(50), FOREIGN KEY (restaurant_id) REFERENCES Restaurants(id) ON DELETE CASCADE );

CREATE TABLE Orders ( id INT PRIMARY KEY AUTO_INCREMENT, user_id INT NOT NULL, restaurant_id INT NOT NULL, total_price DECIMAL(10,2) NOT NULL, status ENUM('Pending', 'Processing', 'Delivered', 'Cancelled') DEFAULT 'Pending', timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE, FOREIGN KEY (restaurant_id) REFERENCES Restaurants(id) ON DELETE CASCADE ); 

CREATE TABLE Order_Items ( id INT PRIMARY KEY AUTO_INCREMENT, order_id INT NOT NULL, menu_item_id INT NOT NULL, quantity INT NOT NULL, price DECIMAL(10,2) NOT NULL, FOREIGN KEY (order_id) REFERENCES Orders(id) ON DELETE CASCADE, FOREIGN KEY (menu_item_id) REFERENCES Menu_Items(id) ON DELETE CASCADE ); 

CREATE TABLE Order_Status ( id INT PRIMARY KEY AUTO_INCREMENT, order_id INT NOT NULL, status ENUM('Pending', 'Processing', 'Out for Delivery', 'Delivered', 'Cancelled'), updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (order_id) REFERENCES Orders(id) ON DELETE CASCADE );

CREATE TABLE Drivers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL,
    vehicle_details VARCHAR(100),
    status ENUM('Available', 'On Delivery', 'Inactive') DEFAULT 'Available'
);

CREATE TABLE Deliveries (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    driver_id INT NULL,
    status ENUM('Pending', 'Picked Up', 'Out for Delivery', 'Delivered') DEFAULT 'Pending',
    estimated_time TIME,
    FOREIGN KEY (order_id)
        REFERENCES Orders (id)
        ON DELETE CASCADE,
    FOREIGN KEY (driver_id)
        REFERENCES Drivers (id)
        ON DELETE SET NULL
);


