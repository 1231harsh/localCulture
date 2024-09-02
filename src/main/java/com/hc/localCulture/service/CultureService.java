package com.hc.localCulture.service;

import com.hc.localCulture.entity.Culture;
import com.hc.localCulture.repository.CultureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultureService {

    @Autowired
    private CultureRepository cultureRepository;

    public List<Culture> findNearbyEvents(double lat, double lng) {
        double radius=100;
        return cultureRepository.findNearbyCultures(lat, lng, radius);
    }
}
