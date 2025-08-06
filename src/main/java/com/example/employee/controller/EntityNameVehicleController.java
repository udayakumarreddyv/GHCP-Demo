```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Create new vehicle object using Spring Data JPA
    @PostMapping()
    public Vehicle createVehicle(@RequestBody VehicleEntity vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
```

In this code, the `Vehicle` entity is being created with attributes make (`String`, model (`String`), year(`Integer`)) by calling the `save()` method from the `vehicleRepository`. The response will be a new `Vehicle` object.

This controller uses Spring Data JPA to interact with the database for managing vehicles and their information, making it easy to manage large amounts of data.