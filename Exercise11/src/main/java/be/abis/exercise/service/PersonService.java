package be.abis.exercise.service;


import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();
    Person findPerson(int id) throws PersonNotFoundException;
    Person findPerson(String emailAddress, String passWord) throws PersonNotFoundException;
    Person addPerson(Person p) throws PersonAlreadyExistsException;
    void deletePerson(int id) throws PersonCanNotBeDeletedException;
    Person changePassword(Person p, String newPswd) throws PersonNotFoundException;
    List<Person> findPersonsByCompanyName(String compName);
    Person findPerson(String email) throws PersonNotFoundException;
}
