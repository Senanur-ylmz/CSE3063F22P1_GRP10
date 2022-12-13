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
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public Course getCourses() {
        return courses;
    }
    
    public void setCourses(Course  courses) {
        this.courses = courses;
    }
    
    public void addCourse(Course course) {
       // this.courses.add(course);
    }
    
    public void removeCourse(Course course) {
      //  this.courses.remove(course);
    }

    public boolean getisRegistered(){
        return this.isRegistered;
    }

    public void setisRegistered(boolean register){
         this.isRegistered = register;
    }

    public boolean isProvidePrereqs(){
        boolean isIt=false;
        List<String> course_prereqs = getCourses().getPrerequisites();
        List<Course> student_transcript = getStudent().getTranscript().getpassedCourses();
        
     //   System.out.println(course_prereqs.get(0));

        if(course_prereqs.isEmpty()){
          isIt=true;
          System.out.println("girdi");
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

    public boolean isEnoughSeatLimit(){
        boolean isIt=true;
        int seat_limit = getCourses().getSeatLimit();

        if(seat_limit<=0){
            isIt=false;
        }

        return isIt;

    }

    public void updateJSON(String key, int value) throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException{
        Object obj = new JSONParser().parse(new FileReader("courses/"+getCourses().getCourseId()+".json"));
        JSONObject jo = (JSONObject) obj;
        String new_value = String.valueOf(value);
        jo.replace(key, new_value);
        // jo.remove(key);
        // jo.put(key, new_value);
        FileWriter writer = new FileWriter((getCourses().getCourseId()+".json")); //overwrites the content of file
        writer.write(jo.toString());
        writer.close();
     
        
    }

    public void updateSchedule() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException{
        getStudent().getTranscript().addCoursetoSchedule(getCourses());
        
        Object obj = new JSONParser().parse(new FileReader(getStudent().getStudentId()+".json"));
        JSONObject jo = (JSONObject) obj;
        JSONArray Schedule = (JSONArray) jo.get("Schedule");
        
        Schedule.add(getCourses().getCourseId().toString());
    
        FileWriter writer = new FileWriter((getStudent().getStudentId()+".json"), false); //overwrites the content of file
        writer.write(jo.toString());
        writer.close();
     
        
    }




}
