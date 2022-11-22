package be.abis.exercise.key;

import be.abis.exercise.model.Session;

import java.io.Serializable;
import java.util.Objects;

public class EnrolmentKey implements Serializable {

    private Session session;
    private int enrolmentInSession;

    public EnrolmentKey() {
    }

    public EnrolmentKey(Session session, int enrolmentInSession) {
        this.session = session;
        this.enrolmentInSession = enrolmentInSession;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrolmentKey that = (EnrolmentKey) o;
        return enrolmentInSession == that.enrolmentInSession && session.equals(that.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, enrolmentInSession);
    }
}
