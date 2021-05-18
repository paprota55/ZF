package pl.paprota.zf.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.paprota.zf.dto.PostalAddressDTO;
import pl.paprota.zf.services.PostalAddressService;

@Api(value = "Controller to manage postal addresses CRUD operations")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostalAddressController {

    private final PostalAddressService postalAddressService;

    @ApiOperation(value = "Get all postal addresses from database",
            notes = "Return all postal addresses list from database",
            response = ResponseEntity.class)
    @GetMapping(value = "/getAllPostalAddresses")
    public ResponseEntity<?> getPostalAddresses(){
        return ResponseEntity.ok(this.postalAddressService.getAllPostalAddress());
    }

    @ApiOperation(value = "Get postal address from database",
            notes = "Provide an ID to look up postal address from the list",
            response = ResponseEntity.class)
    @GetMapping(value = "/getPostalAddress/{id}")
    public ResponseEntity<?> getPostalAddressById(@ApiParam(value = "ID value for the employee you need to retrieve", required = true) @PathVariable Long id){
        try {
            return ResponseEntity.ok(this.postalAddressService.getPostalAddressById(id));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @ApiOperation(value = "Adding new postal address to database",
            notes = "Provide postal address data to be saved in the database",
            response = ResponseEntity.class)
    @PostMapping(value = "/addPostalAddress")
    public ResponseEntity<?> addPostalAddress(@ApiParam(value = "Postal address data like town, postal code, street, employee id", required = true) @RequestBody PostalAddressDTO postalAddressDTO){
        try{
            Long id = this.postalAddressService.addPostalAddress(postalAddressDTO);
            String response = "PostalAddress with ID: " + id + " was successfully added";
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @ApiOperation(value = "Deleting postal address from database",
            notes = "Provide postal address ID to be deleted from database",
            response = ResponseEntity.class)
    @DeleteMapping(value = "/deletePostalAddress/{id}")
    public ResponseEntity<?> deletePostalAddress(@ApiParam(value = "ID value for the postal address you need to delete", required = true) @PathVariable Long id){
        try{
            this.postalAddressService.deleteById(id);
            String response = "Postal address with ID: " + id + " was successfully deleted";
            return ResponseEntity.ok(response);
        } catch (EmptyResultDataAccessException exc){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Postal address with this ID doesn't exist");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @ApiOperation(value = "Updating postal address in database",
            notes = "Provide postal address ID and data which need to be changed",
            response = ResponseEntity.class)
    @PutMapping(value = "/updatePostalAddress/{id}")
    public ResponseEntity<?> updatePostalAddress(@ApiParam(value = "ID value for the postal address you need to update", required = true) @PathVariable Long id, @ApiParam(value = "Postal address data to change") @RequestBody PostalAddressDTO postalAddressDTO){
        try{
            Long updatedId = this.postalAddressService.updatePostalAddress(id, postalAddressDTO);
            String response = "Postal address with ID: " + updatedId + " was successfully updated";
            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}
