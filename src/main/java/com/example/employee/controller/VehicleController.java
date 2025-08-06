```java
// Entity class for vehicle
class Vehicle {
    private String make;
    private String model;
    private int year;

    // Constructor to initialize attribute values
    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Getters and setters for attributes
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

    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
}

// Controller to create a new vehicle
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    // Create a new vehicle and add it to the repository
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        try {
            return restTemplate.postForEntity(
                    UriComponentsBuilder.fromUriString("https://restful-vehicle-api.com/v1/vehicles")
                            .withPath("/api/create/{make}/{model}")
                            .build()
                            .toHttp(),
                    vehicle,
                    new ResponseEntity.BodyMatcher(NotMatchHeaderException.class)
            );
        } catch (HttpClientErrorException exception) {
            return exception.build();
        }
    }

    // Get all vehicles from the repository
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        try {
            return restTemplate.getForObject("https://restful-vehicle-api.com/v1/vehicles", 
                    new ResponseEntity<List<Vehicle>>(List.of(new Vehicle(1, "Toyota Camry", 2020), 
                            new Vehicle(2, "Honda Civic", 2021)), List.of()));
        } catch (HttpClientErrorException exception) {
            return exception.build();
        }
    }

    // Update an existing vehicle
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") int id, @RequestBody Vehicle updatedVehicle) throws HttpClientErrorException {
        try {
            return restTemplate.put(
                    UriComponentsBuilder.fromUriString("https://restful-vehicle-api.com/v1/vehicles/{id}", 
                            new URL("/api/update/{make}/{model}/{year}"))
                            .withPath("/{id}")
                            .build()
                            .toHttp(),
                    updatedVehicle,
                    new ResponseEntity.BodyMatcher(NotMatchHeaderException.class)
            );
        } catch (HttpClientErrorException exception) {
            return exception.build();
        }
    }

    // Delete an existing vehicle
    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable("id") int id) throws HttpClientErrorException {
        try {
            restTemplate.delete(
                    UriComponentsBuilder.fromUriString("https://restful-vehicle-api.com/v1/vehicles/{id}", 
                            new URL("/api/delete/{make}/{model}/{year}"))
                            .withPath("/{id}")
                            .build()
                            .toHttp(),
            );
        } catch (HttpClientErrorException exception) {
            exception.printStackTrace();
        }
    }

}

// Service for adding vehicle
@Service
public class VehicleService {

    public void saveVehicle(Vehicle vehicle) {
        // Here you would insert the new vehicle into your database
    }
}
```

This code snippet is assuming that there's already a `RestClient` available, which can be replaced with your actual RESTful API endpoint. The controller handles HTTP GET, POST, PUT and DELETE operations for creating, updating and deleting vehicles respectively.