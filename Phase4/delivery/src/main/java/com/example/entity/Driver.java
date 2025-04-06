package com.example.entity;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "drivers")
@Schema(description = "Represents a delivery driver")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the driver", example = "1")
    private Long id;
    @Schema(description = "Driver's full name", example = "John Doe")
    private String name;
    @Schema(description = "Driver's contact number", example = "9876543210")
    private String phoneNumber;
    @Schema(description = "Vehicle details of the driver", example = "Toyota Prius - AB123CD")
    private String vehicleDetails;
    @Schema(description = "Driver's availability status", example = "Available")
    private String status;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("driver")
//    @JsonBackReference
    private List<Delivery> deliveries;
    public Driver() {
    }
    public Driver(Long id, String name, String phoneNumber, String vehicleDetails, String status, List<Delivery> deliveries) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vehicleDetails = vehicleDetails;
        this.status = status;
        this.deliveries = deliveries;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }
    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", vehicleDetails='" + vehicleDetails + '\'' +
                ", status='" + status + '\'' +
                ", deliveries=" + (deliveries != null ? deliveries.size() : "null") +
                '}';
    }
}
