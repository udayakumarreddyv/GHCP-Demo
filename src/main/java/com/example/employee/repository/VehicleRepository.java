package com.example.demo.repository;

import com.example.demo.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByTitle(String title);

    List<Vehicle> findByMakeAndModel(String make, String model);

    List<Vehicle> findByYearBetween(Integer startYear, Integer endYear);

    boolean existsByTitleAndMake(String title, String make);

    List<Vehicle> findByMakeOrderByYearDesc(String make);

    @Query("SELECT v FROM Vehicle v WHERE v.make = :make AND v.year >= :year")
    List<Vehicle> findVehiclesByMakeAndYearGreaterThan(@Param("make") String make, @Param("year") Integer year);

    @Query("SELECT COUNT(v) FROM Vehicle v WHERE v.make = :make AND v.model = :model")
    long countByMakeAndModel(@Param("make") String make, @Param("model") String model);

    void deleteByTitle(String title);

    List<Vehicle> findByMakeContainingIgnoreCase(String make);

    Optional<Vehicle> findFirstByOrderByYearDesc();
}