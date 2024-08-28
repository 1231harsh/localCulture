package com.hc.localCulture.service;

import com.hc.localCulture.entity.Store;
import com.hc.localCulture.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public List<Store> findNearbyStores(double userLat, double userLng, double radius) {

        return storeRepository.findNearbyStores(userLat, userLng, radius);
    }
}
