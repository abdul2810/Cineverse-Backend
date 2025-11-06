package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserBo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserMovieService {

    @Autowired
    private UserBo userBo;

    // Register user
    public User registerUser(User user) {
        return userBo.save(user);
    }

    // Login check
    public Map<String, Object> login(String email, String pass) {
        Map<String, Object> res = new HashMap<>();
        Optional<User> userOpt = userBo.findById(email);

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(pass)) {
            User user = userOpt.get();
            res.put("success", true);
            res.put("user", Map.of(
                    "email", user.getEmail(),
                    "name", user.getName(),
                    "userType", "user"
            ));
        } else {
            res.put("success", false);
        }

        return res;
    }

    public User getUserByEmail(String email) {
        return userBo.findById(email).orElse(null);
    }
}
