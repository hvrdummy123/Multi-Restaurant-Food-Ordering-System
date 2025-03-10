package com.example.orderService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "Order_Items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Order ID cannot be null")
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotNull(message = "Menu Item ID cannot be null")
    private Long menuItemId;

    @NotNull(message = "Quantity cannot be null")
    private int quantity;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    public OrderItem() {}

    public OrderItem(Order order, Long menuItemId, int quantity, BigDecimal price) {
        this.order = order;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Long getMenuItemId() { return menuItemId; }
    public void setMenuItemId(Long menuItemId) { this.menuItemId = menuItemId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
