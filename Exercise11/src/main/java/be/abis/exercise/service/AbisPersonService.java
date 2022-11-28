package be.abis.exercise.service;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CompanyJPARepository;
import be.abis.exercise.repository.PersonJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AbisPersonService implements PersonService {

    @Autowired
    PersonJPARepository personRepository;
    @Autowired
    CompanyJPARepository companyRepository;

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person findPerson(int id) throws PersonNotFoundException {
        Person p = personRepository.findByPersonId(id);
        if (p ==null) throw new PersonNotFoundException("Person with id " + id + " was not found.");
        return p;
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) throws PersonNotFoundException {
        Person p = personRepository.findByEmailAddressAndPassword(emailAddress, passWord);
        if (p ==null) throw new PersonNotFoundException("Person with email " + emailAddress + " was not found.");
        return p;
    }

    @Override
    @Transactional
    public Person addPerson(Person p) throws PersonAlreadyExistsException{
        Person existingPerson = personRepository.findByEmailAddress(p.getEmailAddress());

        if (existingPerson == null){
            if (p.getCompany() != null){
                Company c = companyRepository.findByNameAndTown(p.getCompany().getName().toUpperCase(), p.getCompany().getAddress().getTown().toUpperCase());
                if (c != null) p.setCompany(c);
            }

            return personRepository.save(p);
        } else {
            throw new PersonAlreadyExistsException("This person with id "+ p.getPersonId() + " and email "
            + p.getEmailAddress() + " already exists.");
        }
    }

    @Transactional
    @Override
    public Person addHobbyToPerson(int personID, String hobby) throws PersonNotFoundException {
        Person p = personRepository.findByPersonId(personID);
        p.addHobby(hobby);
        return personRepository.save(p);
    }

    @Override
    public void deletePerson(int id) throws PersonCanNotBeDeletedException {
        try {
            personRepository.deleteById(id);
        } catch (DataIntegrityViolationException | EmptyResultDataAccessException e){
            throw new PersonCanNotBeDeletedException("Person cannot be deleted: " + e.getMessage());
        }
    }

    public Person findPerson(String email) throws PersonNotFoundException {
        Person p = personRepository.findByEmailAddress(email);
        if (p==null) throw new PersonNotFoundException("person was not found");
        return p;
    }

    @Override
    public Person changePassword(Person p, String newPswd) throws PersonNotFoundException {
        Person existingPerson = personRepository.findByPersonId(p.getPersonId());
        if (existingPerson != null){
            p.setPassword(newPswd);
            return personRepository.save(p);
        } else {
            throw new PersonNotFoundException("Person with id " + p.getPersonId() + " could not be found.");
        }
    }

    @Override
    public List<Person> findPersonsByCompanyName(String compName) {
        return personRepository.findByCompanyName(compName);
    }
}
