package dq.hr.service.serviceImpl;

import dq.hr.entities.Employee;
import dq.hr.repositories.IEmployeeRepository;
import dq.hr.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return iEmployeeRepository.save(employee);
    }

    @Override
    public Employee getById(Integer id) {
        return iEmployeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<Employee> getAll() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public String delete(Integer id) {
        iEmployeeRepository.deleteById(id);
        return "Employee with id " + id + " deleted";
    }
}
