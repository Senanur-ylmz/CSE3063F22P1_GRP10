import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Transcript {

    private String studentId;
    private List<Course> passedCourses;
    private List<Course> Schedule;
    private List<Grade> grades;

    public Transcript(String studentId) throws FileNotFoundException, IOException, ParseException {
        this.studentId = studentId;
        Object obj = new JSONParser().parse(new FileReader("students/"+getStudentId()+".json"));
        JSONObject jo = (JSONObject) obj;

        JSONArray PassedCourses = (JSONArray) jo.get("PassedCourses");
        this.passedCourses = new ArrayList<>();

        setpassedCourses(PassedCourses);

        JSONArray Schedule = (JSONArray) jo.get("Schedule");
        this.Schedule = new ArrayList<>();
        setSchedule(Schedule);
        
        this.grades = new ArrayList<>();
    }


    public String getStudentId() {
        return studentId;
    }

    public List<Course> getpassedCourses() {
        return passedCourses;
    }

    public void setpassedCourses(List<Course> courses) {
        this.passedCourses = courses;
    }

    public List<Course> getSchedule() {
        return Schedule;
    }

    public void setSchedule(List<Course> courses) {
        this.Schedule = courses;
    }

    public void addCoursetoSchedule(Course course) {
        this.Schedule.add(course);
        System.out.println("yeni schedule: "+getSchedule().toString());

    }

    
    public boolean GPAchecked() throws FileNotFoundException, IOException, ParseException{
        boolean isIt = true;
        Object obj = new JSONParser().parse(new FileReader("students/"+getStudentId()+".json"));
        JSONObject jo = (JSONObject) obj;
        String GPA = (String) jo.get("GPA");
        double gpa =Double.parseDouble(GPA);


        if( gpa < 2.00){
            isIt=false;
            System.out.println("Below 2 GPA");
            

        }
        else{
            isIt=true;
            System.out.println("Above 2 GPA");
            
        }

        return isIt;


    }
    
    public boolean isCourseNotPassedBefore(Course course){
        boolean isIt=true;
        List<Course> student_passed = getSchedule();
        List<Course> student_transcript = getpassedCourses();
        String[] passed_before = new String[student_passed.size()];
        String[] transcript = new String[student_transcript.size()];

        if((student_transcript.size()==0)){
            isIt=true;
        }
    
        else{
    
        for(int i=0;i<student_transcript.size();i++){
        
      
        transcript[i] = String.valueOf(student_transcript.get(i));
    
        }
    
        int i=0;

        while(i<transcript.length){
        
        if((transcript[i].equals(course.getCourseId()))==true){
            isIt=false;
        }
        i++;
        }
    

        for(int x=0;x<student_passed.size();x++){

            passed_before[x]=String.valueOf(student_passed.get(x));
    
            }
    
            int x=0;
            while(x<passed_before.length){
              
            if((passed_before[x].equals(course.getCourseId()))==true){
                isIt=false;
            }
            x++;
            }
        }

    return isIt;

    }
    
}
