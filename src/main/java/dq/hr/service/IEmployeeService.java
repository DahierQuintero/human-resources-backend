package dq.hr.service;

import dq.hr.entities.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee create(Employee employee);
    Employee update(Integer id, Employee employee);
    Employee getById(Integer id);
    List<Employee> getAll();
    String delete(Integer id);
}
