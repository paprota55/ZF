package pl.paprota.zf.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.paprota.zf.dto.EmployeeDTO;
import pl.paprota.zf.services.EmployeeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        try {
            String response = "Employee with ID: " + this.employeeService.addEmployee(employeeDTO) + " was successfully added";
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        try{
            this.employeeService.deleteById(id);
            String response = "Employee with ID: " + id + " was successfully deleted";
            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employeeDTO){
        try{
            String response;
            Long updatedId = this.employeeService.updateEmployee(id, employeeDTO);
            if(updatedId!=null) {
                response = "Employee with ID: " + updatedId + " was successfully updated";
                return ResponseEntity.ok(response);
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee with this ID doesn't exist");
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/getAllEmployees")
    public ResponseEntity<?> getEmployees(){
        return ResponseEntity.ok(this.employeeService.getAllEmployee());
    }

    @GetMapping(value = "/getEmployee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(this.employeeService.getEmployeeById(id));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/getEmployeesByName/{name}")
    public ResponseEntity<?> getEmployeeByName(@PathVariable String name){
        return ResponseEntity.ok(this.employeeService.getEmployeesByName(name));
    }
}
