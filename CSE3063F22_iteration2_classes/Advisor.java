public interface Advisor {
    
    public void adviseStudent(Student student);
    
    public void registerStudent(Student student, Course course);
    
    public void unregisterStudent(Student student, Course course);
    
}
