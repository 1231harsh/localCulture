package com.hc.localCulture.service;

import com.hc.localCulture.entity.Event;
import com.hc.localCulture.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEvents(double lat, double lng) {
        double radius= 100;
        return eventRepository.findNearbyEvents(lat,lng,radius);
    }
}
