package be.abis.exercise.service;

import be.abis.exercise.dto.EnrolmentDTO;
import be.abis.exercise.dto.SessionDTO;
import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.exception.SessionNotFoundException;
import be.abis.exercise.model.Person;
import be.abis.exercise.model.Session;

import java.util.List;

public interface TrainingService {

    public String getWelcomeMessage();
    public PersonService getPersonService();
    public CourseService getCourseService();


    public void enrolForSession(Person person, int sessionId) throws EnrolException, SessionNotFoundException;

    List<SessionDTO> findSessionsForCourse(String courseTitle);

    public List<EnrolmentDTO> findEnrolments(int personId);
    void cancelSession(int id);
    Session findSession(int id);
    long countEnrolments();


}
