package com.shopping.project.repositories;

import com.shopping.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User getUserById(Long id);
    User findUserByUsername(String username);
}
