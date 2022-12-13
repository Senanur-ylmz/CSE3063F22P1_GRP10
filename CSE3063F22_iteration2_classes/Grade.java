public class Grade {
    
    private CourseSection courseSection;
    private Student student;
    private int grade;
    
    public Grade(CourseSection courseSection, Student student, int grade) {
        this.courseSection = courseSection;
        this.student = student;
        this.grade = grade;
    }
    
}
