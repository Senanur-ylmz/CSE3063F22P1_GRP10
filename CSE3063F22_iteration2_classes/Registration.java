import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Registration {
    
    private Student student;
    private Course courses;
    private boolean isRegistered = false;
    
    public Registration(Student student, Course courses) {
        this.student = student;
        this.courses = courses;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public Course getCourses() {
        return courses;
    }


    public boolean isProvidePrereqs(){
        boolean isIt=false;
        List<String> course_prereqs = getCourses().getPrerequisites();
        List<Course> student_transcript = getStudent().getTranscript().getpassedCourses();
        
 

        if(course_prereqs.isEmpty()){
          isIt=true;
         
        }

        else{
        
        int i=0;
        while(i<course_prereqs.size()){

        if((student_transcript.contains(course_prereqs.get(i)))){
            isIt=true;
       }
        i++;
        
    }
}
        return isIt;
    }

   
    public boolean isSemesterCorrect(){
       
            boolean isIt=false;
            int std_semester = Integer.valueOf(getStudent().getSemester());
    
            System.out.println("coure_semester: "+getCourses().getSemester());
    
            if(getCourses().getSemester().equals("T") || getCourses().getSemester().equals("E")){
                if(std_semester>6){
                    isIt=true;
                }
               }
    
            else if(getCourses().getSemester().equals("N")){
                if(std_semester>1){
                    isIt=true;
                }
               }
            
           else if(getCourses().getSemester().equals("U")){
                if(std_semester>6){
                    isIt=true;
                }
               }
            
            else if(getCourses().getSemester().equals("F")){
                if(std_semester>7){
                    isIt= true;
                }
               }
            else{
                int course_semester = Integer.parseInt(getCourses().getSemester());
                if(std_semester >= course_semester){
                    isIt= true;
                }
                else{
                    isIt= false;
                }
            }
    
            return isIt;
        }
    



}
