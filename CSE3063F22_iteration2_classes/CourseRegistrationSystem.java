import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

public class CourseRegistrationSystem {
    
    private List<Course> courses;
    private List<CourseSection> courseSections;
    private List<Student> students;
    private List<Staff> staff;
    private List<Registration> registrations;
    private List<Grade> grades;
    
    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.courseSections = new ArrayList<>();
        this.students = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.registrations = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    
    public List<CourseSection> getCourseSections() {
        return courseSections;
    }
    
    public void setCourseSections(List<CourseSection> courseSections) {
        this.courseSections = courseSections;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    public List<Staff> getStaff() {
        return staff;
    }
    
    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }
    
    public List<Registration> getRegistrations() {
        return registrations;
    }
    
    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
    
    public List<Grade> getGrades() {
        return grades;
    }
    
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
    
    public void addCourse(Course course) {
        this.courses.add(course);
    }
    
    public void addCourseSection(CourseSection courseSection) {
        this.courseSections.add(courseSection);
    }
    
    public void addStudent(Student student) {
        this.students.add(student);
    }
    
    public void addStaff(Staff staff) {
        this.staff.add(staff);
    }
    
    public void addRegistration(Registration registration) {
        this.registrations.add(registration);
    }
    
    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }


  public void register(Registration registration) throws FileNotFoundException, IOException, ParseException, java.text.ParseException{
     
        if(registration.isProvidePrereqs()== true){
            
            if(registration.isEnoughSeatLimit()==true){
              
                
                if(registration.getStudent().getTranscript().GPAchecked()==true){
                    
                      if(registration.getStudent().isCourseNotPassedBefore(registration.getCourses())==true){

                         System.out.println("Atama gerçekleşti");
                         afterReg(registration);
                }
                else{
                  System.out.println("Course is already passed");
                }
                    
                }
                else{
                    System.out.println("Can not register new course.");
                    Log log = new Log("GPAError");
                    log.logging_error("1");
                    System.out.println("GPA Error");
                }
            }
            
            else{
                    Log log = new Log("SeatLimitError");
                    log.logging_error("1");
                    System.out.println("Yeterli limit yok");
    
            }
        }
   
        else{
            Log log = new Log("PrerequisiteError");
            log.logging_error("1");
            System.out.println("Prereq yok");
        }
            
    }



    public void afterReg(Registration r) throws FileNotFoundException, IOException, ParseException, java.text.ParseException{
        
        //Decrease seat limit
        int seat_limit = r.getCourses().getSeatLimit();
        r.getCourses().setSeatLimit(seat_limit-1);
        r.updateJSON("Seatlimit", seat_limit-1);

        //Add course to student's schedule which is in transcript
        r.updateSchedule();



    }

}

