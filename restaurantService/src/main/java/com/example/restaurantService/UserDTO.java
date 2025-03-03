package com.example.restaurantService;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String role;  // This is what we need

    // 🛠 Constructor (for easy object creation)
    public UserDTO(Long id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // 🛠 Default Constructor (needed for serialization)
    public UserDTO() {}

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    // Setter for Role (optional, but useful if modifying roles)
    public void setRole(String role) {
        this.role = role;
    }
}
