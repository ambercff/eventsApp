package com.ambercff.events_app.implementations.user;

import com.ambercff.events_app.models.User;
import com.ambercff.events_app.repositories.UserRepository;
import com.ambercff.events_app.services.user.UserGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGetAllServiceImpl implements UserGetAllService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
