import java.util.ArrayList;
import java.util.List;

public class Lecturer extends Staff {
    
    private List<CourseSection> courseSections;
    
    public Lecturer(String name, String address, String staffId) {
        super(name, address, staffId);
        this.courseSections = new ArrayList<>();
    }
}
