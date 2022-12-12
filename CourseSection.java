import java.util.ArrayList;
import java.util.List;

public class CourseSection {
    
    private int sectionNumber;
    private Course course;
    private List<Student> students;
    private List<Grade> grades;
    
    public CourseSection(int sectionNumber, Course course) {
        this.sectionNumber = sectionNumber;
        this.course = course;
        this.students = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
    
    public int getSectionNumber() {
        return sectionNumber;
    }
    
    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    public List<Grade> getGrades() {
        return grades;
    }
    
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
    
    public void addStudent(Student student) {
        this.students.add(student);
    }
    
    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }
}
