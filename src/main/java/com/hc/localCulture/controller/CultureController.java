package com.hc.localCulture.controller;

import com.hc.localCulture.entity.Culture;
import com.hc.localCulture.service.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CultureController {

    @Autowired
    private CultureService cultureService;;


    @GetMapping("/culture")
    public ResponseEntity<List<Culture>> getCulture(@RequestParam("lat") double lat, @RequestParam("lng") double lng) {
        List<Culture> cultures = cultureService.findNearbyEvents(lat, lng);
        return new ResponseEntity<>(cultures, HttpStatus.OK);
    }
}
