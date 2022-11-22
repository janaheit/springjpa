package be.abis.exercise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class PersonDTO {
    private int personID;
    private String firstName;
    private String lastName;
    private String email;
    @JsonFormat(pattern="dd/MM/yyyy")

    private LocalDate birthdate;
    private String companyName;
    private String companyTown;

    public PersonDTO() {
    }

    public int getPersonID() {
        return personID;
    }

    public PersonDTO(int personID, String firstName, String lastName, String email, LocalDate birthdate) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
    }

    public PersonDTO(int personID, String firstName, String lastName, String email, LocalDate birthdate, String companyName, String companyTown) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
        this.companyName = companyName;
        this.companyTown = companyTown;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTown() {
        return companyTown;
    }

    public void setCompanyTown(String companyTown) {
        this.companyTown = companyTown;
    }
}
