package co.com.perficient.project3.controller;

import co.com.perficient.project3.model.entity.UserP3;
import co.com.perficient.project3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static co.com.perficient.project3.utils.constant.Constants.REGISTER;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = REGISTER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UserP3 userP3) {
        userP3.setPassword(passwordEncoder.encode(userP3.getPassword()));
        userRepository.save(userP3);
        return new ResponseEntity<>("user created successfully", HttpStatus.CREATED);
    }
}
