package be.abis.exercise.dto;

import java.time.LocalDate;

public class EnrolmentDTO {

    private String firstName;
    private String lastName;
    private String persCompName;
    private LocalDate startDate;
    private String locCompName;

    public EnrolmentDTO() {
    }

    public EnrolmentDTO(String firstName, String lastName, String persCompName, LocalDate startDate, String locCompName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.persCompName = persCompName;
        this.startDate = startDate;
        this.locCompName = locCompName;
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

    public String getPersCompName() {
        return persCompName;
    }

    public void setPersCompName(String persCompName) {
        this.persCompName = persCompName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getLocCompName() {
        return locCompName;
    }

    public void setLocCompName(String locCompName) {
        this.locCompName = locCompName;
    }
}
