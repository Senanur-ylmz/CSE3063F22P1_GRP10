import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Student extends Person {

    private String studentId;
    private Transcript transcript;

    public Student(String studentId) throws FileNotFoundException, IOException, ParseException {
        super(name, address);
        this.studentId = studentId;
        this.transcript = new Transcript(studentId);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public void ReadName() throws IOException, ParseException{
        JSONParser jsonParser=new JSONParser();
        FileReader reader =new FileReader(getStudentId()+".json");
        Object obj =jsonParser.parse(reader);
        JSONObject sObject=(JSONObject)obj;
        String fname=(String) sObject.get("FirstName");
        String lname=(String) sObject.get("LastName");

        System.out.println("Full Name is : " + fname + " " + lname);
    }
    
    //Check if the course is already passed before
    public boolean isCourseNotPassedBefore(Course course){
        boolean isIt=true;
        List<Course> student_passed = getTranscript().getSchedule();
        String[] passed_before = new String[student_passed.size()];

        if(student_passed.size()==0){
            isIt=true;
        }

        else{

        for(int i=0;i<student_passed.size();i++){
        passed_before[i]=student_passed.get(i).getCourseId();
        }
int i=0;
        while(i<passed_before.length){
        if((passed_before[i].equals(course.getCourseId()))==true){
            isIt=false;
        }
        i++;
        }
    }
    
        return isIt;
    }
}
