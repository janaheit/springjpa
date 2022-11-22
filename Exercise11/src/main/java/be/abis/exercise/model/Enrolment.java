package be.abis.exercise.model;

import be.abis.exercise.converter.CancelBooleanConverter;
import be.abis.exercise.key.EnrolmentKey;

import javax.persistence.*;

@Entity
@IdClass(EnrolmentKey.class)
@Table(name = "enrolments")
@NamedNativeQuery(name = "Enrolment.findEnrolmentsForPersonByID",
        query = "select pfname, plname, p_comp.coname as pacompname,sdate, loc_comp.coname as loccompname " +
                "from enrolments join persons on e_pno = pno " +
                "join companies p_comp on pa_cono = p_comp.cono " +
                "join sessions on e_sno = sno " +
                "join courses on s_cid = cid " +
                "join companies loc_comp on sloc_cono = loc_comp.cono " +
                "where pno = :personID")
public class Enrolment {

    @Id
    @JoinColumn(name = "e_sno")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private Session session;

    @JoinColumn(name = "e_pno")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private Person enrollee;
    @Id
    @Column(name = "eno")
    private int enrolmentInSession;
    @Column(name = "epay")
    private double pricePerDayPayed;
    @JoinColumn(name = "e_cono")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private Company enrolmentCompany;
    @Column(name = "ecancel")
    @Convert(converter = CancelBooleanConverter.class)
    private boolean cancelled;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getEnrolmentInSession() {
        return enrolmentInSession;
    }

    public void setEnrolmentInSession(int enrolmentInSession) {
        this.enrolmentInSession = enrolmentInSession;
    }

    public Person getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Person enrollee) {
        this.enrollee = enrollee;
    }

    public double getPricePerDayPayed() {
        return pricePerDayPayed;
    }

    public void setPricePerDayPayed(double pricePerDayPayed) {
        this.pricePerDayPayed = pricePerDayPayed;
    }

    public Company getEnrolmentCompany() {
        return enrolmentCompany;
    }

    public void setEnrolmentCompany(Company enrolmentCompany) {
        this.enrolmentCompany = enrolmentCompany;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
