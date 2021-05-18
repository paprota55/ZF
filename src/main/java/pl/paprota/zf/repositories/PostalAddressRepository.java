package pl.paprota.zf.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.paprota.zf.entities.PostalAddress;

@Repository
public interface PostalAddressRepository extends CrudRepository<PostalAddress, Long> {
}
