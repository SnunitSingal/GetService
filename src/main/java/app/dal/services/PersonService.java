package app.dal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dal.connectors.PersonRepository;
import app.dal.entities.Person;
import app.errors.RecordNotFoundException;
 
  
@Service
public class PersonService {
     
    @Autowired
    private PersonRepository repository;
     
    public List<Person> getAllPersons()
    {
        List<Person> personList = repository.findAll();
         
        if(personList.size() > 0) {
            return personList;
        } else {
            return new ArrayList<Person>();
        }
    }
     
    public Person getPersonById(Long id) throws RecordNotFoundException
    {
        Person person = repository.findOne(id);
          
        if(person != null) {
            return person;
        } else {
            throw new RecordNotFoundException("No person record exist for given id");
        }
    }
     
    public Person updatePerson(Long id,Person entity) throws RecordNotFoundException 
    {
        	Person person = repository.findOne(id);
            if (person != null) {
            	
              if (entity.getEmail() != null ) {
            	person.setEmail(entity.getEmail());
              }
              if (entity.getFirstName() != null ) {
                person.setFirstName(entity.getFirstName());
              }
              if (entity.getLastName() != null ) {
                person.setLastName(entity.getLastName());
              }
              repository.save(person);
             
              return person;
        } else {
            throw new RecordNotFoundException("No person record exist for given id");
        }
    }
    

    public Person createPerson(Person entity) 
    {
        	Person person = new Person(entity.getFirstName(),entity.getLastName(),entity.getEmail());
            repository.save(person);
            return person;
    }
    
    public void deletePersonById(Long id) throws RecordNotFoundException
    {
        Person person = repository.findOne(id);
         
        if (person != null )
        {
            repository.delete(id);
        } else {
            throw new RecordNotFoundException("No person record exist for given id");
        }
    }
}
