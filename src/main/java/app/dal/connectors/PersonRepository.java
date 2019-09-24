package app.dal.connectors;

import org.springframework.data.jpa.repository.JpaRepository;
import app.dal.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}