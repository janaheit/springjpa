package be.abis.exercise.repository;

import be.abis.exercise.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionJPARepository extends JpaRepository<Session, Integer> {

    //@Query(value = "select s from Session s where s.course.shortTitle = :courseTitle")
    @Query(value = "select * from sessions join courses on s_cid = cid where cstitle = :courseTitle and scancel is null", nativeQuery = true )
    List<Session> findSessionsForCourse(String courseTitle);
}
