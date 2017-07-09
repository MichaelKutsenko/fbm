package com.fbm.services;

import com.fbm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fbm.repository.UserRepository;

import java.util.List;

/**
 * Created by Mocart on 09-Jul-17.
 */

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(long userId) {
        return userRepository.findOne(userId);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
