package be.abis.exercise.mapper;

import be.abis.exercise.dto.EnrolmentDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EnrolmentMapper {
    public static EnrolmentDTO toDTO(Object[] enrolments){
            return new EnrolmentDTO(enrolments[0].toString().trim(), enrolments[1].toString().trim(),
                    enrolments[2].toString().trim(),
                    LocalDate.parse(enrolments[3].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    enrolments[4].toString().trim());
    }
}
