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
                  Log log = new Log();
                  log.logging_error("AlreadyPassedError",semester);
                  System.out.println("Course is already passed");
                }
                    
                }
                else{
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



    public void afterReg(Student std,Course course) throws FileNotFoundException, IOException, ParseException{
        
        List<Course> student_schedule= std.getTranscript().getSchedule();
        String[] schedule = new String[student_schedule.size()];
        
    Advisor advisor = new Advisor();
    advisor.ApproveRegistration(std);
    if(std.getStatus()){

       for(int i=0;i<student_schedule.size();i++){
            schedule[i]=String.valueOf(student_schedule.get(i).getCourseId());
            System.out.println(schedule[i]);
            course.updateSeat_Limit(schedule[i]);
        }

        std.updateStatus();

    }
    else{
        Log log = new Log();
        log.logging_error("NotApproved", std.getSemester());
        System.out.println("Advisor did not approved schedule!");
    }

    }

}

