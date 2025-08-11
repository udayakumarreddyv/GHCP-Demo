package com.example.demo.controller;

import com.example.demo.model.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Vehicle entity operations.
 * Provides RESTful API endpoints for Vehicle management.
 */
@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
@Validated
public class VehicleController {
    
    private final VehicleService vehicleService;
    
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    
    /**
     * Get all vehicles.
     * @return List of all vehicles with HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllEntities() {
        try {
            List<Vehicle> vehicles = vehicleService.findAll();
            return ResponseEntity.ok(vehicles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Get vehicle by ID.
     * @param id The ID of the vehicle to retrieve
     * @return Vehicle entity with HTTP 200 OK, or HTTP 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getEntityById(@PathVariable @NotNull Long id) {
        try {
            Optional<Vehicle> vehicle = vehicleService.findById(id);
            return vehicle.map(entity -> ResponseEntity.ok().body(entity))
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Create a new vehicle.
     * @param vehicle The vehicle entity to create
     * @return Created vehicle with HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<Vehicle> createEntity(@Valid @RequestBody Vehicle vehicle) {
        try {
            Vehicle savedVehicle = vehicleService.save(vehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Update an existing vehicle.
     * @param id The ID of the vehicle to update
     * @param vehicleDetails The updated vehicle details
     * @return Updated vehicle with HTTP 200 OK, or HTTP 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateEntity(@PathVariable @NotNull Long id, 
                                                     @Valid @RequestBody Vehicle vehicleDetails) {
        try {
            Vehicle updatedVehicle = vehicleService.update(id, vehicleDetails);
            return ResponseEntity.ok(updatedVehicle);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Delete vehicle by ID.
     * @param id The ID of the vehicle to delete
     * @return HTTP 204 No Content on success, or HTTP 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable @NotNull Long id) {
        try {
            vehicleService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Check if vehicle exists by ID.
     * @param id The ID to check
     * @return HTTP 200 OK if exists, HTTP 404 Not Found if not
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable @NotNull Long id) {
        try {
            boolean exists = vehicleService.existsById(id);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Get count of all vehicles.
     * @return Total count with HTTP 200 OK
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        try {
            long count = vehicleService.count();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}