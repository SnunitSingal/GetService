package dal.connectors;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dal.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String lastName);
}