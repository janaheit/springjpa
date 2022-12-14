package be.abis.exercise.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Courses")
public class Course {

	@SequenceGenerator(name="mySeqGen", sequenceName = "courses_cid_seq", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    @Column(name="cid")
	private int courseId;
	@Column(name="cstitle")
	private String shortTitle;
	@Column(name="cltitle")
	private String longTitle;
	@Column(name="cdur")
	private int numberOfDays;
	@Column(name="caprice")
	private double pricePerDay;
	
	public Course(){}
	
	public Course(int courseId, String shortTitle, String longTitle, int numberOfDays, double pricePerDay) {
		super();
		this.courseId = courseId;
		this.shortTitle = shortTitle;
		this.longTitle = longTitle;
		this.numberOfDays = numberOfDays;
		this.pricePerDay = pricePerDay;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public String getLongTitle() {
		return longTitle;
	}
	public void setLongTitle(String longTitle) {
		this.longTitle = longTitle;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public double getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Course course = (Course) o;
		return courseId == course.courseId && numberOfDays == course.numberOfDays && Double.compare(course.pricePerDay, pricePerDay) == 0 && shortTitle.equals(course.shortTitle) && Objects.equals(longTitle, course.longTitle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId, shortTitle, longTitle, numberOfDays, pricePerDay);
	}
}