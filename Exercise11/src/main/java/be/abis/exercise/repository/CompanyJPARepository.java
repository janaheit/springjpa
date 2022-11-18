package be.abis.exercise.repository;

import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CompanyJPARepository extends JpaRepository<Company, Integer> {

	Company findById(int id);
	Company findByName(String name);
	@Query("select c from Company c where c.name = :name and c.address.town = :town")
	Company findByNameAndTown(String name, String town);
}
