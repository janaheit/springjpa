package be.abis.exercise.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Persons")
public class Person {
	
	@SequenceGenerator(name = "mySeqGen", sequenceName = "persons_pno_seq", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
	@Column(name = "pno")
	private int personId;
	@Column(name = "pfname")
	private String firstName;
	@Column(name="plname")
	private String lastName;
	@Column(name = "pbirthdate")
	private LocalDate birthDate;
	@Column(name = "pemail", unique = true)
	private String emailAddress;
	@Column(name = "ppass")
	private String password;
	@Column(name = "plang")
	private String language;
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
	@JoinColumn(name="pa_cono")
	private Company company;
	@ElementCollection
	@CollectionTable(name = "Hobbies", joinColumns = @JoinColumn(name = "h_pno"))
	@OrderColumn(name = "h_hno")
	@Column(name = "h_hobby")
	private List<String> hobbies = new ArrayList<>();

	public Person(){}
	public Person(String firstName, String lastName, LocalDate birthDate, String emailAddress, String password, String language) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.emailAddress = emailAddress;
		this.password = password;
		this.language = language;
	}
	public Person(String firstName, String lastName, LocalDate birthDate, String emailAddress, String password, String language, Company company) {
		this(firstName, lastName, birthDate, emailAddress, password, language);
		this.company = company;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void addHobby(String hobby){
		hobbies.add(hobby);
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "Person with id " + personId + ", " + firstName + " "+ lastName + ", works for " +company.getName() + " in " + company.getAddress().getTown();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return personId == person.personId && firstName.equals(person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(birthDate, person.birthDate) && Objects.equals(emailAddress, person.emailAddress) && Objects.equals(password, person.password) && Objects.equals(language, person.language) && Objects.equals(company, person.company);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId, firstName, lastName, birthDate, emailAddress, password, language, company);
	}
}
