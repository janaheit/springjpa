package be.abis.exercise.repository;

import be.abis.exercise.key.EnrolmentKey;
import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EnrolmentJPARepository extends JpaRepository<Enrolment, EnrolmentKey> {

    List<Object[]> findEnrolmentsForPersonByID(@Param("personID") int personID);
    @Query(value = "select count(*) from enrolments where e_sno=:sessionId", nativeQuery = true)
    int countEnrolmentsForSession(int sessionId);

    @Modifying
    @Query(value = "insert into enrolments values(:sessionId, :enrolmentInSession, :enrolleeId, " +
            ":pricePerDay, :enrolleeCompId, :cancelled)", nativeQuery = true)
    void insertEnrolment(int sessionId, int enrolleeId, int enrolleeCompId, int enrolmentInSession,
                         double pricePerDay, String cancelled);

    @Modifying
    @Query(value = "insert into enrolments(e_sno, eno, e_pno, epay, ecancel)" +
            " values(:sessionId, :enrolmentInSession, :enrolleeId, " +
            ":pricePerDay, :cancelled)", nativeQuery = true)
    void insertEnrolment(int sessionId, int enrolleeId, int enrolmentInSession,
                         double pricePerDay, String cancelled);

}
