package dq.hr.service.serviceImpl;

import dq.hr.entities.Employee;
import dq.hr.exceptions.ResourceNotFoundException;
import dq.hr.repositories.IEmployeeRepository;
import dq.hr.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository iEmployeeRepository;

    public EmployeeServiceImpl(IEmployeeRepository iEmployeeRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
    }

    @Override
    public Employee create(Employee employee) {
        return iEmployeeRepository.save(employee);
    }

    @Override
    public Employee update(Integer id, Employee employee) {
        Employee employeeExist = iEmployeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeExist.setName(employee.getName());
        employeeExist.setDepartment(employee.getDepartment());
        employeeExist.setSalary(employee.getSalary());

        return iEmployeeRepository.save(employeeExist);
    }

    @Override
    public Employee getById(Integer id) {
        return iEmployeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + " not found"));
    }

    @Override
    public List<Employee> getAll() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Integer id) {
        iEmployeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + " not found"));

        iEmployeeRepository.deleteById(id);

        var response = new HashMap<String, Boolean>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
