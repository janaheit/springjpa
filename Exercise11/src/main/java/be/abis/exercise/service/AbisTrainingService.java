package be.abis.exercise.service;

import be.abis.exercise.dto.EnrolmentDTO;
import be.abis.exercise.dto.SessionDTO;
import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.mapper.EnrolmentMapper;
import be.abis.exercise.mapper.SessionMapper;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.EnrolmentJPARepository;
import be.abis.exercise.repository.SessionJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbisTrainingService implements TrainingService {

    @Value("Welcome to the Abis Training Service")
    private String welcomeMessage;

    @Autowired
    private PersonService personService;

    @Autowired
    private CourseService courseService;
    @Autowired
    SessionJPARepository sessionRepository;
    @Autowired
    EnrolmentJPARepository enrolmentRepository;

    @Override
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public CourseService getCourseService() {
        return courseService;
    }


    @Override
    public void enrolForSession(Person person, int sessionId) throws EnrolException {

    }

    @Override
    public List<SessionDTO> findSessionsForCourse(String courseTitle) {
        List<SessionDTO> sessions = sessionRepository.findSessionsForCourse(courseTitle).stream()
                .map(s -> SessionMapper.toDTO(s))
                .collect(Collectors.toList());

        return sessions;
    }



    @Override
    public List<EnrolmentDTO> findEnrolments(int personId) {
        List<Object[]> enrolments = enrolmentRepository.findEnrolmentsForPersonByID(personId);

        return enrolments.stream()
                .map(e -> EnrolmentMapper.toDTO(e))
                .collect(Collectors.toList());
    }


}
