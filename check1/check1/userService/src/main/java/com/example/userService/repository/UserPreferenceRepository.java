package com.example.userService.repository;

import java.util.*;

import com.example.userService.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long>
{
    List<UserPreference> findByUserId(Long userId);
}
