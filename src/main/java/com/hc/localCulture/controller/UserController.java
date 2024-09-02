package com.hc.localCulture.controller;

import com.hc.localCulture.entity.User;
import com.hc.localCulture.jwt.JwtUtil;
import com.hc.localCulture.security.MyUserDetailsService;
import com.hc.localCulture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserdetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return new ResponseEntity<>("User Added", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = myUserdetailService.loadUserByUsername(user.getUsername());
            String token = jwtUtil.generateToken(user.getUsername());
            System.out.println("passed");
            return ResponseEntity.ok(token);
        }catch (Exception e){
            return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
        }
    }
}
