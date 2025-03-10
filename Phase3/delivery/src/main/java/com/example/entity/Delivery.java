package com.example.entity;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "deliveries")
@Schema(description = "Represents a delivery order")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the delivery", example = "1")
    private Long id;
    @Schema(description = "Order ID", example = "ORDER123")
    private Long orderId;
    @Schema(description = "Current status of delivery", example = "Pending")
    private String status;
    @Schema(description = "Estimated time for delivery", example = "30 mins")
    private String estimatedTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", nullable = false)
    @JsonIgnoreProperties("deliveries")
    @Schema(description = "Assigned driver for the delivery")
//    @JsonManagedReference
    private Driver driver;
    public Delivery() {
    }
    public Delivery(Long id, Long orderId, String status, String estimatedTime, Driver driver) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.estimatedTime = estimatedTime;
        this.driver = driver;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", status='" + status + '\'' +
                ", estimatedTime='" + estimatedTime + '\'' +
                ", driver=" + (driver != null ? driver.getId() : "null") +
                '}';
    }
}
