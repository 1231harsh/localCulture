package com.hc.localCulture.controller;

import com.hc.localCulture.entity.Vendor;
import com.hc.localCulture.jwt.JwtUtil;
import com.hc.localCulture.security.MyUserDetailsService;
import com.hc.localCulture.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VendorService vendorService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserdetailService;

    @PostMapping("/register")
    private ResponseEntity<String> register(Vendor vendor) {
        vendor.setPassword(passwordEncoder.encode(vendor.getPassword()));
        vendorService.saveVendor(vendor);
        return new ResponseEntity<>("Vendor Registered", HttpStatus.OK);
    }

    @PostMapping("/login")
    private ResponseEntity<String> login(Vendor vendor) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(vendor.getUsername(), vendor.getPassword())
            );
            UserDetails userDetails = myUserdetailService.loadUserByUsername(vendor.getUsername());
            String token = jwtUtil.generateToken(vendor.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
        }
    }
}
