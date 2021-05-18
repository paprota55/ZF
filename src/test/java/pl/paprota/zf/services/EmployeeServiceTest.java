package pl.paprota.zf.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.paprota.zf.dto.EmployeeDTO;
import pl.paprota.zf.entities.Employee;
import pl.paprota.zf.repositories.EmployeeRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    EmployeeServiceTest(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @Test
    void givenEmployees_whenGetEmployee_thenMatchEntities(){
        Employee employee = new Employee();
        employee.setName("Albert");
        employee.setSurname("Einstein");
        employee.setAge(142);
        employee.setSalary(4000D);
        employee.setId(5L);
        employeeRepository.save(employee);

        Employee employeeDB = employeeRepository.findById(5L).get();

        assertAll(() -> {
            assertEquals(employee.getName(), employeeDB.getName());
            assertEquals(employee.getSurname(), employeeDB.getSurname());
            assertEquals(employee.getSalary(), employeeDB.getSalary());
            assertEquals(employee.getAge(), employeeDB.getAge());
            assertEquals(employee.getId(), employeeDB.getId());
        });
    }

    @Test
    void givenEmployees_whenGetEmployee_thenReturnEmployeeFromDataBase(){
        Employee employee = new Employee();
        employee.setName("Albert");
        employee.setSurname("Einstein");
        employee.setAge(142);
        employee.setSalary(4000D);
        employee.setId(4L);
        employeeRepository.save(employee);

        Employee employeeDB = employeeService.getEmployeeById(4L).get();

        assertAll(() -> {
            assertEquals(employee.getName(), employeeDB.getName());
            assertEquals(employee.getSurname(), employeeDB.getSurname());
            assertEquals(employee.getSalary(), employeeDB.getSalary());
            assertEquals(employee.getAge(), employeeDB.getAge());
            assertEquals(employee.getId(), employeeDB.getId());
        });
    }

    @Test
    void givenEmployeeDTO_whenAddEmployee_ThenReturnAddedEmployeeID(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Dawid");
        employeeDTO.setSurname("Nowak");
        employeeDTO.setSalary(4000D);
        employeeDTO.setAge(22);

        Long id = employeeService.addEmployee(employeeDTO);

        Employee employeeDB = employeeService.getEmployeeById(id).get();

        assertAll(() -> {
            assertEquals(employeeDTO.getName(), employeeDB.getName());
            assertEquals(employeeDTO.getSurname(), employeeDB.getSurname());
            assertEquals(employeeDTO.getSalary(), employeeDB.getSalary());
            assertEquals(employeeDTO.getAge(), employeeDB.getAge());
        });

    }

    @Test
    void givenEmployees_whenDeleteNotExistingEmployee_ThenThrowException(){
        Employee employee = new Employee();
        employee.setName("Albert");
        employee.setSurname("Einstein");
        employee.setAge(142);
        employee.setSalary(4000D);
        employee.setId(1L);
        employeeRepository.save(employee);

        assertThrows(Exception.class, () -> employeeService.deleteById(100L));
    }


}