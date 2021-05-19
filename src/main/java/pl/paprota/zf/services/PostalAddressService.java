package pl.paprota.zf.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.paprota.zf.dto.PostalAddressDTO;
import pl.paprota.zf.entities.Employee;
import pl.paprota.zf.entities.PostalAddress;
import pl.paprota.zf.repositories.EmployeeRepository;
import pl.paprota.zf.repositories.PostalAddressRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostalAddressService {

    private final PostalAddressRepository postalAddressRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void deleteById(Long id){
        this.postalAddressRepository.deleteById(id);
    }

    public Long addPostalAddress(PostalAddressDTO postalAddressDTO) throws Exception {
        PostalAddress postalAddress = new PostalAddress();
        postalAddress.setPostalCode(postalAddressDTO.getPostalCode());
        postalAddress.setTown(postalAddressDTO.getTown());
        postalAddress.setStreet(postalAddressDTO.getStreet());
        Optional<Employee> employee = employeeRepository.findById(postalAddressDTO.getEmployeeId());
        if(employee.isEmpty()){
            throw new Exception("Employee with this ID doesn't exist");
        }
        postalAddress.setEmployee(employee.get());
        return this.postalAddressRepository.save(postalAddress).getId();
    }

    public Optional<PostalAddress> getPostalAddressById(Long id)
    {
        return this.postalAddressRepository.findById(id);
    }

    public List<PostalAddress> getAllPostalAddress(){
        return (List<PostalAddress>) this.postalAddressRepository.findAll();
    }

    @Transactional
    public Long updatePostalAddress(Long id, PostalAddressDTO postalAddressDTO) throws Exception {
        Optional<PostalAddress> optionalPostalAddress = this.postalAddressRepository.findById(id);
        if(optionalPostalAddress.isEmpty()){
            throw new Exception("Postal address with this ID doesn't exist");
        }
        PostalAddress postalAddress = new PostalAddress();
        Optional<Employee> employee = employeeRepository.findById(postalAddressDTO.getEmployeeId());
        employee.ifPresent(postalAddress::setEmployee);
        postalAddress.setPostalCode(postalAddressDTO.getPostalCode());
        postalAddress.setTown(postalAddressDTO.getTown());
        postalAddress.setStreet(postalAddressDTO.getStreet());
        postalAddress.setId(id);
        return this.postalAddressRepository.save(postalAddress).getId();
    }
}
