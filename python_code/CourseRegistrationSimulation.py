

import os
import random
import json
from pathlib import Path

from Log import Log
from RandomStudentAccSemester import RandomStudentAccSemester
from Student import Student
from Course import Course
from CourseRegistrationSystem import CourseRegistrationSystem
from Registration import Registration

class CourseRegistrationSimulation:
    def __init__(self, courseRegistrationSystem):
        self.__courseRegistrationSystem = courseRegistrationSystem

    @staticmethod
    def main():
        try:
         os.remove('example.log')
        except FileNotFoundError:
            print("File have not been created yet.")
            pass
        semester = input("Semester: ")
        semester = int(semester)
        number = input("Student number: ")
        # create log file
        Log.create_logFile(semester)
        # generate random students for given semester
        rand = RandomStudentAccSemester()
        rand.generate_randomStudent(semester, number)
        # simulate registration for students
        CourseRegistrationSimulation.registration_simulation(semester)

        CourseRegistrationSimulation.view_error()

    @staticmethod
    def registration_simulation(semester):
        # get student ids to register courses
        directory = Path("students/")
        students = list(directory.glob("*.json"))

        for student in students:
            student_id = student.stem
            print ("\nStudent ID (for simulation): "+student_id)
            # register random courses
            dir = Path("courses/")
            courses = list(dir.glob("*.json"))

            with open(student, "r") as f:
                std_json = json.load(f)
            std = Student(student_id)
            # determine number of courses to register for the student
            times = random.randint(1, int(std.getSemester()) * 6)
            
            for n in range(times):
                # select a random course
                course = random.choice(courses)
                course_id = course.stem
                c = Course(course_id)
                # register student for the course
                register = Registration(std, c)
                registration = CourseRegistrationSystem()
                registration.register(register, semester)
            # perform post-registration tasks
            after = CourseRegistrationSystem()
            after.afterReg(std)
        
    def view_error():
        GPAError=0
           SemesterError=0
           SeatlimitError=0
           PrereqError=0
           AlreadyPassedError=0
           NotApproved=0
           with open ("example.log") as f:
            for line in f.readlines():
                if('GPA' in line):
                    GPAError+=1
                elif ('Semester' in line):
                    SemesterError+=1
                elif ('Seatlimit' in line):
                    SeatlimitError+=1
                elif ('Prereq' in line):
                    PrereqError+=1
                elif ('AlreadyPassed' in line):
                    AlreadyPassedError+=1
                elif ('Not Approved' in line):
                    NotApproved+=1
            
           print("\nTotal GPA Error in Registration Simulation: "+ str(GPAError))
           print("Total Semester Error in Registration Simulation: "+ str(SemesterError))
           print("Total Seatlimit Error in Registration Simulation: "+ str(SeatlimitError))
           print("Total Prerequisite Error in Registration Simulation: "+ str(PrereqError))
           print("Total Already Passed Error in Registration Simulation: "+ str(AlreadyPassedError))
           print(str(NotApproved)+" schedule did not approved by advisor.")




simulation = CourseRegistrationSimulation
simulation.main()







        
