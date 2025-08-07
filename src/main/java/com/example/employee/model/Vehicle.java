package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String make;
    private String model;
    private String year;
    
    // Constructors
    public Vehicle() {}
    
    // ID getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }    
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
}