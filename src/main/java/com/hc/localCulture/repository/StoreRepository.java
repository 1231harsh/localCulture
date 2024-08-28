package com.hc.localCulture.repository;

import com.hc.localCulture.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query(value = "SELECT  id,address, store_name, latitude, longitude,phone_number, " +
            "(6371 * acos(cos(radians(:userLat)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:userLng)) + " +
            "sin(radians(:userLat)) * sin(radians(latitude)))) AS distance " +
            "FROM store " +
            "HAVING distance < :radius " +
            "ORDER BY distance", nativeQuery = true)
    List<Store> findNearbyStores(@Param("userLat") double userLat,
                                 @Param("userLng") double userLng,
                                 @Param("radius") double radius);
}