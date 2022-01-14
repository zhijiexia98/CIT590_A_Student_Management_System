package roles;

import courses.Course;
import files.FileInfoReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student extends User {
	
	/**
	 * courses one student take
	 */
	private ArrayList<Course> courses = new ArrayList<Course>();
	/**
	 * grades one student get
	 */
	private ArrayList<String[]> coursegrade = new ArrayList<String[]>();

	/**
	 * constructor for existing students, add their course grade to the grade list
	 * @param given_array
	 */
	public Student(String[] given_array) {
		super(given_array);
		String [] courses = given_array[4].split(",");
		for (String course : courses) {
			String[]courseAndGrade = course.split(":");
			courseAndGrade[0] = courseAndGrade[0].trim();
			courseAndGrade[1] = courseAndGrade[1].trim();
			this.coursegrade.add(courseAndGrade);
		}
	}
	
	/**
	 * constructor for adding new student
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 */
	public Student(String id, String name, String username, String password) {
		super(id, name, username, password);
	}
	
	/**
	 * get coursegrade of one student
	 * @return coursegrade
	 */
	public ArrayList<String[]> getCoursegrade(){
		return coursegrade;
	}
	
	/**
	 * get courses one student taking
	 * @return courses one student taking
	 */
	public ArrayList<Course> getCourse(){
		return courses;
	}
	
	/**
	 * add new course to one students's course schedule
	 * @param course
	 * @return true if it is successfully added, false if the course already exist
	 */
	public boolean addCourse(Course course) {
		if(courses.contains(course)) {
			return false;
		}
		else {		
			courses.add(course);
			return true;
		}
	}
	
	/**
	 * drop new course to one students's course schedule
	 * @param course
	 * @return true if it is successfully drop, false if the course does not already exist
	 */
	public boolean dropCourse(Course course) {
		if(!courses.contains(course)) {
			return false;
		}
		else {		
			courses.remove(course);
			return true;
		}
		}


}
