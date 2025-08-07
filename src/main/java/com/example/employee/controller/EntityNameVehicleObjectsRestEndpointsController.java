```java
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
public class VehicleController {

    // REST API endpoint for creating new vehicle objects
    @PostMapping("/vehicle")
    public String createVehicle(@Valid Vehicle vehicle) {
        return "Vehicle created successfully";
    }

    // REST API endpoint for updating existing vehicle object
    @PutMapping("/vehicle/{id}")
    public String updateVehicle(@Valid Integer id, @RequestBody Vehicle vehicle) {
        Vehicle existingVehicle = new Vehicle();
        existingVehicle.setMake(vehicle.getMake());
        existingVehicle.setModel(vehicle.getModel());
        existingVehicle.setYear(vehicle.getYear());

        if (existingVehicle.getId() == null) {
            throw new RuntimeException("vehicle does not exist");
        }

        existingVehicle.setId(id);
        return "Vehicle updated successfully";
    }

    // REST API endpoint for deleting vehicle object
    @DeleteMapping("/vehicle/{id}")
    public void deleteVehicle(@PathVariable Integer id) {
        Vehicle vehicle = getVehicleById(id);

        if (vehicle != null) {
            vehicle.setIsDeleted(true);
            saveVehicle(vehicle);
        }
    }

    private Vehicle getVehicleById(Integer id) {
        return ... // Query database to find vehicle by ID
    }

    private void saveVehicle(Vehicle vehicle) {
        ... // Save the vehicle object in a database table

}
}

// Entity definition for Vehicle object
class Vehicle {

    @Valid
    Integer make;
    @Valid String model;
    @Valid Integer year;

    public Vehicle() {
    }
}
```