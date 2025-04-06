<<<<<<< HEAD
package com.example.userService.repository;

import java.util.*;
import com.example.userService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByEmail(String email);
}
=======
package com.example.userService.repository;

import java.util.*;
import com.example.userService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByEmail(String email);
}
>>>>>>> 2fbcaacacf3d6a76ff652418bf7f214fa5faeb94
