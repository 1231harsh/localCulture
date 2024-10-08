package com.hc.localCulture.repository;

import com.hc.localCulture.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT id, name, description, latitude, longitude, " +
            "(6371 * acos(cos(radians(:userLat)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:userLng)) + " +
            "sin(radians(:userLat)) * sin(radians(latitude)))) AS distance " +
            "FROM event " +
            "HAVING distance < :radius " +
            "ORDER BY distance", nativeQuery = true)
    List<Event> findNearbyEvents(@Param("userLat") double userLat,
                                 @Param("userLng") double userLng,
                                 @Param("radius") double radius);
}
