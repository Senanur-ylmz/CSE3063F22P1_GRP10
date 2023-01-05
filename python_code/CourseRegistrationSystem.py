from Log import Log
from Advisor import Advisor
from Student import Student
from Course import Course
from Registration import Registration
import logging

class CourseRegistrationSystem:
    
        def __init__(self):
            self.__courses = []
            self.__course_sections = []
            self.__students = []
            self.__staff = []
            self.__registrations = []
            self.__grades = []
        
        def get_courses(self):
            return self.__courses
        
        def set_courses(self, courses):
            self.__courses = courses
        
        def get_course_sections(self):
            return self.__course_sections
        
        def set_course_sections(self, course_sections):
            self.__course_sections = course_sections
        
        def get_students(self):
            return self.__students
        
        def set_students(self, students):
            self.__students = students
        
        def get_staff(self):
            return self.__staff
        
        def set_staff(self, staff):
            self.__staff = staff
        
        def get_registrations(self):
            return self.__registrations
        
        #def set_registrations(self, registrations):
        def get_grades(self):
            return self.__grades
        
        def set_grades(self, grades):
            self.__grades = grades
        
        def add_course(self, course):
            self.__courses.append(course)
        
        def add_course_section(self, course_section):
            self.__course_sections.append(course_section)
        
        def add_student(self, student):
            self.__students.append(student)
        
        def add_staff(self, staff):
            self.__staff.append(staff)
        
        def add_registration(self, registration):
            self.__registrations.append(registration)
        
        def add_grade(self, grade):
            self.__grades.append(grade)
        
        def register(self, registration, semester):
            if registration.is_provide_prereqs():
                if registration.getCourses().is_enough_seat_limit():
                    if registration.getStudent().get_transcript().GPA_checked():
                        if registration.getStudent().get_transcript().is_course_not_passed_before(registration.getCourses()):
                            if registration.is_semester_correct():
                                print(registration.getStudent().get_student_id()+" registered "+registration.getCourses().get_course_id()+" successfully.")
                                registration.getStudent().update_schedule(registration.getCourses())
                            else:
                               log = Log()
                               log.logging_error("SemesterError", semester)
                               print("Course ID: "+registration.getCourses().get_course_id()+" --> Semester Error")
                               logging.basicConfig(filename='example.log', level=logging.DEBUG)
                               logging.error('%s Semester Error',registration.getCourses().get_course_id())
                        else:
                            print("Course ID: "+registration.getCourses().get_course_id()+" --> Course is already passed")
                            log = Log()
                            log.logging_error("AlreadyPassedError", semester)
                            logging.basicConfig(filename='example.log', level=logging.DEBUG)
                            logging.error('%s AlreadyPassed Error',registration.getCourses().get_course_id())
                    else:
                        print("Course ID: "+registration.getCourses().get_course_id()+" --> GPA Error")
                        log = Log()
                        log.logging_error("GPAError", semester)
                        logging.basicConfig(filename='example.log', level=logging.DEBUG)
                        logging.error('%s GPA Error',registration.getCourses().get_course_id())
                else:
                    print("Course ID: "+registration.getCourses().get_course_id()+" --> No more seat limit")
                    log = Log()
                    log.logging_error("SeatLimitError", semester)
                    logging.basicConfig(filename='example.log', level=logging.DEBUG)
                    logging.error('%s Seatlimit Error',registration.getCourses().get_course_id())
            else:
                print("Course ID: "+registration.getCourses().get_course_id()+" --> Prereq Error")
                log = Log()
                log.logging_error("PrereqError", semester)
                logging.basicConfig(filename='example.log', level=logging.DEBUG)
                logging.error('%s Prereq Error',registration.getCourses().get_course_id())
        
        def afterReg(self,std: Student):
            student_schedule = std.get_transcript().get_schedule()
          #  schedule = [course for course in student_schedule]
            
            
            advisor = Advisor()
            advisor.approve_registration(std)
            if std.get_status():
                for course_id in student_schedule:
                    print("course in schedule: "+course_id)
                    course = Course(course_id)
                    course.update_seat_limit(course_id)
                std.update_status()
            else:
                log = Log()
                log.logging_error("NotApproved", std.getSemester())
                logging.basicConfig(filename='example.log', level=logging.DEBUG)
                logging.error('%s s schedule is Not Approved',std.get_student_id())
                print("Advisor did not approved schedule!")
