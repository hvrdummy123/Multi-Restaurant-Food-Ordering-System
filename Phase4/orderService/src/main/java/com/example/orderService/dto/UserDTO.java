<<<<<<< HEAD
package com.example.orderService.dto;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String role;  // Ensure this field exists

    // Constructors
    public UserDTO() {}

    public UserDTO(Long id, String name, String email, String address, String phoneNumber, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRole() { return role; }  // ✅ Ensure this method exists
    public void setRole(String role) { this.role = role; }
}
=======
package com.example.orderService.dto;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String role;  // Ensure this field exists

    // Constructors
    public UserDTO() {}

    public UserDTO(Long id, String name, String email, String address, String phoneNumber, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRole() { return role; }  // ✅ Ensure this method exists
    public void setRole(String role) { this.role = role; }
}
>>>>>>> 2fbcaacacf3d6a76ff652418bf7f214fa5faeb94
