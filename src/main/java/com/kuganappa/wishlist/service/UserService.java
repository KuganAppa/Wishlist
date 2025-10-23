package com.kuganappa.wishlist.service;

import com.kuganappa.wishlist.model.User;
import com.kuganappa.wishlist.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }


}
