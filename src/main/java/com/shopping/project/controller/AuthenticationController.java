package com.shopping.project.controller;

import com.shopping.project.dto.LoginCredentials;
import com.shopping.project.dto.RegisterCredentials;
import com.shopping.project.entities.User;
import com.shopping.project.security.JwtTokenProvider;
import com.shopping.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;


    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/customerLogin")
    public ResponseEntity<Object> loginWorker(@RequestBody LoginCredentials data) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtTokenProvider.generateJwtToken(authentication);


        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User loggedInUser = userService.findByUsername(username);
        Map<String, Object> body = new HashMap<>();
        body.put("Authorization", "Bearer " + jwtToken);
        body.put("User", loggedInUser);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/customerRegister")
    public ResponseEntity<?> register(@RequestBody RegisterCredentials data) {
        if (null != userService.findByUsername(data.getUsername())) {
            return new ResponseEntity<>("Username already in use.", HttpStatus.BAD_REQUEST);
        }

        User user = new User(data.getUsername(), passwordEncoder.encode(data.getPassword()), data.getFirstName(), data.getLastName());

        if (data.getUserRole() == null) {
            user.setUserRole("none");
        } else {
            user.setUserRole(data.getUserRole());
        }

        userService.createUser(user);

        return new ResponseEntity<>("User successfully registered.", HttpStatus.OK);
    }
}
