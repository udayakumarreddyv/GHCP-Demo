# GitHub Copilot Instructions for Employee Management System

<!-- Use this file to provide workspace-specific custom instructions to Copilot. -->

## Project Architecture Overview

This is a Spring Boot REST API project following a layered architecture:

```
Controller → Service → Repository → Entity
(EmployeeController) → (EmployeeService) → (EmployeeRepository) → (Employee)
```

### Key Components
- `Employee` (model): Core entity with name, number, and address
- `EmployeeRepository`: JPA interface for data access
- `EmployeeService`: Business logic layer
- `EmployeeController`: REST endpoints with Basic Auth
- `SecurityConfig`: Spring Security configuration

## Development Workflows

### Building and Running
- Use Maven for all builds: `mvn spring-boot:run`
- Test execution: `mvn test`
- Application runs on port 8080 with H2 in-memory database

### Authentication
Basic Auth is required for all API endpoints:
```java
// See SecurityConfig.java
username: "admin"
password: "admin"
```

### Database Access
H2 Console available at `/h2-console` with:
```properties
url=jdbc:h2:mem:employeedb
username=sa
password=
```

## Project Conventions

### REST API Pattern
- Base path: `/api/employees`
- Standard CRUD operations:
  - GET / → List all
  - GET /{id} → Get one
  - POST / → Create
  - PUT /{id} → Update
  - DELETE /{id} → Delete

### Testing Pattern
See `EmployeeControllerTest.java` for reference:
- Use `@WebMvcTest` for controller tests
- Mock service layer with `@MockBean`
- Include `@WithMockUser` for security context

### Logging Convention
```properties
logging.level.root=INFO
logging.level.com.example.employee=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

## Integration Points
- Spring Security for authentication
- H2 Database for persistence
- Spring Data JPA for ORM
- Lombok for reducing boilerplate

## Common Tasks
- Adding new entity fields: Update both `Employee.java` and tests
- Adding new endpoints: Follow pattern in `EmployeeController.java`
- Changing security rules: Modify `SecurityConfig.java`
