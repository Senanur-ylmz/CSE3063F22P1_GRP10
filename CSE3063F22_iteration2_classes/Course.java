import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Course {

        private String courseId;
        private String courseName;
        private List<String> prerequisites;
        private int Seatlimit;
        private String Semester;

        public Course(String courseId) throws FileNotFoundException, IOException, ParseException {
            this.courseId = courseId;
            Object obj = new JSONParser().parse(new FileReader("courses/"+getCourseId()+".json"));
        JSONObject jo = (JSONObject) obj;

        String Cname = (String) jo.get("CourseName");
        setCourseName(Cname);

        JSONArray prereq = (JSONArray) jo.get("Prereq");
        this.prerequisites = new ArrayList<>();
        setPrerequisites(prereq);
        
        String seat_limit = (String) jo.get("Seatlimit");
        int Seat_lim=Integer.parseInt(seat_limit);
        setSeatLimit(Seat_lim);

       
        String CSemester = (String) jo.get("CourseType");
        setSemester(CSemester.substring(0, 1));
                
        }

        public String getCourseId() {
            return courseId;
        }

        // public void setCourseId(passedCourses) {
        //     this.courseId = passedCourses;
        // }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getSeatLimit() {
            return Seatlimit;
        }

        public void setSeatLimit(int limit) {
            this.Seatlimit = limit;
        }
        
        public String getSemester() {
            return Semester;
        }

        public void setSemester(String semester) {
            this.Semester = semester;
        }
        public List<String> getPrerequisites() {
            return prerequisites;
        }

        public void setPrerequisites(List<String> prerequisites) {
            this.prerequisites = prerequisites;
        }
        
        public boolean isEnoughSeatLimit(){
            boolean isIt=true;
            int seat_limit = getSeatLimit();
            
    
            if(seat_limit<=0){
                isIt=false;
            }
    
            return isIt;
    
        }


        
         public void updateSeat_Limit(String courseid) throws FileNotFoundException, IOException, org.json.simple.parser.ParseException{
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



    }
