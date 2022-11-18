package be.abis.exercise.repository;

import be.abis.exercise.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseJPARepository extends JpaRepository<Course, Integer> {

	public Course findById(int id);
	public Course findByShortTitle(String shortTitle);
		
}
