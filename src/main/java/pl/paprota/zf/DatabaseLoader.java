package pl.paprota.zf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.paprota.zf.entities.Employee;
import pl.paprota.zf.entities.PostalAddress;
import pl.paprota.zf.repositories.EmployeeRepository;
import pl.paprota.zf.repositories.PostalAddressRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final PostalAddressRepository postalAddressRepository;

    @Value("${spring.datasource.username}")
    private String dataBase;

    public DatabaseLoader(EmployeeRepository employeeRepository, PostalAddressRepository postalAddressRepository) {
        this.employeeRepository = employeeRepository;
        this.postalAddressRepository = postalAddressRepository;
    }

    @Override
    public void run(final String... strings) {

        if (this.dataBase.equals("postgres")) {

            Employee employee = new Employee();
            employee.setSalary(4000D);
            employee.setAge(20);
            employee.setName("Romuald");
            employee.setSurname("Nowak");
            Long employeeId = employeeRepository.save(employee).getId();
            //employee.setId(employeeId);

            PostalAddress address = new PostalAddress();
            address.setPostalCode("42-124");
            address.setStreet("Wilcza 2");
            address.setTown("Gniezno");
            address.setEmployee(employee);
            postalAddressRepository.save(address);
        }
    }
}
