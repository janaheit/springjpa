package be.abis.exercise;

import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.repository.CompanyJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CompanyRepositoryTest {

    @Autowired
    CompanyJPARepository companyRepository;

    @Test
    void findCompanyByIdWorks(){
        assertEquals("189", companyRepository.findById(1).getAddress().getNr().trim());
    }

    @Test
    void findCompanyByName(){
        assertEquals(4, companyRepository.findByName("ESCON").getCompanyId());
    }

    @Test
    void findCompanyByNameAndTown(){
        assertEquals(3, companyRepository.findByNameAndTown("ABIS N.V.", "LEUVEN").getCompanyId());
    }

    @Test
    void insertSTH(){
        Address a = new Address();
        a.setStreet("teststreet");
        a.setNr("5");
        a.setTown("testtown");
        a.setZipcode("testzip");
        a.setCountryCode("BE");
        Company c = new Company();
        c.setName("company test");
        c.setAddress(a);
        companyRepository.save(c);
    }

    @Test
    void deleteSTH(){

        companyRepository.deleteById(106);
    }
}
