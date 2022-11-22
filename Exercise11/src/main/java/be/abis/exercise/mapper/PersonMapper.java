package be.abis.exercise.mapper;

import be.abis.exercise.dto.PersonCreationDTO;
import be.abis.exercise.dto.PersonDTO;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public static PersonDTO toDTO(Person p){

        if (p.getCompany() != null) return new PersonDTO(p.getPersonId(), p.getFirstName().trim(), p.getLastName().trim(),
                p.getEmailAddress().trim(), p.getBirthDate(), p.getCompany().getName().trim(),
                p.getCompany().getAddress().getTown().trim());

        return new PersonDTO(p.getPersonId(), p.getFirstName().trim(), p.getLastName().trim(),
                p.getEmailAddress().trim(), p.getBirthDate());
    }

    public static Person toPerson(PersonCreationDTO pcDTO){

        Person p;

        if (pcDTO.getCompName() != null && pcDTO.getCompTown() != null){
            Address a = new Address(pcDTO.getCompStreet(), pcDTO.getCompStreetNr(), pcDTO.getCompZipcode(),
                    pcDTO.getCompTown(), pcDTO.getCompCountryCode());
            Company c = new Company(pcDTO.getCompName(), pcDTO.getComptelNumber(), pcDTO.getCompvatNr(), a);

            p = new Person(pcDTO.getFirstName(), pcDTO.getLastName(), pcDTO.getBirthdate(),
                    pcDTO.getEmail(), pcDTO.getPassword(), pcDTO.getLanguage(), c);
        } else {
            p = new Person(pcDTO.getFirstName(), pcDTO.getLastName(), pcDTO.getBirthdate(),
                    pcDTO.getEmail(), pcDTO.getPassword(), pcDTO.getLanguage());
        }

        return p;
    }
}
