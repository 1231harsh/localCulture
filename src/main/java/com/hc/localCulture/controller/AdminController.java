package com.hc.localCulture.controller;

import com.hc.localCulture.entity.Admin;
import com.hc.localCulture.entity.User;
import com.hc.localCulture.entity.Vendor;
import com.hc.localCulture.jwt.JwtUtil;
import com.hc.localCulture.security.MyUserDetailsService;
import com.hc.localCulture.service.UserService;
import com.hc.localCulture.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

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

    @Autowired
    private VendorService vendorService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        System.out.println(admin.getUsername()+" "+admin.getPassword());
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));
            UserDetails userDetails = myUserdetailService.loadUserByUsername(admin.getUsername());
            String token = jwtUtil.generateToken(admin.getUsername());
            System.out.println("passed");
            return ResponseEntity.ok(token);
        }catch (Exception e){
            return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/add/user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return new ResponseEntity<>("User Added", HttpStatus.OK);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return new ResponseEntity<>("User Deleted", HttpStatus.OK);
    }

    @PostMapping("/add/vendor")
    public ResponseEntity<String> addVendor(@RequestBody Vendor vendor) {
        vendor.setPassword(passwordEncoder.encode(vendor.getPassword()));
        vendorService.saveVendor(vendor);
        return new ResponseEntity<>("Vendor Added", HttpStatus.OK);
    }

    @DeleteMapping("/delete/vendor")
    public ResponseEntity<String> deleteVendor(@RequestBody Vendor vendor) {
        vendorService.deleteVendor(vendor);
        return new ResponseEntity<>("Vendor Deleted", HttpStatus.OK);
    }
}
