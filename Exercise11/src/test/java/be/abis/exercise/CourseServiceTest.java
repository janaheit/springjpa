package be.abis.exercise;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CourseServiceTest {
	
	@Autowired
    CourseService courseService;
	
	@Test
	public void course7900isWorkshopSQL() {
		Course c = courseService.findCourse(7900);
		assertEquals("SQLWS",c.getShortTitle().toUpperCase().trim());
	}

	@Test
	void addCourseWorks(){
		Course c = new Course();
		c.setShortTitle("testCourse");
		c.setNumberOfDays(9);
		c.setPricePerDay(36.0);

		assertEquals(c, courseService.addCourse(c));
	}

	@Test
	@Transactional
	void updateCourse(){
		Course c = new Course();
		c.setCourseId(8056);
		c.setShortTitle("newtitle");
		c.setNumberOfDays(9);
		c.setPricePerDay(36.0);

		assertEquals(c, courseService.updateCourse(c));
	}

	@Test
	void deleteCourse(){
		courseService.deleteCourse(8057);
	}

	
	

}
