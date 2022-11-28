package be.abis.exercise.service;

import be.abis.exercise.mapper.UserMapper;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AbisUserDetailsService implements UserDetailsService {
    @Autowired
    PersonJPARepository personRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person user = personRepository.findByEmailAddress(email);
        if (user==null){
            throw new UsernameNotFoundException("this user was not found");
        }
        System.out.println("user = " + user.getEmailAddress() + " " + user.getPersonId());
        return new AbisUserPrincipal(UserMapper.toDTO(user)); // DTO
    }
}
