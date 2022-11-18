package be.abis.exercise.service;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisCourseService implements CourseService {

    @Autowired
    CourseJPARepository courseRepository;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourse(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course findCourse(String shortTitle) {
        return courseRepository.findByShortTitle(shortTitle);
    }

    @Override
    public Course addCourse(Course c) {
        Course course = findCourse(c.getCourseId());
        if (course == null){
            return courseRepository.save(c);
        } else {
            // already exists
            System.out.println("course already exist, cannot add");
            return null;
        }
    }

    @Override
    public Course updateCourse(Course c) {
        Course course = findCourse(c.getCourseId());
        if (course == null){
            // don't update
            System.out.println("course does not exist, cannot update");
            return null;
        } else {
            return courseRepository.save(c);
        }
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }




}
