package pl.paprota.zf.services;

import org.springframework.stereotype.Service;
import pl.paprota.zf.dto.EmployeeDTO;
import pl.paprota.zf.entities.Employee;
import pl.paprota.zf.repositories.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
        private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Long addEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setSurname(employeeDTO.getSurname());
        employee.setAge(employeeDTO.getAge());
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        return this.employeeRepository.save(employee).getId();
    }

    @Transactional
    public void deleteById(Long id){
        this.employeeRepository.deleteById(id);
    }

    @Transactional
    public Long updateEmployee(Long id, EmployeeDTO employeeDTO){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            Employee update = employee.get();
            update.setSurname(employeeDTO.getSurname());
            update.setAge(employeeDTO.getAge());
            update.setName(employeeDTO.getName());
            update.setSalary(employeeDTO.getSalary());
            return this.employeeRepository.save(update).getId();

        }
        else{
            return null;
        }
    }

    public List<Employee> getAllEmployee(){
        return (List<Employee>) this.employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id)
    {
        return this.employeeRepository.findById(id);
    }

    public List<Employee> getEmployeesByName(String name){
        return this.employeeRepository.findAllByName(name);
    }
}
