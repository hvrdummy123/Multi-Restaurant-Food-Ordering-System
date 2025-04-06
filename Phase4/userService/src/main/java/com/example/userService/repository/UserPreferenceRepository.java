<<<<<<< HEAD
package com.example.userService.repository;

import java.util.*;

import com.example.userService.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long>
{
    List<UserPreference> findByUserId(Long userId);
}
=======
package com.example.userService.repository;

import java.util.*;

import com.example.userService.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long>
{
    List<UserPreference> findByUserId(Long userId);
}
>>>>>>> 2fbcaacacf3d6a76ff652418bf7f214fa5faeb94
