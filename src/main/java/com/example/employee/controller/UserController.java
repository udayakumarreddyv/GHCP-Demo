Here's how you can create a REST controller in Java Spring Boot for the `User` entity:

```java
@Controller
public class UserController {

    // Get all the users
    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a single user
    @GetMapping("/api/user/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    // Create a new user
    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Update a single user
    @PutMapping("/api/user/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userRepository.findById(id)
                .map(u -> u.updateFields(user));
    }

    // Delete a single user
    @DeleteMapping("/api/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
```

In the above code, we've created three endpoints: `getAllUsers` to get all users, `getUser` to get a single user, and `createUser`, `updateUser`, and `deleteUser` respectively. Each endpoint has its own method with appropriate HTTP methods (GET, POST, PUT, DELETE) followed by the fields to update or delete.

The DTO (Data Transfer Object) for each entity class is imported as well.

Please note that this example requires Spring Boot 2.0 or above and a JavaSpring MVC framework like Struts or Hibernate MVC.