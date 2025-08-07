package com.example.demo.controller;

import com.example.demo.model.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService service;
    
    @GetMapping
    public List<Vehicle> getAll() {
        return service.findAll();
    }
    
    @PostMapping
    public Vehicle create(@RequestBody Vehicle entity) {
        return service.save(entity);
    }
}