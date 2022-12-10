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

        public Course(String courseId) throws FileNotFoundException, IOException, ParseException {
            this.courseId = courseId;
            Object obj = new JSONParser().parse(new FileReader("courses/"+getCourseId()+".json"));
        JSONObject jo = (JSONObject) obj;

        String Cname = (String) jo.get("CourseName");
        setCourseName(Cname);

        JSONArray prereq = (JSONArray) jo.get("Prereq");
        this.prerequisites = new ArrayList<>();
        setPrerequisites(prereq);

     //   int seat_limit = Integer.parseInt(jo.get("Seatlimit"));
        String seat_limit = (String) jo.get("Seatlimit");
        int Seat_lim=Integer.parseInt(seat_limit);
        setSeatLimit(Seat_lim);
        //    this.courseName = courseName;
         //   this.prerequisites = prerequisites;
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

        public List<String> getPrerequisites() {
            return prerequisites;
        }

        public void setPrerequisites(List<String> prerequisites) {
            this.prerequisites = prerequisites;
        }

        public int getSeatLimit() {
            return Seatlimit;
        }

        public void setSeatLimit(int limit) {
            this.Seatlimit = limit;
        }


    }