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
}