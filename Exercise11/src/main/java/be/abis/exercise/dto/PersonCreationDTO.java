package be.abis.exercise.dto;

import be.abis.exercise.model.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Embedded;
import java.time.LocalDate;

public class PersonCreationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String language;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate birthdate;

    // company attributes
    private String compName;
    private String comptelNumber;
    private String compvatNr;

    private String compStreet;
    private String compStreetNr;
    private String compZipcode;
    private String compTown;
    private String compCountryCode;


    public PersonCreationDTO() {
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

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getComptelNumber() {
        return comptelNumber;
    }

    public void setComptelNumber(String comptelNumber) {
        this.comptelNumber = comptelNumber;
    }

    public String getCompvatNr() {
        return compvatNr;
    }

    public void setCompvatNr(String compvatNr) {
        this.compvatNr = compvatNr;
    }

    public String getCompStreet() {
        return compStreet;
    }

    public void setCompStreet(String compStreet) {
        this.compStreet = compStreet;
    }

    public String getCompStreetNr() {
        return compStreetNr;
    }

    public void setCompStreetNr(String compStreetNr) {
        this.compStreetNr = compStreetNr;
    }

    public String getCompZipcode() {
        return compZipcode;
    }

    public void setCompZipcode(String compZipcode) {
        this.compZipcode = compZipcode;
    }

    public String getCompTown() {
        return compTown;
    }

    public void setCompTown(String compTown) {
        this.compTown = compTown;
    }

    public String getCompCountryCode() {
        return compCountryCode;
    }

    public void setCompCountryCode(String compCountryCode) {
        this.compCountryCode = compCountryCode;
    }
}
