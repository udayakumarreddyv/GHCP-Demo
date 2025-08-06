```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootVehicleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVehicleApplication.class, args);
    }
}

@Entity
@Table(name = "vehicles")
class Vehicle {
    @Id
    private int id;
    private String make;
    private String model;
    private int year;

    // TODO: Create the create method to add vehicle data into database.
    // To be implemented.

    // TODO: Create the save method that saves the vehicle in a database.
    public void saveVehicle(Vehicle vehicle) {
        // Save the vehicle data in the database
        try {
            // Assuming you have JDBC driver for SQL query execution.
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
            Statement statement = connection.createStatement();
            String query = "INSERT INTO vehicles(make, model, year) VALUES ('" + vehicle.make + "', '" + vehicle.model + "', " + vehicle.year + ")";
            statement.executeUpdate(query);
        } catch (Exception e) {
            // Handle exception
            System.out.println("An error occurred while inserting the vehicle: " + e.getMessage());
        }
    }

    public void deleteVehicle(int id) {
        // Assuming you have JDBC driver for SQL query execution.
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
        Statement statement = connection.createStatement();
        String query = "DELETE FROM vehicles WHERE id=" + id;
        statement.executeUpdate(query);
    }

    public void updateVehicle(int id, Vehicle vehicle) {
        // Assuming you have JDBC driver for SQL query execution.
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
        Statement statement = connection.createStatement();
        String query = "UPDATE vehicles SET make='" + vehicle.make + "', model='" + vehicle.model + "', year=" + vehicle.year + "' WHERE id=" + id;
        statement.executeUpdate(query);
    }
}
```