

import os
import random
import json
from pathlib import Path

from Log import Log
from RandomStudentAccSemester import RandomStudentAccSemester
from RandomStudentMix import RandomStudentMix
from Student import Student
from Course import Course
from CourseRegistrationSystem import CourseRegistrationSystem
from Registration import Registration

class CourseRegistrationSimulation(CourseRegistrationSystem):
    def __init__(self, courseRegistrationSystem):
        self.__courseRegistrationSystem = courseRegistrationSystem

    def start_simulation():
        try:
            os.remove('example.log')
        except FileNotFoundError:
            print("Log file have not been created yet.")
            pass

        try:
            for course in os.listdir("courses/"):
                with open(f"courses/{course}") as file:
                    data=json.load(file)
                    data["Seatlimit"]=data["Quota"]
                with open(f'courses/{course}', 'w') as f:
                 json.dump(data, f)
        except KeyError:
            print(course+ " has Key Error!")

        semester = input("Semester: ")
        number = input("Student number: ")
        # create log file
        Log.create_logFile(semester)
        # generate random students for given semester
        if ((semester=="fall") | (semester=="spring")):
            semester=semester.lower()
            rand = RandomStudentMix(int(number),semester)
            rand.generate_randomStudent(int(number))
        else:
            semester = int(semester)
            rand = RandomStudentAccSemester()
            rand.generate_randomStudent(semester, number)
        # simulate registration for students
        CourseRegistrationSimulation.registration_simulation(semester)
        CourseRegistrationSimulation.view_error()


    def registration_simulation(semester):
     
        semester_array=[]
        if (semester=="fall"):
            semester_array = ["1","3","5","7","T","E","N","U","F"]
        elif(semester=="spring"):
            semester_array= ["2","4","6","8","T","E","N","U","F"]
        else:
            semester_array=["1","3","5","7","T","E","N","U","F","2","4","6","8"]


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
            std.get_name()
            # determine number of courses to register for the student
            times = random.randint(1, int(std.getSemester()) * 6)
            n=0
            while(n<times):
                # select a random course
                course = random.choice(courses)
                course_id = course.stem
                c = Course(course_id)
                # register student for the course
                #print("Course semester: "+c.getSemester())
                #print("semester: "+ str(semester_array))
                if (c.getSemester() in semester_array):
                    register = Registration(std, c)
                    registration = CourseRegistrationSystem()
                    registration.register(register, semester)
                    n+=1
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
           print(str(NotApproved)+" schedule did not approved by advisors.")




simulation = CourseRegistrationSimulation
simulation.start_simulation()







        
