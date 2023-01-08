from Course import Course
from Student import Student


class Registration:
    # Initializes a new registration with the given student and courses.
    def __init__(self, student: 'Student', courses: 'Course'):
        self.__student = student
        self.__courses = courses
        
     # Returns the student for this registration.
    def getStudent(self) -> 'Student':
        return self.__student
    
     # Returns the courses for this registration.
    def set_student(self, student: 'Student'):
        self.__student = student
    
    def getCourses(self) -> 'Course':
        return self.__courses
    
    def set_courses(self, courses: 'Course'):
        self.__courses = courses
    
    def add_course(self, course: 'Course'):
        pass
    
    def remove_course(self, course: 'Course'):
        pass
    
    # Returns True if the student has completed the prerequisites for the courses,
    # False otherwise.
    def is_provide_prereqs(self) -> bool:
        is_it = False
        course_prereqs = self.getCourses().get_prerequisites()
        student_transcript = self.getStudent().get_transcript().get_passed_courses()
        
        if not course_prereqs:
            is_it = True
        else:
            i = 0
            while i < len(course_prereqs):
                if course_prereqs[i] in student_transcript:
                    is_it = True
                i += 1
        return is_it
     # Returns True if the student is eligible to take the courses in the current semester,
    # False
    def is_semester_correct(self):
        isIt=False
        std_semester = int(self.getStudent().getSemester())

        if self.getCourses().getSemester() in ("T", "E"):
            if std_semester > 6:
                isIt = True
        elif self.getCourses().getSemester() == "N":
            if std_semester > 1:
                isIt = True
        elif self.getCourses().getSemester() == "U":
            if std_semester > 6:
                isIt = True
        elif self.getCourses().getSemester() == "F":
            if std_semester > 7:
                isIt = True
        else:
            course_semester = int(self.getCourses().getSemester())
            if std_semester >= course_semester:
                isIt = True
            else:
                isIt = False

        return isIt
