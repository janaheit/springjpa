package be.abis.exercise.model;

import be.abis.exercise.converter.CancelBooleanConverter;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "skind", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Sessions")
public class Session {

    @SequenceGenerator(name="mySeqGen", sequenceName = "sessions_sno_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    @Column(name="sno")

    private int sessionId;
    @Column(name = "sdate")
    private LocalDate startDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name="sins_pno")
    private Person instructor;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name="sloc_cono")
    private Company location;
    //@Column(name="skind")
    //private String kind;
    @Column(name="sincomes")
    private double income;
    @Column(name="scancel")
    @Convert(converter = CancelBooleanConverter.class)
    private boolean cancelled;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name="s_cid")
    private Course course;

    public Session(){}

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

    public Company getLocation() {
        return location;
    }

    public void setLocation(Company location) {
        this.location = location;
    }

    public Person getInstructor() {
        return instructor;
    }

    public void setInstructor(Person instructor) {
        this.instructor = instructor;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    /*public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

     */

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
