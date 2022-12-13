import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Student extends Person {

    private String studentId;
    private Transcript transcript;
    private String Semester;

    public Student(String studentId) throws FileNotFoundException, IOException, ParseException {
        super(name, address);
        this.studentId = studentId;
        this.transcript = new Transcript(studentId);
        
        Object obj = new JSONParser().parse(new FileReader(studentId+".json"));
        JSONObject jo = (JSONObject) obj;
        String SSemester = (String) jo.get("SSemester");
        setSemester(SSemester);
    }

    public String getStudentId() {
        return studentId;
    }

    public Transcript getTranscript() {
        return transcript;
    }
    
    	public boolean isSemesterCorret(Course courses) {
		return false;
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
    
     public boolean isSemesterCorret(Course course){
        System.out.println("Student: "+getStudentId());
        boolean isIt=false;
        int std_semester = Integer.valueOf(getSemester());

        System.out.println("coure_semester: "+course.getSemester());

        if(course.getSemester().equals("T")){
            if(std_semester>6){
                isIt=true;
            }
           }

        else if(course.getSemester().equals("N")){
            if(std_semester>1){
                isIt=true;
            }
           }
        
       else if(course.getSemester().equals("U")){
            if(std_semester>6){
                isIt=true;
            }
           }
        
        else if(course.getSemester().equals("F")){
            if(std_semester>7){
                isIt= true;
            }
           }
        else{
            int course_semester = Integer.parseInt(course.getSemester());
            if(std_semester>=course_semester){
                isIt= true;
            }
            else{
                isIt= false;
            }
        }

        return isIt;
    }

}
