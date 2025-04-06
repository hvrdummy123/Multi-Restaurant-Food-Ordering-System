<<<<<<< HEAD
package com.example.userService.service;

import com.example.userService.entity.User;
import com.example.userService.entity.UserPreference;
import com.example.userService.entity.Role;
import java.util.*;

public interface UserService {
    User registerUser(User user);

    Optional<User> getUserById(Long userId);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    void deleteUser(Long userId);

    User updateUser(Long userId, User updatedUser);

    List<UserPreference> getUserPreferences(Long userId);

    UserPreference setUserPreferences(Long userId, String preferredCuisine);

    User updateUserRole(Long userId, Role role);

    UserPreference updateUserPreferences(Long userId, String preferredCuisine); // ✅ Added this function
}
=======
package com.example.userService.service;

import com.example.userService.entity.User;
import com.example.userService.entity.UserPreference;
import com.example.userService.entity.Role;
import java.util.*;

public interface UserService {
    User registerUser(User user);

    Optional<User> getUserById(Long userId);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    void deleteUser(Long userId);

    User updateUser(Long userId, User updatedUser);

    List<UserPreference> getUserPreferences(Long userId);

    UserPreference setUserPreferences(Long userId, String preferredCuisine);

    User updateUserRole(Long userId, Role role);

    UserPreference updateUserPreferences(Long userId, String preferredCuisine); // ✅ Added this function
}
>>>>>>> 2fbcaacacf3d6a76ff652418bf7f214fa5faeb94
