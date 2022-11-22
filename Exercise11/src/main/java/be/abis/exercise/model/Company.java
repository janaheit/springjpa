package be.abis.exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Companies")
public class Company{

	@SequenceGenerator(name="mySeqGen", sequenceName = "companies_cono_seq", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
	@Column(name="cono")
	private int companyId;
	@Column(name = "coname")
	private String name;
	@Column(name = "cotel")
	private String telephoneNumber;
	@Column(name = "covat")
	private String vatNr;
	@Embedded
	private Address address;
	//@JsonIgnore
	@OneToMany(targetEntity = Person.class, mappedBy = "company", fetch = FetchType.LAZY)
	private List<Person> employees = new ArrayList<>();

	public Company(){}
	public Company(String name, String telephoneNumber, String vatNr, Address address) {
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		this.vatNr = vatNr;
		this.address = address;
	}

	public List<Person> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Person> employees) {
		this.employees = employees;
	}

	public void addEmployee(Person p){
		this.employees.add(p);
		p.setCompany(this);
	}

	public void removeEmployee(Person p){
		this.employees.remove(p);
		p.setCompany(null);
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getVatNr() {
		return vatNr;
	}
	public void setVatNr(String vatNr) {
		this.vatNr = vatNr;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String toString(){
		return name + " in " + address.getTown();
	}
}
