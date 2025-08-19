package dq.hr.controllers;

import dq.hr.entities.Employee;
import dq.hr.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/hr/employees")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Employees", description = "Employee related transactions")
public class EmployeeController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeController.class);

    private IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Get all employees",
            description = "Return a list of all employees registered in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee list obtained successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAll() {
        var employees = employeeService.getAll();
        employees.forEach((employee -> LOGGER.info(employee.toString())));
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Operation(
            summary = "Create new employee",
            description = "Create a new employee in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Employee created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data"
            )
    })
    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        LOGGER.info("Employee to be created: " + employee.toString());
        return new ResponseEntity<>(employeeService.create(employee), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Search for an employee by ID",
            description = "Returns a specific employee based on their ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee doesn't found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Update employee",
            description = "Update an existing employee"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(id, employee), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete employee",
            description = "Delete an employee from the system"
    )
    @ApiResponse(responseCode = "204", description = "Employee successfully deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(employeeService.delete(id), HttpStatus.NO_CONTENT);
    }

}
