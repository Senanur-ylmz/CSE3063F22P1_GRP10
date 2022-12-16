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

    public Student(String studentId) throws FileNotFoundException, IOException, ParseException {
        super(name, address);
        this.studentId = studentId;
        this.transcript = new Transcript(studentId);
        
        Object obj = new JSONParser().parse(new FileReader("students/"+studentId+".json"));
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
    
    //Check if the course is already passed before
   public boolean isCourseNotPassedBefore(Course course){
        boolean isIt=true;
        List<Course> student_passed = getTranscript().getSchedule();
        List<Course> student_transcript = getTranscript().getpassedCourses();
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

    
     public boolean isSemesterCorrect(Course course){
        System.out.println("Student: "+getStudentId());
        boolean isIt=false;
        int std_semester = Integer.valueOf(getSemester());

        System.out.println("coure_semester: "+course.getSemester());

        if(course.getSemester().equals("T") || course.getSemester().equals("E")){
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

	 public void updateStatus() throws FileNotFoundException, IOException, ParseException{
        Object obj = new JSONParser().parse(new FileReader("students/"+getStudentId()+".json"));
        JSONObject jo = (JSONObject) obj;
        jo.remove("ScheduleStatus");
        jo.put("ScheduleStatus", "Approved");

        FileWriter writer = new FileWriter(("students/"+getStudentId()+".json"), false); //overwrites the content of file
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
