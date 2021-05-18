package pl.paprota.zf.services;

import org.springframework.stereotype.Service;
import pl.paprota.zf.entities.PostalAddress;
import pl.paprota.zf.repositories.PostalAddressRepository;

import java.util.List;

@Service
public class PostalAddressService {

    private final PostalAddressRepository postalAddressRepository;

    public PostalAddressService(PostalAddressRepository postalAddressRepository) {
        this.postalAddressRepository = postalAddressRepository;
    }



    public List<PostalAddress> getAllPostalAddress(){
        return (List<PostalAddress>) this.postalAddressRepository.findAll();
    }
}
