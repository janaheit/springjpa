package be.abis.exercise;


import be.abis.exercise.dto.EnrolmentDTO;
import be.abis.exercise.model.Enrolment;
import be.abis.exercise.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class TrainingServiceTest {

    @Autowired
    TrainingService trainingService;

    @Test
    void findEnrolmentsById(){
        List<EnrolmentDTO> enrolmentList = trainingService.findEnrolments(25);
        assertEquals(5, enrolmentList.size());
    }
}
