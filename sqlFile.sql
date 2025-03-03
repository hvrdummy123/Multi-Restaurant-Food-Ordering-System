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

use multirestaurantordering;
select * from restaurants;
INSERT INTO restaurants (id, name, location, contact, rating) VALUES
(1, 'Spicy Bites', 'Downtown', '1234567890', 4.5),
(2, 'Green Garden', 'Uptown', '0987654321', 4.2),
(3, 'Ocean Delight', 'Seaside', '1122334455', 4.8),
(4, 'Grill Master', 'Midtown', '5566778899', 4.6),
(5, 'Pasta Heaven', 'City Center', '6677889900', 4.3),
(6, 'Burger Stop', 'Westside', '7788990011', 4.4),
(7, 'Sushi World', 'Eastside', '8899001122', 4.9),
(8, 'Steak House', 'Northside', '9900112233', 4.7),
(9, 'Curry Palace', 'Southside', '1112223334', 4.1),
(10, 'Taco Fiesta', 'Suburb', '2223334445', 4.0);


INSERT INTO menu_items (restaurant_id, name, price, category) VALUES
(1, 'Spicy Chicken Wings', 8.99, 'Appetizer'),
(1, 'Grilled Paneer', 7.99, 'Appetizer'),
(1, 'Hot & Sour Soup', 5.49, 'Soup'),
(1, 'Chicken Biryani', 12.99, 'Main Course'),
(1, 'Paneer Butter Masala', 10.99, 'Main Course'),
(1, 'Garlic Naan', 2.99, 'Bread'),
(1, 'Mango Lassi', 3.99, 'Beverage'),
(1, 'Gulab Jamun', 4.99, 'Dessert'),
(1, 'Chicken Tikka', 9.99, 'Appetizer'),
(1, 'Veg Pulao', 8.49, 'Main Course'),

(2, 'Garden Salad', 6.99, 'Appetizer'),
(2, 'Broccoli Soup', 5.99, 'Soup'),
(2, 'Veg Lasagna', 11.99, 'Main Course'),
(2, 'Spinach Pasta', 10.49, 'Main Course'),
(2, 'Bruschetta', 6.49, 'Appetizer'),
(2, 'Fruit Punch', 4.99, 'Beverage'),
(2, 'Tiramisu', 7.99, 'Dessert'),
(2, 'Grilled Zucchini', 8.99, 'Appetizer'),
(2, 'Pumpkin Soup', 5.49, 'Soup'),
(2, 'Quinoa Salad', 9.49, 'Main Course'),

(3, 'Fish Tacos', 9.99, 'Main Course'),
(3, 'Lobster Bisque', 12.49, 'Soup'),
(3, 'Grilled Salmon', 14.99, 'Main Course'),
(3, 'Seafood Paella', 15.99, 'Main Course'),
(3, 'Shrimp Cocktail', 10.99, 'Appetizer'),
(3, 'Blueberry Cheesecake', 6.99, 'Dessert'),
(3, 'Crab Cakes', 11.99, 'Appetizer'),
(3, 'Clam Chowder', 8.99, 'Soup'),
(3, 'Prawn Tempura', 10.49, 'Appetizer'),
(3, 'Lemon Iced Tea', 3.99, 'Beverage'),

(4, 'BBQ Ribs', 14.99, 'Main Course'),
(4, 'Chicken Wings', 9.99, 'Appetizer'),
(4, 'Corn on the Cob', 5.49, 'Side Dish'),
(4, 'Pulled Pork Burger', 12.49, 'Main Course'),
(4, 'Mac & Cheese', 6.99, 'Side Dish'),
(4, 'Root Beer', 4.49, 'Beverage'),
(4, 'Apple Pie', 5.99, 'Dessert'),
(4, 'Grilled Chicken', 11.99, 'Main Course'),
(4, 'French Fries', 4.99, 'Side Dish'),
(4, 'Chocolate Shake', 5.49, 'Beverage'),

(5, 'Alfredo Pasta', 10.99, 'Main Course'),
(5, 'Garlic Bread', 5.49, 'Appetizer'),
(5, 'Margherita Pizza', 9.99, 'Main Course'),
(5, 'Mushroom Risotto', 11.99, 'Main Course'),
(5, 'Caprese Salad', 7.99, 'Appetizer'),
(5, 'Lemonade', 3.99, 'Beverage'),
(5, 'Panna Cotta', 6.99, 'Dessert'),
(5, 'Lasagna', 12.99, 'Main Course'),
(5, 'Stuffed Bell Peppers', 8.99, 'Appetizer'),
(5, 'Tiramisu', 7.99, 'Dessert'),

(6, 'Classic Cheeseburger', 9.49, 'Main Course'),
(6, 'Veggie Burger', 8.99, 'Main Course'),
(6, 'Sweet Potato Fries', 5.49, 'Side Dish'),
(6, 'Onion Rings', 4.99, 'Appetizer'),
(6, 'Milkshake', 6.49, 'Beverage'),
(6, 'Ice Cream Sundae', 5.99, 'Dessert'),
(6, 'Grilled Chicken Burger', 10.49, 'Main Course'),
(6, 'BBQ Bacon Burger', 11.99, 'Main Course'),
(6, 'Spicy Fries', 5.99, 'Side Dish'),
(6, 'Chocolate Brownie', 6.49, 'Dessert'),

(7, 'Sushi Roll', 12.99, 'Main Course'),
(7, 'Miso Soup', 5.49, 'Soup'),
(7, 'Tempura Shrimp', 10.99, 'Appetizer'),
(7, 'Teriyaki Chicken', 13.49, 'Main Course'),
(7, 'Green Tea', 3.99, 'Beverage'),
(7, 'Matcha Ice Cream', 5.99, 'Dessert'),
(7, 'Dragon Roll', 14.49, 'Main Course'),
(7, 'Salmon Sashimi', 15.99, 'Appetizer'),
(7, 'Edamame', 4.99, 'Appetizer'),
(7, 'Mochi', 6.99, 'Dessert'),

(8, 'Grilled Ribeye', 18.99, 'Main Course'),
(8, 'Caesar Salad', 7.49, 'Appetizer'),
(8, 'Garlic Mashed Potatoes', 6.99, 'Side Dish'),
(8, 'Creamed Spinach', 5.99, 'Side Dish'),
(8, 'Red Wine', 9.99, 'Beverage'),
(8, 'Chocolate Lava Cake', 8.99, 'Dessert'),
(8, 'Lamb Chops', 19.99, 'Main Course'),
(8, 'Baked Potato', 5.99, 'Side Dish'),
(8, 'Brussels Sprouts', 6.49, 'Side Dish'),
(8, 'Cheesecake', 7.99, 'Dessert'),

(9, 'Chicken Tikka Masala', 13.49, 'Main Course'),
(9, 'Butter Naan', 3.49, 'Bread'),
(9, 'Tandoori Chicken', 14.99, 'Main Course'),
(9, 'Dal Makhani', 10.99, 'Main Course'),
(9, 'Chai Tea', 3.49, 'Beverage'),
(9, 'Rasgulla', 5.49, 'Dessert'),
(9, 'Palak Paneer', 11.49, 'Main Course'),
(9, 'Aloo Paratha', 4.99, 'Bread'),
(9, 'Samosa', 3.99, 'Appetizer'),
(9, 'Mango Shake', 4.49, 'Beverage'),

(10, 'Beef Taco', 8.99, 'Main Course'),
(10, 'Chicken Taco', 7.99, 'Main Course'),
(10, 'Veggie Taco', 6.99, 'Main Course'),
(10, 'Nachos with Cheese', 5.99, 'Appetizer'),
(10, 'Churros', 4.99, 'Dessert'),
(10, 'Quesadilla', 9.99, 'Main Course'),
(10, 'Guacamole with Chips', 6.49, 'Appetizer'),
(10, 'Burrito Bowl', 10.99, 'Main Course'),
(10, 'Mexican Rice', 4.49, 'Side Dish'),
(10, 'Horchata', 3.99, 'Beverage');


select * from menu_items;

use multirestaurantordering;
ALTER TABLE Users 
ADD COLUMN role ENUM('ADMIN', 'USER', 'OWNER') NOT NULL DEFAULT 'USER';


-- 1. Insert a normal USER
INSERT INTO Users (name, email, password, address, phone_number, role)
VALUES ('John Doe', 'john.doe@example.com', 'hashed_password_1', '1234 Elm Street, City', '123-456-7890', 'USER');

-- 2. Insert a RESTAURANT_OWNER
INSERT INTO Users (name, email, password, address, phone_number, role)
VALUES ('Jane Smith', 'jane.smith@restaurant.com', 'hashed_password_2', '5678 Maple Avenue, Town', '987-654-3210', 'OWNER');

-- 3. Insert an ADMIN user
INSERT INTO Users (name, email, password, address, phone_number, role)
VALUES ('Michael Brown', 'michael.brown@admin.com', 'hashed_password_3', '1234 Pine Road, Metropolis', '555-123-4567', 'ADMIN');

-- 4. Insert a normal USER
INSERT INTO Users (name, email, password, address, phone_number, role)
VALUES ('Sarah Johnson', 'sarah.johnson@example.com', 'hashed_password_4', '4321 Oak Lane, Village', '666-987-5432', 'USER');

-- 5. Insert an OWNER with missing address
INSERT INTO Users (name, email, password, address, phone_number, role)
VALUES ('Chris Lee', 'chris.lee@restaurant.com', 'hashed_password_5', NULL, '777-654-3210', 'OWNER');

-- 6. Insert a normal USER with a different phone number format
INSERT INTO Users (name, email, password, address, phone_number, role)
VALUES ('Lisa Davis', 'lisa.davis@example.com', 'hashed_password_6', '7890 Cedar Road, Suburb', '+1-234-567-8901', 'USER');

-- 7. Insert an ADMIN with a more formal address
INSERT INTO Users (name, email, password, address, phone_number, role)
VALUES ('David Wilson', 'david.wilson@admin.com', 'hashed_password_7', '123 Business Blvd, Suite 456, Corporate City', '888-123-7890', 'ADMIN');

-- 8. Insert a USER with empty address
INSERT INTO Users (name, email, password, address, phone_number, role)
VALUES ('Emily Carter', 'emily.carter@example.com', 'hashed_password_8', '', '555-987-6543', 'USER');

-- 9. Insert an OWNER
INSERT INTO Users (name, email, password, address, phone_number, role)
VALUES ('Robert Clark', 'robert.clark@restaurant.com', 'hashed_password_9', '2467 Birch Street, City Center', '444-222-3333', 'OWNER');

-- 10. Insert an ADMIN
INSERT INTO Users (name, email, password, address, phone_number, role)
VALUES ('Olivia Martinez', 'olivia.martinez@admin.com', 'hashed_password_10', '98 Sunset Drive, Hilltop', '111-999-2222', 'ADMIN');
use multirestaurantordering;
select * from users;
select * from restaurants;
select * from menu_items;