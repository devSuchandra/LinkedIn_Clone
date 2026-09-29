package com.CodingShuttle.LinkedIn.UserService.Repository;

import com.CodingShuttle.LinkedIn.UserService.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
