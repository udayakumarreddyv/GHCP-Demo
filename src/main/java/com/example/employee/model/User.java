```java
package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private Date dateOfBirth;

    // getters and setters for the fields

    public User() {
        super();
    }

    public User(String make, String model, Date dateOfBirth) {
        this.make = make;
        this.model = model;
        this.dateOfBirth = dateOfBirth;
    }
}
```