package pl.paprota.zf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.paprota.zf.dto.PostalAddressDTO;
import pl.paprota.zf.services.PostalAddressService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostalAddressController {

    private final PostalAddressService postalAddressService;

    @GetMapping(value = "/getAllPostalAddresses")
    public ResponseEntity<?> getPostalAddresses(){
        return ResponseEntity.ok(this.postalAddressService.getAllPostalAddress());
    }

    @GetMapping(value = "/getPostalAddress/{id}")
    public ResponseEntity<?> getPostalAddressById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(this.postalAddressService.getPostalAddressById(id));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PostMapping(value = "/addPostalAddress")
    public ResponseEntity<?> addPostalAddress(@RequestBody PostalAddressDTO postalAddressDTO){
        try{
            Long id = this.postalAddressService.addPostalAddress(postalAddressDTO);
            String response = "PostalAddress with ID: " + id + " was successfully added";
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletePostalAddress/{id}")
    public ResponseEntity<?> deletePostalAddress(@PathVariable Long id){
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

    @PutMapping(value = "/updatePostalAddress/{id}")
    public ResponseEntity<?> updatePostalAddress(@PathVariable Long id, @RequestBody PostalAddressDTO postalAddressDTO){
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
