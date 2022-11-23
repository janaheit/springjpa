package be.abis.exercise;

import be.abis.exercise.repository.EnrolmentJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EnrolmentRepositoryTest {
    @Autowired
    EnrolmentJPARepository enrolmentRepository;

    @Test
    void findAll(){
        System.out.println(enrolmentRepository.findAll());
    }

    @Test
    void findEnrolmentsForPerson(){
        List<Object[]> enrolments = enrolmentRepository.findEnrolmentsForPersonByID(25);
        for (Object[] l :enrolments){
            for (Object o: l){
                System.out.println(o);
            }
        }
    }

    @Test
    void countEnrolmentsBySession(){
        System.out.println(enrolmentRepository.countEnrolmentsForSession(1));
    }

    @Test
    void countEnrolmentsForSessionThatHas0(){
        System.out.println(enrolmentRepository.countEnrolmentsForSession(5));
    }
}
