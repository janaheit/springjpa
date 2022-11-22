package be.abis.exercise.repository;

import be.abis.exercise.key.EnrolmentKey;
import be.abis.exercise.model.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EnrolmentJPARepository extends JpaRepository<Enrolment, EnrolmentKey> {

    List<Object[]> findEnrolmentsForPersonByID(@Param("personID") int personID);
}
