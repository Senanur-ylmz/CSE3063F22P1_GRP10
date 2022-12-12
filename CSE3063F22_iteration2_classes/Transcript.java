import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
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
        Object obj = new JSONParser().parse(new FileReader(getStudentId()+".json"));
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

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public void addCoursetoSchedule(Course course) {
        this.Schedule.add(course);
        System.out.println("yeni schedule: "+getSchedule().toString());

    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }
}