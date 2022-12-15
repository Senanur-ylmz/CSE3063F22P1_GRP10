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
    
     public List<Student> getStudents() {
        return students;
    }
    

  public void register(Registration registration, String semester) throws FileNotFoundException, IOException, ParseException, java.text.ParseException{
     
        if(registration.isProvidePrereqs()== true){
            
            if(registration.isEnoughSeatLimit()==true){
              
                
                if(registration.getStudent().getTranscript().GPAchecked()==true){
                    
                      if(registration.getStudent().isCourseNotPassedBefore(registration.getCourses())==true){

                              if(registration.getStudent().isSemesterCorret(registration.getCourses())){
                            System.out.println("Assignment done");
                            afterReg(registration);
                        }

                        else{
                        
                            Log log = new Log();
                            log.logging_error("SemestorError",semester);
                            System.out.println("Semester Error");
                        }
                }
                else{
                  System.out.println("Course is already passed");
                }
                    
                }
                else{
                    System.out.println("Can not register new course.");
                    Log log = new Log();
                    log.logging_error("GPAError",semester);
                    System.out.println("GPA Error");
                }
            }
            
            else{
                    Log log = new Log();
                    log.logging_error("SeatLimitError",semester);
                    System.out.println("Not enough limits");
    
            }
        }
   
        else{
            Log log = new Log();
            log.logging_error("PrerequisiteError",semester);
            System.out.println("No Prerequisite");
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

