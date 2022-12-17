import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Student extends Person {

    private String studentId;
    private Transcript transcript;
    private String Semester;
    private boolean scheduleStatus = false;

    public Student(String studentId) throws FileNotFoundException, IOException, ParseException {
        super(name, address);
        this.studentId = studentId;
        this.transcript = new Transcript(studentId);
        
        Object obj = new JSONParser().parse(new FileReader("students/"+studentId+".json"));
        JSONObject jo = (JSONObject) obj;
        String SSemester = (String) jo.get("SSemester");
        setSemester(SSemester);
    }
	
    public void setStatus(boolean status) throws FileNotFoundException, IOException, ParseException{
        scheduleStatus=status;
    }

    public boolean getStatus() {
        return scheduleStatus;
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
   
    public String getSemester() {
		return Semester;
	}

     public void setSemester(String semester) {
		Semester = semester;
	}

    public void ReadName() throws IOException, ParseException{
        JSONParser jsonParser=new JSONParser();
        FileReader reader =new FileReader("students/"+getStudentId()+".json");
        Object obj =jsonParser.parse(reader);
        JSONObject sObject=(JSONObject)obj;
        String fname=(String) sObject.get("FirstName");
        String lname=(String) sObject.get("LastName");

        System.out.println("Full Name is : " + fname + " " + lname);
    }

    public void updateStatus() throws FileNotFoundException, IOException, ParseException{
        Object obj = new JSONParser().parse(new FileReader("students/"+getStudentId()+".json"));
        JSONObject jo = (JSONObject) obj;
        jo.remove("ScheduleStatus");
        jo.put("ScheduleStatus", "Approved");

        FileWriter writer = new FileWriter(("students/"+getStudentId()+".json"), false); //overwrites the content of file
        writer.write(jo.toString());
        writer.close();
    }
    
    public void updateJSON(String courseid) throws FileNotFoundException, IOException, org.json.simple.parser.ParseException{
        System.out.println(courseid);
        Object obj = new JSONParser().parse(new FileReader("courses/"+courseid+".json"));
        JSONObject jo = (JSONObject) obj;
        int value = Integer.parseInt(String.valueOf(jo.get("Seatlimit")));
        String new_value = String.valueOf(value-1);
        
        jo.remove("Seatlimit");
        jo.put("Seatlimit", new_value);

        FileWriter writer = new FileWriter("courses/"+courseid+".json",false);
        writer.write(jo.toString());
        writer.close();
     
    }
    
    public void updateSchedule(Course course) throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException{
        getTranscript().addCoursetoSchedule(course);
        
        Object obj = new JSONParser().parse(new FileReader("students/"+getStudentId()+".json"));
        JSONObject jo = (JSONObject) obj;
        JSONArray Schedule = (JSONArray) jo.get("Schedule");
        
        Schedule.add(course.getCourseId().toString());
    
        FileWriter writer = new FileWriter(("students/"+getStudentId()+".json"), false); //overwrites the content of file
        writer.write(jo.toString());
        writer.close();
     
        
    }

   
}
