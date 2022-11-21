package be.abis.exercise;

import be.abis.exercise.repository.SessionJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SessionRepositoryTest {
    @Autowired
    SessionJPARepository sessionRepository;

    @Test
    void getAll(){
        System.out.println(sessionRepository.findAll());
    }

    @Test
    void findSessionsForCourseTitle(){
        String courseTitle = "IMSDB";
        System.out.println(sessionRepository.findSessionsForCourse(courseTitle));
    }
}
