import json

from Course import Course

class Transcript(object):
    def __init__(self, student_id):
        self.__student_id = student_id
        with open(f"students/{self.__student_id}.json", "r") as f:
            data = json.load(f)
        
        self.__passed_courses = []
        for course in data["PassedCourses"]:
            self.__passed_courses.append(Course(course).get_course_id())

        self.__Schedule = []
        for course in data["Schedule"]:
            self.__Schedule.append(Course(course).get_course_id())

        self.__grades = []

    def get_student_id(self):
        return self.__studentId

    def set_student_id(self, studentId):
        self.__studentId = studentId

    def get_passed_courses(self):
        return self.__passed_courses

    def set_passed_courses(self, courses):
        self.__passedCourses = courses

    def get_schedule(self):
        return self.__Schedule

    def set_schedule(self, courses):
        self.__Schedule = courses

    def get_grades(self):
        return self.__grades

    def set_grades(self, grades):
        self.__grades = grades

    def add_course_to_schedule(self, course : Course):
        self.__Schedule.append(course.get_course_id())

    def add_grade(self, grade):
        self.__grades.add(grade)

    def GPA_checked(self):
        with open(f"students/{self.__student_id}.json") as file:
            data = json.load(file)
        gpa = data["GPA"]
        if gpa < 2.0:
            return False
        else:
            return True
    
    def is_course_not_passed_before(self,course:Course):
        if ((course.get_course_id() in self.get_passed_courses()) | (course.get_course_id() in self.get_schedule())):
            return False
        else:
            return True