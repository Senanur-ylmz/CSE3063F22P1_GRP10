public class Grade {
    
    private CourseSection courseSection;
    private Student student;
    private int grade;
    
    public Grade(CourseSection courseSection, Student student, int grade) {
        this.courseSection = courseSection;
        this.student = student;
        this.grade = grade;
    }
    
    public CourseSection getCourseSection() {
        return courseSection;
    }
    
    public void setCourseSection(CourseSection courseSection) {
        this.courseSection = courseSection;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public int getGrade() {
        return grade;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }
}