```java
package com.example.demo.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    private Long id;
    private String make;
    private String model;
    private Date date;

    // Constructor, getters, and setters
    public Vehicle() {}

    public Vehicle(Long id, String make, String model, Date date) {
        this.id = id;
        setMake(make);
        setModel(model);
        setDate(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
```