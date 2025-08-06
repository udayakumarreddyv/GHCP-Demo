Here's an example of what a Vehicle Service class could look like using Java Spring Boot, with its dependencies injected using @Service annotations:

```java
@Service()
public class VehicleService {
    // Bean properties
    private final VehicleRepository vehicleRepository;
    
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    
    // CRUD operations
    
    // Get all vehicles from the repository
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }
    
    // Get a single vehicle by ID
    public void getSingleById(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).map(Vehicle::new);
        if (vehicle != null) {
            System.out.println("Vehicle found: " + vehicle.getName() + " (" + vehicle.getMake() + ", " + vehicle.getModel() + ")");
        } else {
            System.out.println("Vehicle not found");
        }
    }
    
    // Create a new vehicle
    public void create(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        System.out.println("Vehicle created: " + vehicle.getName());
    }
    
    // Update an existing vehicle
    public void update(Integer id, Vehicle vehicle) {
        vehicle.setMake(vehicle.getMake() == null ? "" : vehicle.getMake());
        vehicle.setModel(vehicle.getModel() == null ? "" : vehicle.getModel());
        if (vehicle.getId() != null && vehicleRepository.findById(id).isPresent()) {
            vehicle.setId(1);
        }
        vehicleRepository.save(vehicle);
        System.out.println("Vehicle updated: " + vehicle.getName());
    }
    
    // Delete a vehicle by ID
    public void delete(Integer id) {
        vehicleRepository.deleteById(id);
        System.out.println("Vehicle deleted");
    }
}
```

This example assumes that the `VehicleRepository` class is already defined in your Spring Boot application and provides methods to interact with the database. The `vehicleRepository` bean references this repository in the factory beans of the Service classes. 

In terms of business logic, it would need to be implemented according to the requirements provided for CRUD operations and business logic methods. 

The Java code above is just an example and may require modifications based on your actual use case.