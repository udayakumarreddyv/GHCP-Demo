### Entity Requirements:

**Vehicle.java**
```java
import java.time.LocalDate;
import java.util.List;

public class Vehicle {

    private Long id;
    private String make;
    private List<String> modelList = new ArrayList<>();
    private LocalDate year;

    public static void main(String[] args) {
        System.out.println(new Vehicle().getMake());
    }
}
```

**VehicleService.java**
```java
import java.time.LocalDate;
import java.util.List;

public class VehicleService {

    public static List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    private VehicleRepository vehicleRepository = new EmployeeRepository();

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }
}
```

**EmployeeRepository.java**
```java
import java.time.LocalDate;

public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public static void createEmployee(Employee employee) {
        if (employees.contains(employee)) {
            throw new RuntimeException("This employee has already been created");
        } else {
            employees.add(employee);
        }
    }

    public static Employee getEmployeeById(Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public void updateEmployee(Employee employee, Long id) {
        if (employees.stream().anyMatch(e -> e.getId().equals(id))) {
            employee.setAddress("New Address");
            employee.setName("John Doe");
            employee.setSalary(5000);
            employee.setYearsExperience(25);

            employees.forEach(employee -> {
                if (employee.getName().equals("John Doe")) {
                    employee.setSalary(4700);
                }
            });

            try {
                EmployeeRepository.saveEmployee(employee);
                System.out.println("Employee updated successfully");
            } catch (Exception e) {
                System.out.println("Error updating employee: " + e.getMessage());
            }

        } else {
            throw new RuntimeException("This employee does not exist");
        }
    }

    public void deleteEmployee(Long id) {
        if (employees.stream()
                .filter(employee -> employee.getId().equals(id)) != null) {
                    employees.remove(employees.stream()
                            .filter(employee -> employee.getId().equals(id)).findAny().orElseThrow());
                } else {
            throw new RuntimeException("This employee does not exist");
        }
    }

    public void saveEmployee(Employee employee) {
        if (employees.isEmpty()) {
            employees.add(employee);
            return;
        }

        Employee savedEmployee = employees.stream()
                .filter(e -> e.getName().equals(employee.getName()))
                .findAny().orElseThrow();

        try {
            employee.setName(savedEmployee.getName() + " " + employee.getSalary());
        } catch (Exception e) {
            System.out.println("Error saving employee: " + e.getMessage());
        }
    }

    public void saveOrUpdateEmployee(Employee employee) {
        if (employees.isEmpty()) {
            employees.add(employee);
            return;
        }

        Employee savedEmployee = employees.stream()
                .filter(e -> e.getName().equals(employee.getName()))
                .findAny().orElseThrow();

        try {
            employee.setName(savedEmployee.getName() + " " + savedEmployee.getSalary());
        } catch (Exception e) {
            System.out.println("Error saving employee: " + e.getMessage());
        }
    }

    public void searchEmployees(List<String> filteredNames, List<Employee> employees) {
        if (filteredNames.isEmpty()) return;

        filteredNames.forEach(employeeName -> {
            String[] split = employeeName.split(",");
            Integer id = Integer.parseInt(split[0]);
            List<Employee> list;
            if (!employees.stream().anyMatch(e -> e.getName().equals(split[1]))) {
                list = employees.stream()
                        .filter(
                                e -> e.getName()
                                        .toLowerCase()
                                        .contains(filteredNames.get(0).toLowerCase())
                            )
                        .collect(Collectors.toList());
            } else {
                list = employees.stream()
                        .filter(e -> e.getName()
                                .equals(split[1]))
                        .collect(Collectors.toList());
            }

            if (!list.isEmpty()) {
                filteredNames.forEach(employeeName -> {
                    Employee savedEmployee = employees.stream()
                            .filter(e -> e.getName().toLowerCase().contains(filteredNames.get(0).toLowerCase()))
                            .findFirst()
                            .orElseThrow();
                    List<Employee> newEmployees = employeeService.searchEmployeeByMakeAndModel(
                            savedEmployee.getMake(), savedEmployee.getModel(),
                            LocalDate.parse(employeeName.substring(0, 4)),
                            LocalDateTime.parse(employeeName.substring(employeeName.length() + 1))
                    );
                    if (newEmployees.isEmpty()) {
                        list.add(savedEmployee);
                    } else {
                        list.set(list.indexOf(savedEmployee), newEmployees.get(0));
                    }
                });
            }

        });

        filteredNames.forEach(employeeName -> {
            String[] split = employeeName.split(",");
            Integer id = Integer.parseInt(split[0]);
            List<Employee> list;
            if (!employees.stream().anyMatch(e -> e.getName()
                                        .toLowerCase()
                                        .contains(filteredNames.get(0).toLowerCase()))) {
                list = employees.stream()
                        .filter(
                                e -> e.getName()
                                        .toLowerCase()
                                        .contains(filteredNames.get(1).toLowerCase())
                            )
                        .collect(Collectors.toList());
            } else {
                list = employees.stream()
                        .filter(e -> e.getName()
                                .equals(split[1]))
                        .collect(Collectors.toList());
            }

            if (!list.isEmpty()) {
                filteredNames.forEach(employeeName -> {
                    Employee savedEmployee = employees.stream()
                            .filter(e -> e.getName().toLowerCase()
                                    .contains(filteredNames.get(0).toLowerCase())
                                    && e.getModel()
                                            .equals(split[2]))
                            .findFirst()
                            .orElseThrow();
                    List<Employee> newEmployees = employeeService.searchEmployeeByMakeAndModel(
                            savedEmployee.getMake(), savedEmployee.getModel(),
                            LocalDate.parse(employeeName.substring(0, 4)),
                            LocalDateTime.parse(employeeName.substring(employeeName.length() + 1))
                    );
                    if (newEmployees.isEmpty()) {
                        list.add(savedEmployee);
                    } else {
                        list.set(list.indexOf(savedEmployee), newEmployees.get(0));
                    }
                });
            }

        });

        filteredNames.forEach(employeeName -> {
            String[] split = employeeName.split(",");
            Integer id = Integer.parseInt(split[0]);
            List<Employee> list;
            if (!employees.stream().anyMatch(e -> e.getName()
                                        .equals(split[1]))) {
                list = employees.stream()
                        .filter(
                                e -> e.getName() + " " + (split[2].isEmpty()) ? split[3] : split[2])
                        .collect(Collectors.toList());
            } else {
                list = employees.stream().filter(e -> e.getName() + " " + (split[1].isEmpty()) ? split[2] : split[2]) //
                        .collect(Collectors.toList());
            }

            if (!list.isEmpty()) {
                filteredNames.forEach(employeeName -> {
                    Employee savedEmployee = employees.stream()
                            .filter(e -> e.getName() + " " + split[3])
                            .findFirst()
                            .orElseThrow();
                    List<Employee> newEmployees = employeeService.searchEmployeeByMakeAndModel(
                            savedEmployee.getMake(), savedEmployee.getModel(),
                            LocalDate.parse(employeeName.substring(0, 4)),
                            LocalDateTime.parse(employeeName.substring(employeeName.length() + 1))
                    );
                    if (newEmployees.isEmpty()) {
                        list.add(savedEmployee);
                    } else {
                        list.set(list.indexOf(savedEmployee), newEmployees.get(0));
                    }
                });
            }

        });

        filteredNames.forEach(employeeName -> {
            String[] split = employeeName.split(",");
            Integer id = Integer.parseInt(split[0]);
            List<Employee> list;
            if (!employees.stream().anyMatch(e -> e.getName() + " " + split[2])) {
                list = employees.stream()
                        .filter(
                                e -> e.getName() + " " + (split[1].isEmpty()) ? split[3] : split[2])
                        .collect(Collectors.toList());
            } else {
                list = employees.stream().filter(e -> e.getName() + " " + (split[1].isEmpty()) ? split[2]
                        : split[2]) //
                        .collect(Collectors.toList());
            }

            if (!list.isEmpty()) {
                filteredNames.forEach(employeeName -> {
                    Employee savedEmployee = employees.stream()
                            .filter(e -> e.getName() + " " + split[3])
                            .findFirst()
                            .orElseThrow();
                    List<Employee> newEmployees = employeeService.searchEmployeeByMakeAndModel(
                            savedEmployee.getMake(), savedEmployee.getModel(),
                            LocalDate.parse(employeeName.substring(0, 4)),
                            LocalDateTime.parse(employeeName.substring(employeeName.length() + 1))
                    );
                    if (newEmployees.isEmpty()) {
                        list.add(savedEmployee);
                    } else {
                        list.set(list.indexOf(savedEmployee), newEmployees.get(0));
                    }
                });
            }

        });

    }
}
```

**EmployeeController.java**
```java
import java.time.LocalDate;

public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    public String createEmployee(EmployeeRequest request) {
        if (employeeService.searchEmployeeByMakeAndModel(request.getMake(), request.getModel())) {
            throw new RuntimeException("This employee already exists");
        }

        if (!request.getName().equals("") && !request.getAddress().isEmpty()) {
            return "Creating an Employee.";
        }
        return null;
    }

    public List<Employee> searchEmployees(List<String> filteredNames) {
        List<Employee> list = employeeService.searchEmployeeByMakeAndModel(
                request.getMake(), request.getModel(),
                LocalDate.parse(request.getName()), LocalDateTime.parse(request.getAddress())
        );

        if (list.isEmpty()) {
            return list;
        }
        String[] split = filteredNames.split(",");
        Integer id = Integer.parseInt(split[0]);
        List<Employee> newEmployees = employeeService.searchEmployeeByMakeAndModel(
                request.getMake(), request.getModel(),
                LocalDate.parse(filteredList.get(id).getName()), LocalDateTime.parse(filteredList.get(id).getAddress())
        );

        if (newEmployees.isEmpty()) {
            return list;
        }
        if (!list.isEmpty()) {
            return new EmployeesBuilder().add(employeeService.searchEmployeeByMakeAndModel(
                    request.getMake(), request.getModel(),
                    LocalDate.parse(request.getName()), LocalDateTime.parse(request.getAddress()))
            ).toList()
                .addAll(newEmployees.stream()
                        .collect(Collectors.toList()));
        }

        return list;
    }

    public Employee getEmployeeById(Long id) {
        return employeeService.searchEmployeeByMakeAndModel(
                request.getMake(), request.getModel(),
                LocalDate.parse(request.getName()), LocalDateTime.parse(request.getAddress())
        ).get(0);
    }
}
```