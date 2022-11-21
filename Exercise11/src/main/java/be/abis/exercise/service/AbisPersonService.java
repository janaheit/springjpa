package be.abis.exercise.service;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CompanyJPARepository;
import be.abis.exercise.repository.PersonJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return personRepository.findByPersonId(id);
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) throws PersonNotFoundException {
        return personRepository.findByEmailAddressAndPassword(emailAddress, passWord);
    }

    @Override
    @Transactional
    public Person addPerson(Person p) throws PersonAlreadyExistsException, PersonNotFoundException {
        Person existingPerson = personRepository.findByPersonId(p.getPersonId());
        if (existingPerson == null){
            if (p.getCompany() != null){
                Company c = companyRepository.findByNameAndTown(p.getCompany().getName().toUpperCase(), p.getCompany().getAddress().getTown().toUpperCase());
                if (c != null) p.setCompany(c);
            }

            return personRepository.save(p);
        } else {
            System.out.println("Person " + p.getFirstName() + " already exists...");
            return null;
        }
    }

    @Override
    public void deletePerson(int id) throws PersonCanNotBeDeletedException {
        personRepository.deleteById(id);
    }

    @Override
    public Person changePassword(Person p, String newPswd) throws PersonNotFoundException {
        Person existingPerson = personRepository.findByPersonId(p.getPersonId());
        if (existingPerson != null){
            p.setPassword(newPswd);
            return personRepository.save(p);
        } else {
            System.out.println("Person " + p.getFirstName() + " does not eists...");
            return null;
        }
    }

    @Override
    public List<Person> findPersonsByCompanyName(String compName) {
        return personRepository.findByCompanyName(compName);
    }
}
