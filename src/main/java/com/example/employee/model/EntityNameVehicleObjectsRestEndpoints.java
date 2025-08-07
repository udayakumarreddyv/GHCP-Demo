```java
package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VehicleObjects")
public class VehicleObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "make", columnDefinition = "TEXT(20)")
    private String make;
    
    @Column(name = "model", columnDefinition = "TEXT(150)")
    private String model;
    
    @Column(name = "year", columnDefinition = "DATE")
    private String year;
    
    // Constructor, getters, setters, etc.

}
```