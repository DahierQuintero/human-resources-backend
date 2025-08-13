package dq.hr.controllers;

import dq.hr.entities.Employee;
import dq.hr.service.IEmployeeService;
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
public class EmployeeController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeController.class);

    private IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAll() {
        var employees = employeeService.getAll();
        employees.forEach((employee -> LOGGER.info(employee.toString())));
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        LOGGER.info("Employee to be created: " + employee.toString());
        return new ResponseEntity<>(employeeService.create(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(id, employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(employeeService.delete(id), HttpStatus.OK);
    }

}
