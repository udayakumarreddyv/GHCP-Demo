```java
package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private Long addressId;
    private LocalDateTime dob;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEmployee;

    private String make;
    private String model;
    private int year;
    private Double price;
    private LocalDateTime createdDate;

    // Additional getter and setter methods

    public Employee() {
        super();
    }

    // Bean constructor for Employee object
}
```