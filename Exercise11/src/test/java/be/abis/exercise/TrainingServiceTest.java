package be.abis.exercise;


import be.abis.exercise.dto.EnrolmentDTO;
import be.abis.exercise.dto.SessionDTO;
import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.exception.SessionNotFoundException;
import be.abis.exercise.model.*;
import be.abis.exercise.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TrainingServiceTest {

    @Autowired
    TrainingService trainingService;

    @Test
    void findEnrolmentsById(){
        List<EnrolmentDTO> enrolmentList = trainingService.findEnrolments(25);
        assertEquals(5, enrolmentList.size());
    }

    @Test
    void findSessionsForCourse(){
        List<SessionDTO> sessions = trainingService.findSessionsForCourse("DB2BAS");
        System.out.println(sessions);
    }

    @Test
    void cancelSession(){
        trainingService.cancelSession(1);

        assertTrue(trainingService.findSession(1).isCancelled());
    }

    @Transactional
    @Test
    void enrollForSessionWithNewPerson() throws EnrolException, SessionNotFoundException {
        Address a = new Address("Diestsevest","32","3000","Leuven","B");
        Company c = new Company("Abis","016/455610","BE12345678",a);
        Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl",c);
        long before = trainingService.countEnrolments();
        trainingService.enrolForSession(p, 1);
        long after = trainingService.countEnrolments();

        assertEquals(before+1, after);
    }

    @Transactional
    @Test
    void enrollForSessionWithExistingPersonWithoutCompany() throws EnrolException, SessionNotFoundException {
        Person p = new Person("xx","xx", LocalDate.of(1978,04,10),"john.doe@gmail.com","abis123","nl");

        long before = trainingService.countEnrolments();
        trainingService.enrolForSession(p, 1);
        long after = trainingService.countEnrolments();

        assertEquals(before+1, after);
    }

    @Transactional
    @Test
    void enrollForSessionAsFirstEnrolment() throws EnrolException, SessionNotFoundException {
        Address a = new Address("Diestsevest","32","3000","Leuven","B");
        Company c = new Company("Abis","016/455610","BE12345678",a);
        Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl",c);

        long before = trainingService.countEnrolments();
        trainingService.enrolForSession(p, 5);
        long after = trainingService.countEnrolments();

        assertEquals(before+1, after);
    }

    @Transactional
    @Test
    void enrollForNonExistingSession() {
        Address a = new Address("Diestsevest","32","3000","Leuven","B");
        Company c = new Company("Abis","016/455610","BE12345678",a);
        Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl",c);

        assertThrows(EnrolException.class, () -> trainingService.enrolForSession(p, 5000));
    }
}
