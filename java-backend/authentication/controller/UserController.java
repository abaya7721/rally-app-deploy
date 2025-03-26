// package com.app.rally.authentication.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.app.rally.authentication.data.UserNew;
// import com.app.rally.data.repository.UserRepository;

// @RestController
// @RequestMapping("/users")
// public class UserController {

//     @Autowired
//     private UserRepository userRepository;
    
//     @GetMapping
//     public List<UserNew> getAllUsers() {
//         return userRepository.findAll();
//     }

//     @GetMapping("/{username}")
//     public UserNew getUserByUsername(@PathVariable String username) {
//         return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
//     }
// }
