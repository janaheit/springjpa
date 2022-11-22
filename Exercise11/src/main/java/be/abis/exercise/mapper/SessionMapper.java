package be.abis.exercise.mapper;

import be.abis.exercise.dto.SessionDTO;
import be.abis.exercise.model.Session;

public class SessionMapper {

    public static SessionDTO toDTO(Session s){
        return new SessionDTO(s.getSessionId(), s.getStartDate(), s.getInstructor().getFirstName(),
                    s.getInstructor().getLastName(), s.getLocation().getName(), s.getLocation().getAddress().getTown(),
                    s.getKind(), s.isCancelled(), s.getCourse().getLongTitle());
    }
}
