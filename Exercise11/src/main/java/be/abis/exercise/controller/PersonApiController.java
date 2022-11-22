package be.abis.exercise.controller;

import be.abis.exercise.dto.PersonCreationDTO;
import be.abis.exercise.dto.PersonDTO;
import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.mapper.PersonMapper;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("persons")
public class PersonApiController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonService ps;

    @GetMapping("")
    public List<PersonDTO> getAllPersons(){
        return ps.getAllPersons().stream()
                .map(person -> PersonMapper.toDTO(person))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public PersonDTO findPerson(@PathVariable("id") int id) throws PersonNotFoundException {
        // TODO exception handling when person is null
        Person p = ps.findPerson(id);
        return PersonMapper.toDTO(p);
    }

    @PostMapping("/login")
    public PersonDTO findPersonByMailAndPwd(@RequestBody Login login) throws PersonNotFoundException {
        Person p = ps.findPerson(login.getEmail(), login.getPassword());
        return PersonMapper.toDTO(p);
    }

    @GetMapping(path="/compquery")
    public List<PersonDTO> findPersonsByCompanyName(@RequestParam("compname") String compName) {
        List<PersonDTO> personDTOList = ps.findPersonsByCompanyName(compName).stream()
                .map(person -> PersonMapper.toDTO(person))
                .collect(Collectors.toList());

        return personDTOList;
    }

    @PostMapping(path="")
    public PersonDTO addPerson(@RequestBody PersonCreationDTO pcDTO) throws PersonAlreadyExistsException, PersonNotFoundException {

        Person p = PersonMapper.toPerson(pcDTO);
        Person personDB = ps.addPerson(p);
        return PersonMapper.toDTO(personDB);
    }

    @DeleteMapping("{id}")
    public void deletePerson(@PathVariable("id") int id) throws PersonCanNotBeDeletedException {
        ps.deletePerson(id);
    }

    @PutMapping("{id}")
    public void changePassword(@PathVariable("id") int id, @RequestBody Person person) throws PersonNotFoundException {
        System.out.println("changing password to newpswd= " + person.getPassword());
        ps.changePassword(person, person.getPassword());
    }





}
