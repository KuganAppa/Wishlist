package com.kuganappa.wishlist.service;

import com.kuganappa.wishlist.model.User;
import com.kuganappa.wishlist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public User getUser(int id){
        return userRepository.getUserFromId(id);
    }


    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserFromName(String username) {
        return userRepository.getUserFromName(username);
    }
}
