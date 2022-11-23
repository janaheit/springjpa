package be.abis.exercise.service;

import be.abis.exercise.dto.EnrolmentDTO;
import be.abis.exercise.dto.SessionDTO;
import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.exception.SessionNotFoundException;
import be.abis.exercise.mapper.EnrolmentMapper;
import be.abis.exercise.mapper.SessionMapper;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.Person;
import be.abis.exercise.model.Session;
import be.abis.exercise.repository.EnrolmentJPARepository;
import be.abis.exercise.repository.SessionJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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


    //@Transactional
    @Override
    public void enrolForSession(Person person, int sessionId) throws EnrolException, SessionNotFoundException {

        System.out.println(person.getPersonId());
        Session s = sessionRepository.findBySessionId(sessionId);
        if (s == null)
            throw new EnrolException("Enrolment not executed bc session with id " + sessionId + " does not exist.");

        int enrolmentNumber = enrolmentRepository.countEnrolmentsForSession(sessionId);
        enrolmentNumber++;
        String cancelled = null;

        try {
            //System.out.println(person.getPersonId());
            person = personService.addPerson(person);
            //System.out.println(person.getPersonId());
        } catch (PersonAlreadyExistsException e) {
            try {
                person = personService.findPerson(person.getEmailAddress());
            } catch (PersonNotFoundException ex) {
                //nothing bc i already tested that this person exists
            }
        }
        System.out.println(person.getPersonId());
        if (person.getCompany() != null) {
            enrolmentRepository.insertEnrolment(s.getSessionId(), person.getPersonId(), person.getCompany().getCompanyId(),
                    enrolmentNumber, s.getCourse().getPricePerDay(), cancelled);
        } else {
            enrolmentRepository.insertEnrolment(s.getSessionId(), person.getPersonId(),
                    enrolmentNumber, s.getCourse().getPricePerDay(), cancelled);
        }
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

    @Transactional
    @Override
    public void cancelSession(int id) {
        sessionRepository.cancelSessionById(id);
    }

    @Override
    public Session findSession(int id) {
        return sessionRepository.findBySessionId(id);
    }

    public long countEnrolments(){
        return enrolmentRepository.count();
    }
}

