package app.controller;
 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dal.entities.Person;
import app.dal.services.PersonService;
import app.errors.RecordNotFoundException;

@RestController
@RequestMapping("/persons")
public class Controller
{
    @Autowired
    PersonService service;
 
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> list = service.getAllPersons();
 
        return new ResponseEntity<List<Person>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        Person entity = service.getPersonById(id);
 
        return new ResponseEntity<Person>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(Person person)
                                                    throws RecordNotFoundException {
        Person newPerson = service.createPerson(person);
        return new ResponseEntity<Person>(newPerson, new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, Person person)
                                                    throws RecordNotFoundException {
        Person updated = service.updatePerson(id, person);
        return new ResponseEntity<Person>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deletePersonById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deletePersonById(id);
        return HttpStatus.ACCEPTED;
    }
 
}