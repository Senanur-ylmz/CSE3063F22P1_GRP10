import java.util.ArrayList;
import java.util.List;

public class StudentAffairsStaff extends Staff {
    
    private List<Advisor> advisors;
    
    public StudentAffairsStaff(String name, String address, String staffId) {
        super(name, address, staffId);
        this.advisors = new ArrayList<>();
    }
    
    public List<Advisor> getAdvisors() {
        return advisors;
    }
    
    public void setAdvisors(List<Advisor> advisors) {
        this.advisors = advisors;
    }
    
    public void addAdvisor(Advisor advisor) {
        this.advisors.add(advisor);
    }
}
