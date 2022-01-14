package files;

import roles.Admin;
import roles.Professor;
import roles.Student;
import roles.User;
import courses.Course;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;


public class FileInfoReader {
	
	/**
	 * admins
	 */
    public ArrayList<Admin> admins = new ArrayList<Admin>();
    
    /**
     * professors
     */
    public ArrayList<Professor> profs = new ArrayList<Professor>();
    
    /**
     * student
     */
    public ArrayList<Student> students = new ArrayList<Student>();
    
    /**
     * courses
     */
    public ArrayList<Course> courses = new ArrayList<Course>();
    
    
    /**
     * read the course info file and 
     * set up the course array 
     * @param courseInfo
     */
    public void courseSetUp (String courseInfo) {
		try {
            File f = new File (courseInfo);
            FileReader fd = new FileReader(f);
            BufferedReader br = new BufferedReader(fd);
            while (true) {
                String line;
                if ((line = br.readLine()) != null) {
                    String [] course_array = line.trim().split(";");
                    
                    Course newCourse = new Course(course_array);
                    courses.add(newCourse);
                }
                else {
                	break;
                }
            }
            fd.close();
            br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
    }
    
    /**
     * read the user info file and 
     * set up the user array 
     * @param courseInfo
     */
    public void userSetUp (String userInfo, String type) {
    		try {
                File f = new File (userInfo);
                FileReader fd = new FileReader(f);
                BufferedReader br = new BufferedReader(fd);
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    String [] input_array = line.trim().split(";");
                    
                    if (type.equals("3")) {
                        Admin newAdmin = new Admin(input_array);
                        admins.add(newAdmin);
                    }
                    
                    else if (type.equals("2")) {
                        Professor newProfessor = new Professor(input_array);
                        profs.add(newProfessor);
                    }
                    
                    else if (type.equals("1")) {
                    	Student newStudent = new Student(input_array);
                    	students.add(newStudent);
                    }
                }
                fd.close();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    /**
     * get the corresponding user with the id
     * @param username
     * @param type
     * @param id
     * @return corresponding user, null if id don't exsit
     */
    public User getUser(String type, String id) {
    	 if(type.equals("admin")) {
             for (int i = 0; i < admins.size(); i++){
            	 if (admins.get(i).getId().equals(id)){
            		 return admins.get(i);         
                 }
             }
         }
         
         else if(type.equals("professor")) {
        	 for (int i = 0; i < profs.size(); i++){
        		 if (profs.get(i).getId().equals(id)){
                	 return profs.get(i);         
                 }
             }
         }
         
         else if(type.equals("student")) {
        	 for (int i = 0; i < students.size(); i++){
        		 if (students.get(i).getId().equals(id)){
                	 return students.get(i);         
                 }
             }
         }
    	 return null;   
    }
    
    /**
     * return one specific course with the given course id
     * @param id
     * @return one specific course
     */
    public Course getSpecificCourse(String id) {
    	for (int i = 0; i < courses.size(); i++){
    		if (courses.get(i).getCourseID().equals(id)){
    			return courses.get(i);         
            }
        }
    	return null;
    }
    
    /**
     * return all the course one specific professor teach
     * @param id
     * @param name
     * @return all the course one specific professor teach
     */
    public ArrayList<Course> getSpecificProfCourse(String name) {
    	ArrayList<Course> SpecificProfCourse = new ArrayList<>();
    	for (int i = 0; i < courses.size(); i++) {
    		if(courses.get(i).getLecturer().equals(name)) {
    			SpecificProfCourse.add(courses.get(i));
    		}
    	}
    	return SpecificProfCourse;
    }
    
    /**
     * get the corresponding professor list
     * @return professor list
     */
    public ArrayList<Professor> getProfessor(){
    	return profs;
    }
    
    /**
     * get the corresponding admin list
     * @return admin list
     */
    public ArrayList<Admin> getAdmin(){
    	return admins;
    }
    
    /**
     * get the corresponding student list
     * @return student list
     */
    public ArrayList<Student> getStudent(){
    	return students;
    }
    
    /**
     * get the corresponding course list
     * @return courses list
     */
    public ArrayList<Course> getCourse(){
    	return courses;
    }
    
    /**
     * add new professor to the give professor list
     * @param professor
     */
    public void addProfessor(Professor professor) {
    	profs.add(professor);
    }
    
    /**
     * add new admin to the give admin list
     * @param admin
     */
    public void addAdmin(Admin admin) {
    	admins.add(admin);
    }
    
    /**
     * add new student to the given student list
     * @param student
     */
    public void addStudent(Student stud) {
    	students.add(stud);
    }
    
    /**
     * add new course to the given course list
     * @param course
     */
    public void addCourse(Course course) {
    	courses.add(course);
    }
    
    /**
     * check whether the user's input is valid or not
     * @param username
     * @param password
     * @param type
     * @return return corresponding position, if not, return -1
     */
    public int login(String username, String password, String type){   	
        if(type.equals("3")) {
            for (int i = 0; i < admins.size(); i++){
                if (admins.get(i).getUsername().equals(username)){
                    if (admins.get(i).getPassword().equals(password)){
                        return i;
                    }
                }
            }
            return -1;
        }
        else if(type.equals("2")) {
            for (int i = 0; i < profs.size(); i++){
                if (profs.get(i).getUsername().equals(username)){
                    if (profs.get(i).getPassword().equals(password)){
                        return i;
                    }
                }
            }
            return -1;
        }
        else if(type.equals("1")) {
            for (int i = 0; i < students.size(); i++){
                if (students.get(i).getUsername().equals(username)){
                    if (students.get(i).getPassword().equals(password)){
                        return i;
                    }
                }
            }
            return -1;
        }
        return -1;
     }
    
    /**
     * check the given user id exist or not
     * @param id
     * @param type
     * @return true if the user id exist, false if not
     */
    public boolean validUserId(String id, String type) {
   	 	if(type.equals("admin")) {
   	 		for (int i = 0; i < admins.size(); i++){
   	 			if (admins.get(i).getId().equals(id)){
   	 				return true;         
   	 			}
   	 		}
   	 		return false;
   	 	}
     
   	 	else if(type.equals("professor")) {
   	 		for (int i = 0; i < profs.size(); i++){
   	 			if (profs.get(i).getId().equals(id)){
   	 				return true;         
   	 			}
   	 		}
   	 		return false;
   	 	}
     
   	 	else if(type.equals("student")) {
   	 		for (int i = 0; i < students.size(); i++){
   	 			if (students.get(i).getId().equals(id)){
   	 				return true;         
   	 			}
   	 		}
   	 		return false;
   	 	}
   	 	return false;
    }
    
    
    /**
     * check the given username exist or not
     * @param id
     * @param type
     * @return true if the username exist, false if not
     */
    public boolean validUsername(String username, String type) {
   	 	if(type.equals("admin")) {
   	 		for (int i = 0; i < admins.size(); i++){
   	 			if (admins.get(i).getUsername().equals(username)){
   	 				return true;         
   	 			}
   	 		}
   	 		return false;
   	 	}
     
   	 	else if(type.equals("professor")) {
   	 		for (int i = 0; i < profs.size(); i++){
   	 			if (profs.get(i).getUsername().equals(username)){
   	 				return true;         
   	 			}
   	 		}
   	 		return false;
   	 	}
     
   	 	else if(type.equals("student")) {
   	 		for (int i = 0; i < students.size(); i++){
   	 			if (students.get(i).getUsername().equals(username)){
   	 				return true;         
   	 			}
   	 		}
   	 		return false;
   	 	}
   	 	return false;
    }
    
    /**
     * check whether two courses has time conflict
     * @param courseOne
     * @param courseTwo
     * @return true if there's a time conflict, false if not
     */
    public boolean courseConflict(Course courseOne, Course courseTwo) {
    	String[] courseOneDate;
    	String[] courseTwoDate;
    	
    	courseOneDate = courseOne.getDays().split("(?!^)");
    	courseTwoDate = courseTwo.getDays().split("(?!^)");
    	
    	for(String day1 : courseOneDate ) {
    		for(String day2 : courseTwoDate ) {
    			if (day1.equals(day2)) {
    				
    				//get the start time in the string from
    				String[] courseOneStartTime = courseOne.getStart().split(":");
    				//cast into integer for later comparison
                    int IntcourseOneStartTime = Integer.parseInt(courseOneStartTime[0] + courseOneStartTime[1]);
                    String[] courseOneEndTime = courseOne.getEnd().split(":");
                    int IntcourseOneEndTime = Integer.parseInt(courseOneEndTime[0] + courseOneEndTime[1]);
    				String[] courseTwoStartTime = courseTwo.getStart().split(":");
                    int IntcourseTwoStartTime = Integer.parseInt(courseTwoStartTime[0] + courseTwoStartTime[1]);
                    String[] courseTwoEndTime = courseTwo.getEnd().split(":");
                    int IntcourseTwoEndTime = Integer.parseInt(courseTwoEndTime[0] + courseTwoEndTime[1]);
                    
                    if(((IntcourseOneStartTime <= IntcourseTwoStartTime) && (IntcourseTwoStartTime < IntcourseOneEndTime)) ||
                    		((IntcourseOneStartTime < IntcourseTwoEndTime) && (IntcourseTwoEndTime <= IntcourseOneEndTime))){
                    	return true;
                    }  				
    			}
    		}
    	}
    	return false;
    }
 
    
    
    /**
     * check the given course whether has conflict with the course array
     * @param courses, the whole set of courses
     * @param course, the given course 
     * @return the given course if there's a conflict, null if not
     */
    public Course checkCourseConflict(ArrayList<Course> courses, Course course) {
    	for(Course sample : courses) {
    		if(courseConflict(sample,course)) {
    			return sample;
    		} 		
    	}
    	return null;
    }
    
    /**
     * remove the user with respect the given user id
     * @param username
     * @param id
     * @param type
     * @return true if the given user has successfully been removed, false if not
     */
    public boolean deleteUser(String id, String type) {
    	User user = getUser(type,id);
    	
   	 	if(type.equals("admin")) {
   	 		if (Objects.isNull(user)){
   	 			return false;
   	 		}
   	 		else{
   	 			admins.remove(user);
   	 			return true;
   	 		}
   	 	}   
   	 	else if(type.equals("professor")) {
   	 		if (Objects.isNull(user)){
   	 			return false;
   	 		}
   	 		else{
   	 			profs.remove(user);
   	 			return true;
   	 		}
   	 	}   
   	 	else if(type.equals("student")) {
   	 		if (Objects.isNull(user)){
   	 			return false;
   	 		}
   	 		else{
   	 			students.remove(user);
   	 			return true;
   	 		}
   	 	}
   	 	return false;
    }
    
    /**
     * remove the specific course from the course list
     * @param id
     * @return true if the course has been successfully removed, false if the id does not exist
     */
    public boolean deleteCourse(String id) {
    	Course course = getSpecificCourse(id);
    	if (Objects.isNull(course)){
	 			return false;
	 		}
	 		else{
	 			courses.remove(course);
	 			return true;
	 		}
    }
    	
    
}
