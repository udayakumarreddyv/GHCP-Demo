```java
package com.example.demo.repository;
// Spring Data JPA repository interface

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // Get all vehicles by make
    @Override
    public List<Vehicle> findByMake(String make);
    
    // Get one vehicle based on id
    @Override
    public Vehicle findOne(Long id);
}
```