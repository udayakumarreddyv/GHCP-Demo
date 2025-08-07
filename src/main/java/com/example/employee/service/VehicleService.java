package com.example.demo.service;

import com.example.demo.model.Vehicle;
import com.example.demo.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public Vehicle createVehicle(@Valid Vehicle vehicle) {
        logger.info("Creating new vehicle: {}", vehicle);
        return vehicleRepository.save(vehicle);
    }

    @Transactional(readOnly = true)
    public Vehicle getVehicleById(Long id) {
        logger.info("Fetching vehicle with id: {}", id);
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehicles() {
        logger.info("Fetching all vehicles");
        return vehicleRepository.findAll();
    }

    @Transactional
    public Vehicle updateVehicle(Long id, @Valid Vehicle vehicleDetails) {
        logger.info("Updating vehicle with id: {}", id);
        Vehicle vehicle = getVehicleById(id);
        
        vehicle.setMake(vehicleDetails.getMake());
        vehicle.setModel(vehicleDetails.getModel());
        vehicle.setYear(vehicleDetails.getYear());
        vehicle.setTitle(vehicleDetails.getTitle());
        
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public void deleteVehicle(Long id) {
        logger.info("Deleting vehicle with id: {}", id);
        if (!vehicleRepository.existsById(id)) {
            throw new EntityNotFoundException("Vehicle not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> findVehiclesByMakeAndModel(String make, String model) {
        logger.info("Searching vehicles by make: {} and model: {}", make, model);
        return vehicleRepository.findByMakeAndModel(make, model);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> findVehiclesByYear(Integer year) {
        logger.info("Searching vehicles by year: {}", year);
        return vehicleRepository.findByYear(year);
    }

    @Transactional
    public Vehicle updateVehicleTitle(Long id, String newTitle) {
        logger.info("Updating title for vehicle with id: {}", id);
        Vehicle vehicle = getVehicleById(id);
        vehicle.setTitle(newTitle);
        return vehicleRepository.save(vehicle);
    }

    @Transactional(readOnly = true)
    public boolean isVehicleExists(Long id) {
        logger.debug("Checking if vehicle exists with id: {}", id);
        return vehicleRepository.existsById(id);
    }
}