package be.abis.exercise.repository;

import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CompanyJPARepository extends JpaRepository<Company, Integer> {

	@Query(value = "select c from Company c join fetch c.employees where c.companyId = :id")
	Company findById(int id);
	Company findByName(String name);
	List<Company> findByNameStartingWith(String startingWith);
	@Query("select c from Company c where c.name like :name% and c.address.town = :town")
	Company findByNameAndTown(String name, String town);
}
