package com.cts.it.service.impl;

import com.cts.it.model.Login;
import com.cts.it.model.User;
import com.cts.it.repository.UserRepository;
import com.cts.it.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int user_id) {
        return null;
    }

    @Override
    public User pushUser(User newUser) {
        return null;
    }

    @Override
    public User updateUser(User updatedUser, int user_id) {
        return null;
    }

    @Override
    public void deleteUserById(int user_id) {

    }

	@Override
	public List<Login> getAllLogers() {
		// TODO Auto-generated method stub
		return null;
	}
}
