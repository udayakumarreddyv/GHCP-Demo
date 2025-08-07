package com.example.demo.service;

import com.example.demo.model.Vehicle;
import com.example.demo.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Vehicle entity operations.
 * Provides business logic and CRUD operations.
 */
@Service
@Transactional
public class VehicleService {
    
    private final VehicleRepository vehicleRepository;
    
    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    
    /**
     * Retrieve all vehicle entities.
     * @return List of all vehicle entities
     */
    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }
    
    /**
     * Find vehicle by ID.
     * @param id The ID of the vehicle to find
     * @return Optional containing the vehicle if found
     */
    @Transactional(readOnly = true)
    public Optional<Vehicle> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return vehicleRepository.findById(id);
    }
    
    /**
     * Save a new vehicle entity.
     * @param vehicle The vehicle entity to save
     * @return The saved vehicle entity
     */
    public Vehicle save(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        return vehicleRepository.save(vehicle);
    }
    
    /**
     * Update an existing vehicle entity.
     * @param id The ID of the vehicle to update
     * @param vehicleDetails The updated vehicle details
     * @return The updated vehicle entity
     * @throws RuntimeException if vehicle not found
     */
    public Vehicle update(Long id, Vehicle vehicleDetails) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (vehicleDetails == null) {
            throw new IllegalArgumentException("Vehicle details cannot be null");
        }
        
        return vehicleRepository.findById(id)
            .map(existingVehicle -> {
                // Update fields here - customize based on entity fields
                return vehicleRepository.save(existingVehicle);
            })
            .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }
    
    /**
     * Delete a vehicle entity by ID.
     * @param id The ID of the vehicle to delete
     * @throws RuntimeException if vehicle not found
     */
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        
        if (!vehicleRepository.existsById(id)) {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
        
        vehicleRepository.deleteById(id);
    }
    
    /**
     * Check if vehicle exists by ID.
     * @param id The ID to check
     * @return true if vehicle exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return vehicleRepository.existsById(id);
    }
    
    /**
     * Count all vehicle entities.
     * @return Total count of vehicle entities
     */
    @Transactional(readOnly = true)
    public long count() {
        return vehicleRepository.count();
    }
}