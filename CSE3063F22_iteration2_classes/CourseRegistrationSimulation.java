import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import org.json.simple.parser.ParseException;

public class CourseRegistrationSimulation {
    
    private CourseRegistrationSystem courseRegistrationSystem;
    
    public CourseRegistrationSimulation(CourseRegistrationSystem courseRegistrationSystem) {
        this.courseRegistrationSystem = courseRegistrationSystem;


    }

    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException, java.text.ParseException{
        Scanner sem= new Scanner(System.in);
       System.out.println("Semester: "); 
       
       String semester = sem.next();
       Scanner student_number = new Scanner(System.in);
       System.out.println("Number of students: ");
       int number = student_number.nextInt();
       
       Log.create_logFile(semester);

       randomStudent_accSemester rand = new randomStudent_accSemester();
       rand.generate_randomStudent(semester, number);
       registration_simulation();


    
    }
    
    public void start(){
        // read parameters from parameters.json file
        // read transcripts from json files
        // create courses
        // create course sections
        // create students
        // create staff
        // assign advisors to students
        // register students for courses
        // assign grades for each course section
        // print transcripts
        // save transcripts to json files
    	
    }

    public static void registration_simulation(String semester) throws FileNotFoundException, IOException, ParseException, java.text.ParseException{
          // Get student ids  to register courses
          File directory = new File("students/");
          File[] students = directory.listFiles();
  
          for(int i=0;i<students.length;i++){
          File student = students[i];
          String student_id = String.valueOf(student);
  
          student_id = student.getAbsolutePath().substring(student.getAbsolutePath().lastIndexOf("\\")+1);
          student_id = student_id.split(".json")[0];
  
          // Register random courses
          File dir = new File("courses/");
          File[] courses = dir.listFiles();
  
          Student std = new Student(student_id);
          int times = ThreadLocalRandom.current().nextInt(1, Integer.parseInt(std.getSemester())*6);
          
          for(int n=0;n<times;n++){
              // BU KISIM FONKSİYON YAPILABİLİR
              File course = courses[ThreadLocalRandom.current().nextInt(courses.length)];
              String course_id = String.valueOf(course);
      
              course_id = course.getAbsolutePath().substring(course.getAbsolutePath().lastIndexOf("\\")+1);
              course_id = course_id.split(".json")[0];
              
      
              Course c = new Course(course_id);
             
              Registration register = new Registration(std, c);
              CourseRegistrationSystem registration = new CourseRegistrationSystem();
              registration.register(register);
              //
          }
  
          CourseRegistrationSystem after = new CourseRegistrationSystem();
          after.afterReg(std);
  
          }
      
    }
    

