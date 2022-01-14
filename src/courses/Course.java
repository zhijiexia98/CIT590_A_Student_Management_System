package courses;

public class Course {
	
	/**
	 * course id
	 */
	private String courseID;
	
	/**
	 * course name
	 */
	private String courseName;
	
	/**
	 * name of lecturer
	 */
	private String lecturer;
	
	/**
	 * number of days
	 */
	private String days;
	
	/**
	 * start time
	 */
	private String start;
	
	/**
	 * end time
	 */
	private String end;
	
	/**
	 * capacity of the course
	 */
	private String capacity;
	
	//constructors
	/**
     * sets all required information
     */
	public Course(String ci, String cn, String lec, String days, String start, String end, String cap) {
        this.courseID = ci;
        this.courseName = cn;
        this.lecturer = lec;
        this.days = days;
        this.start = start;
        this.end = end;
        this.capacity = cap;
    }
	
	/**
	 * sets all required information within an array that contains all the information
	 * @param courseInfo
	 */
    public Course(String[] courseInfo) {
        this.courseID = courseInfo[0].trim();
        this.courseName = courseInfo[1].trim();
        this.lecturer = courseInfo[2].trim();
        this.days = courseInfo[3].trim();
        this.start = courseInfo[4].trim();
        this.end = courseInfo[5].trim();
        this.capacity = courseInfo[6].trim();
    }
    
    //getters
    /**
     * @return courseID
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * @return courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @return lecturer
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * @return days
     */
    public String getDays() {
        return days;
    }

    /**
     * @return start
     */
    public String getStart() {
        return start;
    }

    /**
     * @return end
     */
    public String getEnd() {
        return end;
    }

    /**
     * @return capacity
     */
    public String getCapacity() {
        return capacity;
    }
    

}
