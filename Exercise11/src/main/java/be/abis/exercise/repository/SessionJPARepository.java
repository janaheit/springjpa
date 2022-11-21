package be.abis.exercise.repository;

import be.abis.exercise.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionJPARepository extends JpaRepository<Session, Integer> {

    @Query(value = "select s from Session s join s.course c where c.shortTitle = :courseTitle")
    List<Session> findSessionsForCourse(String courseTitle);
}
