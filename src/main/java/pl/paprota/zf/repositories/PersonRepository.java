package pl.paprota.zf.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.paprota.zf.entities.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
