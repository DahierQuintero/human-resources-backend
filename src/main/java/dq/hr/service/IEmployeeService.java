package dq.hr.service;

import dq.hr.entities.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {

    Employee create(Employee employee);
    Employee update(Integer id, Employee employee);
    Employee getById(Integer id);
    List<Employee> getAll();
    Map<String, Boolean> delete(Integer id);
}
