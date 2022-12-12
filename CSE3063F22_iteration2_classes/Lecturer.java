import java.util.ArrayList;
import java.util.List;

public class Lecturer extends Staff {
    
    private List<CourseSection> courseSections;
    
    public Lecturer(String name, String address, String staffId) {
        super(name, address, staffId);
        this.courseSections = new ArrayList<>();
    }
    
    public List<CourseSection> getCourseSections() {
        return courseSections;
    }
    
    public void setCourseSections(List<CourseSection> courseSections) {
        this.courseSections = courseSections;
    }
    
    public void addCourseSection(CourseSection courseSection) {
        this.courseSections.add(courseSection);
    }
}