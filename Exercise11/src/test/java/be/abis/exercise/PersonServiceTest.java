package be.abis.exercise;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonServiceTest {
	
	@Autowired
    PersonService personService;

	@Test
	@Order(1)
	public void person1ShouldBeCalledJan() throws PersonNotFoundException {
		String firstName = personService.findPerson(1).getFirstName();
		assertEquals("jan",firstName.trim().toLowerCase());
	}

	/*@Test
	@Order(2)
	public void thereShouldBe3PersonsInTheFile(){
		int nrOfPersons = personService.getAllPersons().size();
		assertEquals(3,nrOfPersons);
	}
	 */

	@Test
	//@Transactional
	public void addNewPersonWithExistingCompany() throws PersonAlreadyExistsException, PersonNotFoundException {
		Address a = new Address("Diestsevest","32","3000","Leuven","B");
		Company c = new Company("Abis","016/455610","BE12345678",a);
		Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl",c);
		assertEquals(p, personService.addPerson(p));
	}

	@Test
	//@Transactional
	public void addNewPersonWithNewCompany() throws PersonAlreadyExistsException, PersonNotFoundException {
		Address a = new Address("Diestsevest","33","3000","Leuven","B");
		Company c = new Company("Microsoft","016/465610","BE12355678",a);
		Person p = new Person("Sam","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebekx@abis.be","abis123","nl",c);
		assertEquals(p, personService.addPerson(p));
	}

	@Test
	//@Transactional
	public void addNewPersonWithoutCompany() throws PersonAlreadyExistsException, PersonNotFoundException {
		Person p = new Person("Sam","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebekx@abis.be","abis123","nl");
		assertEquals(p, personService.addPerson(p));
	}

	@Test
	@Transactional
	public void deleteAddedPerson() throws PersonCanNotBeDeletedException, PersonNotFoundException {
		personService.deletePerson(135);
		assertNull(personService.findPerson(135));
	}

	void deleteInexistingPerson(){
		//TODO
	}

	@Test
	void findByEmailAndPassword() throws PersonNotFoundException {
		String email = "john.doe@gmail.com";
		String password = "jd456";
		assertEquals(135, personService.findPerson(email, password).getPersonId());
	}

	@Test
	void findByWrongEmailAndPassword() throws PersonNotFoundException {
		String email = "johndoe@gmail.com";
		String password = "jd456";
		assertNull(personService.findPerson(email, password));
	}

	@Test
	@Transactional
	public void changePassWordOfJohn() throws PersonNotFoundException {
		String email = "john.doe@gmail.com";
		String password = "jd456";
		Person p = personService.findPerson(email, password);
		Person check = personService.changePassword(p,"blabla");
		assertEquals(p.getPassword(), check.getPassword());
	}

	/*
	@Test
	@Order(3)
	public void deletePersonWithSameCompany() throws PersonAlreadyExistsException, PersonNotFoundException, PersonCanNotBeDeletedException {
		Address a = new Address("Diestsevest","32","3000","Leuven","B");
		Company c = new Company("Abis","016/455610","BE12345678",a);
		Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl",c);

		Person p2 = new Person("test","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeecddkx@abis.be","abis123","nl",c);

		int p1no = personService.addPerson(p).getPersonId();
		personService.addPerson(p2);//.getPersonId();

		// personService.deletePerson(p1no);
	}

	 */





	
	

}
