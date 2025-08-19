package com.example.demo.repository;

import com.example.demo.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Vehicle entity.
 * Provides CRUD operations and custom queries.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
    /**
     * Check if vehicle exists by ID.
     * @param id The ID to check
     * @return true if vehicle exists, false otherwise
     */
    boolean existsById(Long id);
    
    /**
     * Find all vehicle entities ordered by ID.
     * @return List of vehicle entities ordered by ID ascending
     */
    List<Vehicle> findAllByOrderByIdAsc();
    
    /**
     * Find all vehicle entities ordered by ID descending.
     * @return List of vehicle entities ordered by ID descending
     */
    List<Vehicle> findAllByOrderByIdDesc();    
    /**
     * Find vehicle entities by make.
     * @param make The make to search for
     * @return List of vehicle entities matching the make
     */
    List<Vehicle> findByMake(String make);
    
    /**
     * Find vehicle entities by make containing the given text.
     * @param make The make text to search for
     * @return List of vehicle entities containing the make
     */
    List<Vehicle> findByMakeContainingIgnoreCase(String make);
    
    /**
     * Find vehicle entities by model.
     * @param model The model to search for
     * @return List of vehicle entities matching the model
     */
    List<Vehicle> findByModel(String model);
    
    /**
     * Find vehicle entities by model containing the given text.
     * @param model The model text to search for
     * @return List of vehicle entities containing the model
     */
    List<Vehicle> findByModelContainingIgnoreCase(String model);
    
    /**
     * Find vehicle entities by year.
     * @param year The year to search for
     * @return List of vehicle entities matching the year
     */
    List<Vehicle> findByYear(String year);
    
    /**
     * Find vehicle entities by year containing the given text.
     * @param year The year text to search for
     * @return List of vehicle entities containing the year
     */
    List<Vehicle> findByYearContainingIgnoreCase(String year);
    
    /**
     * Custom query to count entities.
     * @return Total count of vehicle entities
     */
    @Query("SELECT COUNT(e) FROM Vehicle e")
    long countAll();
    
    /**
     * Custom query example - customize based on your needs.
     * @param keyword The keyword to search for
     * @return List of vehicle entities matching the search
     */
    @Query("SELECT e FROM Vehicle e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Vehicle> findByKeyword(@Param("keyword") String keyword);
}