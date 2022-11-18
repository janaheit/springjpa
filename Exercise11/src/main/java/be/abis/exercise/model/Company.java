package be.abis.exercise.model;

import javax.persistence.*;

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

	public Company(){}
	public Company(String name, String telephoneNumber, String vatNr, Address address) {
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		this.vatNr = vatNr;
		this.address = address;
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
