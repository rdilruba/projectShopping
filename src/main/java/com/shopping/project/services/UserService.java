package com.shopping.project.services;

import com.shopping.project.entities.User;

public interface UserService {
    User getUser(Long id);
    User createUser(User user);
}
