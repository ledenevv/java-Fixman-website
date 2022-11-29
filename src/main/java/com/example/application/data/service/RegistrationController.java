package com.example.application.data.service;

import com.example.application.data.entity.Role;
import com.example.application.data.entity.User;
import com.example.application.data.repository.RoleRepository;
import com.example.application.data.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
        public String addUser(User user,Role role, RoleRepository roleRepository, Map<String, Object> model) {
            User userFromDb = userRepository.findByLogin(user.getLogin());

            if (userFromDb != null) {
                model.put("message", "User exists!");
                return "registration";
            }

        //roleRepository.findById(3);
        //user.setRole(role);

            return "redirect:/login";
        }

}
