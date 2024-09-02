package com.hc.localCulture.controller;

import com.hc.localCulture.entity.Store;
import com.hc.localCulture.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoreConroller {

    @Autowired
    private StoreService storeService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Working",HttpStatus.OK);
    }

    @GetMapping("/store")
    public ResponseEntity<List<Store>> getStores (@RequestParam("lat") double lat, @RequestParam("lng") double lng) {
        double radius=5;
        List<Store>  listOfStores = storeService.findNearbyStores(lat, lng,radius);
        return new ResponseEntity<>(listOfStores, HttpStatus.OK);
    }
}
