package be.abis.exercise.mapper;

import be.abis.exercise.dto.User;
import be.abis.exercise.model.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

public class UserMapper {

    public static User toDTO(Person person){
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();


        User u = new User(person.getPersonId(), person.getEmailAddress(), enc.encode(person.getPassword()));
        return u;
    }
}
