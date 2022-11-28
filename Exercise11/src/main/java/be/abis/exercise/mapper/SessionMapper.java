package be.abis.exercise.mapper;

import be.abis.exercise.dto.SessionDTO;
import be.abis.exercise.model.CompanySession;
import be.abis.exercise.model.PublicSession;
import be.abis.exercise.model.Session;

public class SessionMapper {

    public static SessionDTO toDTO(Session s){
        String skind;
        if (s instanceof PublicSession) skind = "p";
        else if (s instanceof CompanySession) skind = "c";
        else skind = null;

        return new SessionDTO(s.getSessionId(), s.getStartDate(), s.getInstructor().getFirstName(),
                    s.getInstructor().getLastName(), s.getLocation().getName(), s.getLocation().getAddress().getTown(),
                    skind, s.isCancelled(), s.getCourse().getLongTitle());

        }
}
