package com.cts.it.service;

import com.cts.it.model.Login;
import com.cts.it.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    
    List<Login> getAllLogers();

    User getUserById(int user_id);

    User pushUser(User newUser);

    User updateUser(User updatedUser, int user_id);

    void deleteUserById(int user_id);
}
