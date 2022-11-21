package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonJPARepository extends JpaRepository<Person, Integer> {
	
	    Person findByPersonId(int id) throws PersonNotFoundException;
	    Person findByEmailAddressAndPassword(String emailAddress, String passWord) throws PersonNotFoundException;
		//TODO implement this:
		List<Person> findByCompanyName(String compName);
}
