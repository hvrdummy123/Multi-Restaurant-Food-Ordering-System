package com.example.restaurantService.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private String category;
    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Restaurant restaurant;

    public MenuItem() {}

    public MenuItem(String name, double price, String category, Long restaurantId) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
