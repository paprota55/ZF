package pl.paprota.zf.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.paprota.zf.services.PostalAddressService;

@Controller
@RequestMapping("/api")
public class PostalAddressController {

    private final PostalAddressService postalAddressService;

    public PostalAddressController(PostalAddressService postalAddressService) {
        this.postalAddressService = postalAddressService;
    }

    @GetMapping(value = "/getAllPostalAddresses")
    public ResponseEntity<?> getPostalAddresses(){
        return ResponseEntity.ok(this.postalAddressService.getAllPostalAddress());
    }
}
