package files;

import java.util.ArrayList;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import files.FileInfoReader;
import courses.Course;
import roles.Admin;
import roles.Professor;
import roles.Student;
import roles.User;
import courses.Course;

public class test {

	FileInfoReader fileinforeader;
	private ArrayList<Course> courses = new ArrayList<Course>();

		
	@BeforeEach
	void setup() {
		fileinforeader = new FileInfoReader();
		fileinforeader.courseSetUp("courseInfo.txt");
		fileinforeader.userSetUp("adminInfo.txt", "3");
		fileinforeader.userSetUp("profInfo.txt", "2");
		fileinforeader.userSetUp("studentInfo.txt", "1");
	}
	
	@Test
	void testgetUser() {
		assertEquals("Stephanie Weirich", fileinforeader.getUser("professor", "003").getName());
		assertEquals("StudentName1", fileinforeader.getUser("student", "001").getName());
		assertNull(fileinforeader.getUser("admin", "999"));
		assertNull(fileinforeader.getUser("student", "003"));
	}
	
	@Test
	void testgetSpecificCourse() {
        Course courseOne = fileinforeader.getSpecificCourse("CIT590");
        assertEquals("Brandon L Krakowsky", courseOne.getLecturer());
        assertEquals("Programming Languages and Techniques", courseOne.getCourseName());
        
        assertNull(fileinforeader.getSpecificCourse("MSE525"));
        
        Course courseTwo = fileinforeader.getSpecificCourse("CIT596");
        assertEquals("Arvind Bhusnurmath", courseTwo.getLecturer());
        assertEquals("Algorithms and Computation", courseTwo.getCourseName());
        
	}
	
	@Test
	void testgetSpecificProfCourse() {
		ArrayList<Course> course = fileinforeader.getSpecificProfCourse("Brandon L Krakowsky");
		assertEquals("Programming Languages and Techniques", course.get(0).getCourseName());
		assertEquals("CIT590", course.get(0).getCourseID());
        assertEquals(1, course.size());
	}
	
	@Test
	void testlogin() {
		assertEquals(0,fileinforeader.login("Greenberg","password590","2"));
		assertEquals(31,fileinforeader.login("Gallier","password590","2"));
		assertEquals(-1,fileinforeader.login("Greenberg","forget","2"));
		assertEquals(-1,fileinforeader.login("Ruilin","password590","2"));
		
	}
	@Test
	void testvalidUserId() {
		assertTrue(fileinforeader.validUserId("001","professor"));
		assertFalse(fileinforeader.validUserId("999999","professor"));
		
		assertFalse(fileinforeader.validUserId("004","admin"));
		Admin admin = new Admin("004","admin","admin04","password590");
		fileinforeader.addAdmin(admin);
		assertTrue(fileinforeader.validUserId("004","admin"));
		
			
	}
	
	@Test
	void testvalidUsername() {
		assertTrue(fileinforeader.validUsername("Greenberg","professor"));
		assertFalse(fileinforeader.validUsername("Clayton Greenberg","professor"));
	}
	
	@Test
	void testcourseConflict() {
		Course courseOne = new Course("CIT597","Test course","Ruilin Yu","MW","12:30","14:00","110" );
		Course courseTwo = new Course("CIT598","Test course","Ruilin Yu","MW","12:00","14:00","110" );
		Course courseThree = new Course("CIT599","Test course","Ruilin Yu","MW","16:00","18:00","110" );
		assertFalse(fileinforeader.courseConflict(courseOne,courseThree));
		assertTrue(fileinforeader.courseConflict(courseOne,courseTwo));
    }
	
	@Test
	void testcheckCourseConflict() {
		Course courseOne = new Course("CIT597","Test course","Ruilin Yu","MW","12:30","14:00","110" );
		Course courseTwo = new Course("CIT598","Test course","Ruilin Yu","MW","08:00","11:00","110" );
		Course courseThree = new Course("CIT599","Test course","Ruilin Yu","MW","16:00","18:00","110" );
		Course courseFour = new Course("CIT600","Test course","Ruilin Yu","MW","16:00","18:00","110" );
		
		ArrayList<Course> courses = new ArrayList<Course>();
		courses.add(courseOne);
		courses.add(courseThree);
		assertNull(fileinforeader.checkCourseConflict(courses,courseTwo));
		
		ArrayList<Course> coursesNew = new ArrayList<Course>();
		coursesNew.add(courseFour);
		coursesNew.add(courseThree);
		assertEquals("CIT600",fileinforeader.checkCourseConflict(coursesNew, courseFour).getCourseID());	
	}
	
	
	@Test
	void testdeleteUser() {
		assertTrue(fileinforeader.validUserId("001","professor"));
		assertTrue(fileinforeader.deleteUser("001","professor"));
		assertFalse(fileinforeader.validUserId("001","professor"));
	}
	
	@Test
	void testdeleteCourse() {
		Course courseOne = new Course("CIT597","Test course","Ruilin Yu","MW","12:30","14:00","110" );
		Course courseTwo = new Course("CIT598","Test course","Ruilin Yu","MW","12:00","14:00","110" );
		Course courseThree = new Course("CIT599","Test course","Ruilin Yu","MW","16:00","18:00","110" );
		fileinforeader.addCourse(courseThree);
		fileinforeader.addCourse(courseTwo);
		fileinforeader.addCourse(courseOne);
		ArrayList<Course> course = fileinforeader.getSpecificProfCourse("Ruilin Yu");
        assertEquals(3, course.size());
        fileinforeader.deleteCourse("CIT597");
        course = fileinforeader.getSpecificProfCourse("Ruilin Yu");
        assertEquals(2, course.size());	
	}
	
	@Test
	void testAdd() {
		assertFalse(fileinforeader.validUserId("004","admin"));
		Admin admin = new Admin("004","admin","admin04","password590");
		fileinforeader.addAdmin(admin);
		assertTrue(fileinforeader.validUserId("004","admin"));
		
		
		assertFalse(fileinforeader.validUserId("007","student"));
		Student student = new Student("007","test","test","password590");
		fileinforeader.addStudent(student);
		assertTrue(fileinforeader.validUserId("007","student"));
	}


	
}
