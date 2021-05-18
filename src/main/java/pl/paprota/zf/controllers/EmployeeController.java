package pl.paprota.zf.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.paprota.zf.dto.EmployeeDTO;
import pl.paprota.zf.services.EmployeeService;

import javax.validation.Valid;

@Api(value = "Controller to manage employees CRUD operations")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    @ApiOperation(value = "Adding new employee to database",
                    notes = "Provide employee data to be saved in the database",
                    response = ResponseEntity.class)
    @PostMapping(value = "/addEmployee")
    public ResponseEntity<?> addEmployee(@ApiParam(value = "Employee data like name, surname, salary, age", required = true) @RequestBody EmployeeDTO employeeDTO){
        try {
            String response = "Employee with ID: " + this.employeeService.addEmployee(employeeDTO) + " was successfully added";
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @ApiOperation(value = "Deleting employee from database",
            notes = "Provide employee ID to be deleted from database",
            response = ResponseEntity.class)
    @DeleteMapping(value = "/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@ApiParam(value = "ID value for the employee you need to delete", required = true) @PathVariable Long id){
        try{
            this.employeeService.deleteById(id);
            String response = "Employee with ID: " + id + " was successfully deleted";
            return ResponseEntity.ok(response);
        }
        catch (EmptyResultDataAccessException exc){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Employee with this ID doesn't exist");
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @ApiOperation(value = "Updating employee in database",
            notes = "Provide employee ID and data which need to be changed",
            response = ResponseEntity.class)
    @PutMapping(value = "/updateEmployee/{id}")
    public ResponseEntity<?> updateEmployee(@ApiParam(value = "ID value for the employee you need to update", required = true) @PathVariable Long id, @ApiParam(value = "Employee data to change") @RequestBody EmployeeDTO employeeDTO){
        try{
            Long updatedId = this.employeeService.updateEmployee(id, employeeDTO);
            String response = "Employee with ID: " + updatedId + " was successfully updated";
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @ApiOperation(value = "Get all employees from database",
            notes = "Return all employees list from database",
            response = ResponseEntity.class)
    @GetMapping(value = "/getAllEmployees")
    public ResponseEntity<?> getEmployees(){
        return ResponseEntity.ok(this.employeeService.getAllEmployee());
    }


    @ApiOperation(value = "Get employee from database",
            notes = "Provide an ID to look up employee from the list",
            response = ResponseEntity.class)
    @GetMapping(value = "/getEmployee/{id}")
    public ResponseEntity<?> getEmployeeById(@ApiParam(value = "ID value for the employee you need to retrieve", required = true) @PathVariable Long id){
        try {
            return ResponseEntity.ok(this.employeeService.getEmployeeById(id));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}
