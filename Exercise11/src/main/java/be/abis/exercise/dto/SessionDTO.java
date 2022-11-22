package be.abis.exercise.dto;

import be.abis.exercise.converter.CancelBooleanConverter;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;

import javax.persistence.*;
import java.time.LocalDate;

public class SessionDTO {

    private int sessionId;
    private LocalDate startDate;
    private String instFirstName;
    private String instLastName;
    private String locCompName;
    private String locCompTown;
    private String kind;
    private boolean cancelled;
    private String longCourseTitle;

    public SessionDTO() {
    }

    public SessionDTO(int sessionId, LocalDate startDate, String instFirstName, String instLastName, String kind, boolean cancelled, String longCourseTitle) {
        this.sessionId = sessionId;
        this.startDate = startDate;
        this.instFirstName = instFirstName;
        this.instLastName = instLastName;
        this.kind = kind;
        this.cancelled = cancelled;
        this.longCourseTitle = longCourseTitle;
    }

    public SessionDTO(int sessionId, LocalDate startDate, String instFirstName, String instLastName, String locCompName, String locCompTown, String kind, boolean cancelled, String longCourseTitle) {
        this(sessionId, startDate, instFirstName, instLastName, kind, cancelled, longCourseTitle);
        this.locCompName = locCompName;
        this.locCompTown = locCompTown;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getInstFirstName() {
        return instFirstName;
    }

    public void setInstFirstName(String instFirstName) {
        this.instFirstName = instFirstName;
    }

    public String getInstLastName() {
        return instLastName;
    }

    public void setInstLastName(String instLastName) {
        this.instLastName = instLastName;
    }

    public String getLocCompName() {
        return locCompName;
    }

    public void setLocCompName(String locCompName) {
        this.locCompName = locCompName;
    }

    public String getLocCompTown() {
        return locCompTown;
    }

    public void setLocCompTown(String locCompTown) {
        this.locCompTown = locCompTown;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getLongCourseTitle() {
        return longCourseTitle;
    }

    public void setLongCourseTitle(String longCourseTitle) {
        this.longCourseTitle = longCourseTitle;
    }
}
