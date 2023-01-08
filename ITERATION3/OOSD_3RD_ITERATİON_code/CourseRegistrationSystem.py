from Log import Log
from Advisor import Advisor
from Student import Student
from Course import Course
from Registration import Registration
import logging

class CourseRegistrationSystem:
    
        def __init__(self):
            pass
        
  
          
        
        def register(self, registration:Registration, semester):
            if registration.is_provide_prereqs():
                if registration.getCourses().is_enough_seat_limit():
                    if registration.getStudent().get_transcript().GPA_checked():
                        if registration.getStudent().get_transcript().is_course_not_passed_before(registration.getCourses()):
                            if registration.is_semester_correct():
                                print("Course ID: "+registration.getCourses().get_course_id()+" --> "+registration.getStudent().get_student_id()+" registered"+registration.getCourses().get_name()+"successfully.")
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
                logging.basicConfig(filename='example.log', level=logging.DEBUG)
                logging.error('%s s schedule is Not Approved',std.get_student_id())
                print("Advisor did not approved schedule!")
