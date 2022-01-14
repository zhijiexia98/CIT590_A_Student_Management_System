/*
 * Author: Zhijie Xia
 * Author: Ruilin Yu
 */
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import files.FileInfoReader;
import courses.Course;
import roles.Admin;
import roles.Professor;
import roles.Student;

public class Controller {

	/**
	 * Create a global instance of fileinforeader
	 */
	public static FileInfoReader fir;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		Controller con = new Controller();
		fir = new FileInfoReader();
		fir.courseSetUp("courseInfo.txt");
		fir.userSetUp("adminInfo.txt", "3");
		fir.userSetUp("ProfInfo.txt", "2");
		fir.userSetUp("studentInfo.txt", "1");
		while (true) {
			System.out.println("--------------------------");
	        System.out.println("Students Management System");
	        System.out.println("--------------------------");
	        System.out.println(" 1 -- Login as a student");
	        System.out.println(" 2 -- Login as a professor");
	        System.out.println(" 3 -- Login as an admin");
	        System.out.println(" 4 -- End the system");
	        System.out.println("Please enter your option, eg. '1'.");
	        String order = sc.nextLine().trim();
	        if (order.equals("4")) {
	        	System.out.println("Goodbye!");
	        	sc.close();
	        	break;
	        }
	        System.out.println("Please enter your username, or type 'q' to end.");
			String name = sc.nextLine();
			if(name.equals("q")) {
				break;
			}
			else {
				System.out.println("Please enter your password, or type 'q' to end.");
				String password = sc.nextLine();
				if(password.equals("q")) {
					break;
				}
				else {
					//login into different subsets of the system with the inputed number 
					int num = fir.login(name, password, order);
					if(num != -1) {
						if(order.equals("1")) {
							con.studentLogin(fir.getStudent().get(num),sc);
						}
						else if(order.equals("2")) {
							con.professorLogin(fir.getProfessor().get(num),sc);
						}
						else if(order.equals("3")) {
							con.adminLogin(fir.getAdmin().get(num),sc);
						}
					}
				}
			}
		}
	}
	
	/**
	 * student login, 1: view all courses, 2: add courses to the list, 3: view enrolled courses, 
	 * 4: drop courses in your list, 5: view grades, 6: return to the previous menu 
	 * @param student
	 * @param sc
	 */
	private void studentLogin(Student student, Scanner sc) {
		while(true) {
            System.out.println("--------------------------");
            System.out.println("Welcome, " + student.getName());
            System.out.println("--------------------------");
            System.out.println(" 1 -- View all courses");
            System.out.println(" 2 -- Add courses to your list");
            System.out.println(" 3 -- View enrolled courses");
            System.out.println(" 4 -- Drop courses in your list");
            System.out.println(" 5 -- View grades");
            System.out.println(" 6 -- Return to previous menu");
            System.out.println("Please enter your option, eg. '1'.");

            String order = sc.nextLine();
            
            //View all courses: All courses are displayed in the console. 
            if(order.equals("1")) {
            	for (int i = 0; i < fir.getCourse().size(); i++){
                    Course c = fir.getCourse().get(i);
                    print(c);
                }
            }
            //add the course to the schedule
            else if(order.equals("2")) {
            	while(true) {
                    System.out.println("Please select the course ID you want to add to your list, e.g. 'CIT590");
                    System.out.println("Or enter 'q' to return to the previous menu.");
                    String s = sc.nextLine();
                    if(s.equals("q")) {
                    	break;
                    }
                    else {
                    	Course c = fir.getSpecificCourse(s);
                    	if(Objects.isNull(c) == false) {
                    		Course cc = fir.checkCourseConflict(student.getCourse(), c);
                    		//need to check if it is already in the list
                    		if (student.addCourse(c) == false) {
                    			System.out.println("The course you selected is already in your list");
                    			continue;
                    		}
                    		//need to check the conflict
                    		if(Objects.isNull(cc) == false) {
                    			System.out.printf("The course you selected has time conflict with %s %s.\n", cc.getCourseID(), cc.getCourseName());
                    		}
                    		else {
                    			System.out.println("Course added successfully");
                    		}
                    	}
                    	else {
                    		System.out.println("Course not found, please try again");
                    	}
                    }
            	}
            }
            //view all courses
            else if(order.equals("3")) {
            	System.out.println("The courses in your list:");
            	for (int i = 0; i < student.getCourse().size(); i++){
                    Course c = student.getCourse().get(i);
                    print(c);
                }
            }
            //drop the course you want
            else if(order.equals("4")) {
            	while(true) {
                    System.out.println("Please select the course ID you want to drop from your list, e.g. 'CIT590");
                    System.out.println("Or enter 'q' to return to the previous menu.");
                    String s = sc.nextLine();
                    if(s.equals("q")) {
                    	break;
                    }
                    else {
                    	Course c = fir.getSpecificCourse(s);
                    	//need to check whether the course is in the student's list
                		if (student.dropCourse(c) == false) {
                			System.out.println("The course you selected is not in your list");
                		}
                		else {
                			System.out.println("Course dropped successfully");
                		}
                    }
            	}
            }
            //view grades
            else if(order.equals("5")) {
            	System.out.println("Here are the courses you already taken, with your grade in a letter format");
            	for (int i = 0; i < student.getCoursegrade().size(); i++){
                    System.out.printf("Grade of %s %s: %s\n", student.getCoursegrade().get(i)[0],
                    		fir.getSpecificCourse(student.getCoursegrade().get(i)[0]).getCourseName(),
                    		student.getCoursegrade().get(i)[1]);
                }
            }
            //quit
            else if(order.equals("6")) {
            	break;
            }
		}
	}
	
	/**
	 * professor login, 1:view given courses, 2:view student list of the given course, 3:return to the menu
	 * @param professor
	 * @param sc
	 */
	private void professorLogin(Professor professor, Scanner sc) {
		while(true) {
            System.out.println("--------------------------");
            System.out.println("Welcome, " + professor.getName());
            System.out.println("--------------------------");
            System.out.println(" 1 -- View given courses");
            System.out.println(" 2 -- View student list of the given course");
            System.out.println(" 3 -- Return to the previous menu");
            System.out.println("Please enter your option, eg. '1'.");

            String order = sc.nextLine();
            
            //view the courses that the professor teaches
            if(order.equals("1")) {
            	 for (int i = 0; i < fir.getSpecificProfCourse(professor.getName()).size(); i++){
                     print(fir.getSpecificProfCourse(professor.getName()).get(i));
                 }
            }
            //view student list
            if(order.equals("2")) {
            	while(true) {
                    System.out.println("Please enter the course ID, eg. 'CIS519'.");
                    System.out.println("Or type 'q' to end.");

                    String s = sc.nextLine();
                    if (s.equals("q")) {
                        break;
                    }
                    else {
	                    Course c = fir.getSpecificCourse(s);
	                    if (Objects.isNull(c) == false) {
	                    	ArrayList <Student> addedStudent = new ArrayList<>();
	                		for (int i = 0; i < fir.getStudent().size(); i++){
	                            if (fir.getStudent().get(i).getCourse().contains(c)){
	                                addedStudent.add(fir.getStudent().get(i));
	                            }
	                        }
	                        for (int i = 0; i < addedStudent.size(); i++){
	                            System.out.printf("%s %s\n", addedStudent.get(i).getId(), addedStudent.get(i).getName());
	                        }
	                    }
	                    else {
	                        System.out.println("Course not found, please try again");
	                    }
	                }
            	}
            }
            //return to previous menu
            if(order.equals("3")) {
            	break;	
           }
		}
	}
	
	/**
	 * admin login, the following operations are shown 1-8 below
	 * @param admin
	 * @param sc
	 */
	private void adminLogin(Admin admin, Scanner sc) {
		while(true) {
            System.out.println("--------------------------");
            System.out.println("Welcome, " + admin.getName());
            System.out.println("--------------------------");
            System.out.println(" 1 -- View all courses");
            System.out.println(" 2 -- Add new courses");
            System.out.println(" 3 -- Delete courses");
            System.out.println(" 4 -- Add new professor");
            System.out.println(" 5 -- Delete professor");
            System.out.println(" 6 -- Add new student");
            System.out.println(" 7 -- Delete student");
            System.out.println(" 8 -- Return to previous menu");
            System.out.println("Please enter your option, eg. '1'.");

            String order = sc.nextLine();
            //return to the previous menu
            if(order.equals("8")) {
            	break;
            }
            //view all courses
            if(order.equals("1")) {
            	for (int i = 0; i < fir.getCourse().size(); i++){
                    Course c = fir.getCourse().get(i);
                    print(c);
                }
            }
            //add new courses
            if(order.equals("2")) {
            	while(true) {
                    System.out.println("Please enter the course ID, or type 'q' to end");
                    String id = sc.nextLine();
                    if (id.equals("q")) {
                        break;
                    }

                    // Check if the course id already exists
                    Course c = fir.getSpecificCourse(id);
                    if (Objects.isNull(c) == false) {
                        System.out.println("The course already exist");
                        continue;
                    }

                    System.out.println("Please enter the course name, or type 'q' to end");
                    String name = sc.nextLine();
                    if (name.equals("q")) {
                        break;
                    }

                    System.out.println("Please enter the course start time, or type 'q' to end");
                    String start = sc.nextLine();
                    if (start.equals("q")) {
                        break;
                    }

                    System.out.println("Please enter the course end time, or type 'q' to end");
                    String end = sc.nextLine();
                    if (end.equals("q")) {
                        break;
                    }

                    System.out.println("Please enter the course date, or type 'q' to end");
                    String date = sc.nextLine();
                    if (date.equals("q")) {
                        break;
                    }

                    System.out.println("Please enter the course capacity, or type 'q' to end");
                    String cap = sc.nextLine();
                    if (cap.equals("q")) {
                        break;
                    }

                    System.out.println("Please enter the course lecturer's id, or type 'q' to end. eg. '001'");
                    String lec_id = sc.nextLine();
                    if (lec_id.equals("q")) {
                        break;
                    }
                    
                    //check if the input professor already exists
                    if (fir.validUserId(lec_id, "professor") == false){
                        System.out.println("The Professor is not in the system, please add this professor first");
                        Professor prof = createNewProfessor(sc);
                        if (Objects.isNull(prof)) {
                        	break;
                        }
                        lec_id = prof.getId();
                    }
                    String profn = fir.getUser("professor", lec_id).getName();
                    
                    // check whether the new course has time conflicts with all of the lecturer's other courses
                    Course conf = new Course(id, name, profn, date, start, end, cap);
                    Course cconf = fir.checkCourseConflict(fir.getSpecificProfCourse(profn), conf);
                    if (Objects.isNull(cconf) == false){
                        System.out.printf("The new added course has time conflict with course: ");
                        print(cconf); 
                        System.out.print("Unable to add new course ");
                        print(conf);
                    }
                    else {
                        fir.addCourse(conf);
                        System.out.print("Successfully added the course: ");
                        print(conf);
                        break;
                    }
                }
            }
            //delete courses
            if(order.equals("3")) {
            	deleteSomething("course",sc);
            }
            //create new professor(s)
            if(order.equals("4")){
            	createNewProfessor(sc);
            }
            //delete professor
            if(order.equals("5")) {
            	deleteSomething("professor",sc);
            }
            //create new student
            if(order.equals("6")){
            	createNewStudent(sc);
            }
            //delete student
            if(order.equals("7")) {
            	deleteSomething("student",sc);
            }
		}
	}
	
	/**
	 * Delete professor/course/student
	 * @param s
	 * @param sc
	 */
	private void deleteSomething(String s, Scanner sc) {
		while(true) {
            System.out.printf("Please enter the %s ID you want delete, or type 'q' to end\n", s);
            String id = sc.nextLine();
            if (id.equals("q")) {
                break;
            }
            //check the input string is/isn't "course"
            if(s.equals("course")) {
            	//check if it is OK to delete the course
            	if(fir.deleteCourse(id) == false) {
            		System.out.println("The course you selected is not in your list");
            	}
            	else{
                    System.out.printf("%s deleted successfully\n",s);
            	}
            }
            //check if it is OK to delete the professor/student
            else if (fir.deleteUser(id, s) == false) {
                System.out.printf("%s does not exist\n", s);
            }
            else{
                System.out.printf("%s deleted successfully\n",s);
            }
        }
	}
	
	/**
	 * 
	 * @param Create new professor/student
	 * @param sc
	 * @return an array that contains all the information for professor/student
	 */
	private String[] createSomething(String s, Scanner sc) {
		String id;
		String username;
		while(true) {
    		System.out.printf("Please enter the %s's ID, or type 'q' to end\n", s);
    		id = sc.nextLine();
    		if (id.equals("q")) {
                return null;
            }
    		//check if the ID already exists
    		else if(fir.validUserId(id, s) == true) {
    			 System.out.println("The ID already exists");
    		}
    		else {
    			break;
    		}
		}
		System.out.printf("Please enter %s's name, or type 'q' to end\n", s);
		String name = sc.nextLine();
		if (name.equals("q")) {
            return null;
        }
		while(true) {
			System.out.println("Please enter a username");
    		username = sc.nextLine();
    		//check the validity of username
    		if(fir.validUsername(username, s) == true) {
    			System.out.println("The username you entered is not available");
    		}
    		else {
    			break;
    		}
		}
		System.out.println("Please enter a password");
		String password = sc.nextLine();
		return new String[] {id, name, username, password};
	}
	
	/**
	 * create new professor with the given information
	 * @param sc
	 * @return the created professor
	 */
	private Professor createNewProfessor(Scanner sc) {
		String[] prof_info = createSomething("professor", sc);
		if(prof_info == null) {
			return null;
		}
		Professor newProf = new Professor(prof_info[0], prof_info[1],prof_info[2], prof_info[3]);
        System.out.printf("Successfully added the professor: %s %s\n", prof_info[0], prof_info[1]);
        fir.addProfessor(newProf);
        return newProf;
	}
    
	/**
	 * create new student with the given information
	 * @param sc
	 */
	private void createNewStudent(Scanner sc) {
		String[] stu_info = createSomething("student", sc);
		Student newStu = new Student(stu_info[0], stu_info[1],stu_info[2], stu_info[3]);
		
		while(true) {
			System.out.println("Please enter ID of a course which this student already took, one in a time");
			System.out.println("Type 'q' to end, type 'n' to stop adding.");
			String course_id = sc.nextLine();
			//type 'q' to quit, so no records are recorded
			if (course_id.equals("q")) {
				return;
			}
			else if (course_id.equals("n")) {
                break;
            }
			else {
				System.out.println("Please enter the grade, eg, 'A'");
				String course_grade = sc.nextLine();
				String[] course_and_grade = new String[2]; 
				course_and_grade[0] = course_id;
				course_and_grade[1] = course_grade;
				newStu.getCoursegrade().add(course_and_grade);
			}
			
		}
        System.out.printf("Successfully added the student: %s %s\n", stu_info[0], stu_info[1]);
        fir.addStudent(newStu);
	}
	
	/**
	 * print the course information
	 * @param c
	 */
	private void print(Course c) {
		int enrollment = 0;
		
		for (int i = 0; i < fir.getStudent().size(); i++){
            if (fir.getStudent().get(i).getCourse().contains(c)){
                enrollment ++;
            }
        }
		System.out.printf("%s | %s, %s - %s on %s, with course capacity: %s, students: %d, lecturer: %s\n",
				c.getCourseID(), c.getCourseName(), c.getStart(), c.getEnd(), c.getDays(), c.getCapacity(), enrollment, c.getLecturer());
	}

}
