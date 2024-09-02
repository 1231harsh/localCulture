package com.hc.localCulture.controller;

import com.hc.localCulture.entity.Event;
import com.hc.localCulture.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/event")
    public ResponseEntity<List<Event>> getEvent(@RequestParam("lat") double lat, @RequestParam("lng") double lng) {
        List<Event> events=eventService.getEvents(lat, lng);
        return new ResponseEntity<>(events,HttpStatus.OK);
    }
}
