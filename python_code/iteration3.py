##############################################################################
class Person:
    def init(self, name:str, address:str):
        self.name = name
        self.address = address
    def get_name(self) -> str:
        return self.name

    def set_name(self, name:str) -> None:
        self.name = name

    def get_address(self) -> str:
        return self.address

    def set_address(self, address:str) -> None:
        self.address = address

###############################################################################################

import json
import os
import random

class Course:
    def __init__(self, course_id):
        self.course_id = course_id
        try:
            with open(f'courses/{self.course_id}.json') as f:
             data = json.load(f)
             self.course_name = data['CourseName']
            self.prerequisites = data['Prereq']
            self.seat_limit = data['Seatlimit']
            self.semester = data['CourseType'][0]
        except FileNotFoundError:
                print ("Not found file error occured!")
      
    
    
    def get_course_id(self):
        return self.course_id
    
    
    def set_course_id(self, course_id):
        self.course_id = course_id
    
    
    def get_course_name(self):
        return self.course_name
    
   
    def set_course_name(self, course_name):
        self.course_name = course_name
    
    def get_prerequisites(self):
        return self.prerequisites
        
    
    
    def set_prerequisites(self, prerequisites):
        self._prerequisites = prerequisites
    
    @property
    def seat_limit(self):
        return self._seat_limit
    
    @seat_limit.setter
    def seat_limit(self, seat_limit):
        self._seat_limit = seat_limit
    
    
    def getSemester(self):
        return self.semester
    
   
    def setSemester(self, semester):
        self.semester = semester
    
    def is_enough_seat_limit(self):
        return int(self.seat_limit) > 0
    
    def update_seat_limit(self, course_id):
        try:
            with open(f'courses/{course_id}.json') as f:
                data = json.load(f)
            data['Seatlimit'] = str(int(data['Seatlimit'])-1)
            with open(f'courses/{course_id}.json', 'w') as f:
                json.dump(data, f)
            print("Seat limit of "+course_id+" is updated succesfully.")
        except FileNotFoundError:
            print ("not found file error occured")
    
    ###########################################################################33


class Transcript(object):
    def __init__(self, student_id):
        self.student_id = student_id
        with open(f"students/{self.student_id}.json", "r") as f:
            data = json.load(f)
        
        self.passed_courses = []
        for course in data["PassedCourses"]:
            self.passed_courses.append(Course(course).get_course_id())

        self.Schedule = []
        for course in data["Schedule"]:
            self.Schedule.append(Course(course).get_course_id())
            print("Eklenen: "+Course(course).get_course_id())

        self.grades = []

    def get_student_id(self):
        return self.studentId

    def set_student_id(self, studentId):
        self.studentId = studentId

    def get_passed_courses(self):
        return self.passed_courses

    def set_passed_courses(self, courses):
        self.passedCourses = courses

    def get_schedule(self):
        return self.Schedule

    def set_schedule(self, courses):
        self.Schedule = courses

    def get_grades(self):
        return self.grades

    def set_grades(self, grades):
        self.grades = grades

    def add_course_to_schedule(self, course : Course):
        self.Schedule.append(course.get_course_id())

    def add_grade(self, grade):
        self.grades.add(grade)

    def GPA_checked(self):
        with open(f"students/{self.student_id}.json") as file:
            data = json.load(file)
        gpa = data["GPA"]
        if gpa < 2.0:
            return False
        else:
            return True

    def is_course_not_passed_before____(self, course):
        student_passed = self.Schedule
        student_transcript = self.passed_courses
        if not student_transcript:
            return True
        else:
            transcript = [str(c) for c in student_transcript]
            for t in transcript:
                if t == course.course_id:
                    return False
            passed_before = [str(c) for c in student_passed]
            for p in passed_before:
                if p == course.course_id:
                    return False
            return True
    
    def is_course_not_passed_before(self,course:Course):
        if ((course.get_course_id() in self.get_passed_courses()) | (course.get_course_id() in self.get_schedule())):
            return False
        else:
            return True

        
##################################################################################################
        import json

class Student(object):
    def __init__(self, student_id: str):
       # super().init(name, address)
        self.student_id = student_id
        self.transcript = Transcript(student_id)
        self.schedule_status=False

        with open(f"students/{student_id}.json", "r") as f:
            data = json.load(f)
            self.semester = data["SSemester"]

    def set_status(self, status: bool):
        self.schedule_status = status

    def get_status(self):
        return bool(self.schedule_status)

    def get_student_id(self) -> str:
        return self.student_id

    def set_student_id(self, student_id: str):
        self.student_id = student_id

    def get_transcript(self) -> Transcript:
        return self.transcript

    def set_transcript(self, transcript: Transcript):
        self.transcript = transcript

    def getSemester(self) -> str:
        return self.semester

    def set_semester(self, semester: str):
        self.semester = semester

    def read_name(self):
        with open(f"students/{self.student_id}.json", "r") as f:
            data = json.load(f)
            first_name = data["FirstName"]
            last_name = data["LastName"]

        print(f"Full Name is: {first_name} {last_name}")

    def update_status(self):
        with open(f"students/{self.student_id}.json", "r") as f:
            data = json.load(f)

        data.pop("ScheduleStatus")
        data["ScheduleStatus"] = "Approved"

        with open(f"students/{self.student_id}.json", "w") as f:
            json.dump(data, f)

    def update_schedule(self, course: Course):
        self.transcript.add_course_to_schedule(course)

        with open(f"students/{self.student_id}.json", "r") as f:
            data = json.load(f)
        
        schedule = data["Schedule"]
        schedule.append(course.get_course_id())
        data["Schedule"]=schedule

        with open(f"students/{self.student_id}.json", "w") as f:
            json.dump(data, f)
######################################################################################################
class Advisor:

    def approve_registration(self, std:Student):
        std.set_status (self.get_random_boolean(70))
    
    def get_random_boolean(self, probability):
        random_value = random.random() * 100  # 0.0 to 99.9
        return random_value <= probability
##########################################################################################################3

class CourseRegistrationSystem:
    
        def __init__(self):
            self.courses = []
            self.course_sections = []
            self.students = []
            self.staff = []
            self.registrations = []
            self.grades = []
        
        def get_courses(self):
            return self.courses
        
        def set_courses(self, courses):
            self.courses = courses
        
        def get_course_sections(self):
            return self.course_sections
        
        def set_course_sections(self, course_sections):
            self.course_sections = course_sections
        
        def get_students(self):
            return self.students
        
        def set_students(self, students):
            self.students = students
        
        def get_staff(self):
            return self.staff
        
        def set_staff(self, staff):
            self.staff = staff
        
        def get_registrations(self):
            return self.registrations
        
        #def set_registrations(self, registrations):
        def get_grades(self):
            return self.grades
        
        def set_grades(self, grades):
            self.grades = grades
        
        def add_course(self, course):
            self.courses.append(course)
        
        def add_course_section(self, course_section):
            self.course_sections.append(course_section)
        
        def add_student(self, student):
            self.students.append(student)
        
        def add_staff(self, staff):
            self.staff.append(staff)
        
        def add_registration(self, registration):
            self.registrations.append(registration)
        
        def add_grade(self, grade):
            self.grades.append(grade)
        
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
                        else:
                            print("Course ID: "+registration.getCourses().get_course_id()+" --> Course is already passed")
                            log = Log()
                            log.logging_error("AlreadyPassedError", semester)
                    else:
                        print("Course ID: "+registration.getCourses().get_course_id()+" --> GPA Error")
                        log = Log()
                        log.logging_error("GPAError", semester)
                else:
                    print("Course ID: "+registration.getCourses().get_course_id()+" --> No more seat limit")
                    log = Log()
                    log.logging_error("SeatLimitError", semester)
            else:
                print("Course ID: "+registration.getCourses().get_course_id()+" --> Prereq Error")
                log = Log()
                log.logging_error("PrereqError", semester)
        
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
                print("Advisor did not approved schedule!")

#########################################################################################3

import json

class Log:
    def init(self):
        self.key = None
    
    @staticmethod
    def create_logFile(semester):
        json_object = {
            "SemesterError": 0,
            "GPAError": 0,
            "PrereqError": 0,
            "SeatLimitError": 0,
            "AlreadyPassedError": 0,
            "NotApproved": 0
        }
        with open(f"errorLog_{semester}.json", "w") as file:
            json.dump(json_object, file)

    def logging_error(self, key, semester):
        with open(f"errorLog_{semester}.json", "r") as file:
            json_object = json.load(file)
        value = json_object[key]
        new_value = value + 1
        json_object[key] = new_value
        
        with open(f"errorLog_{semester}.json", "w") as file:
            json.dump(json_object, file)

    def write_txt(self, semester):
        with open(f"errorLog_{semester}.json", "r") as file:
            json_string = json.dumps(json.load(file))
        
        with open(f"errorLog_{semester}.txt", "w") as file:
            file.write(json_string)

##############################################################################################33


class RandomStudentAccSemester:
    semester = 0
    number = 0
    def __init__(self):
        pass

    def getSemester(self):
        return self.semester

    def getNumber(self):
        return self.number

    def generate_randomStudent(self, semester: str, number: int):
        f_names = ["Zeynep", "Sena","Hazal","Bihter","Nilufer","Sule", "Beyza","Safa","Esref","Emre","Nur","Yagmur","Betul","Merve","Irem"]
        l_names = ["Destan","Yilmaz","Boylan","Akdem","Koca","Cabuk","San","Yildirim","Bakirci","Uslu","Ersan","Kalem","Keskin","Tunc","Okumus"]

        student_number = number
        ids = []
        
        for i in range(int(student_number)):
            student_info = {}

            # Random student ID
            random_id = "150120"
            for x in range(3):
                random_id += str(random.randint(1, 9))
            
            if random_id in ids:
                print("This ID is used!")
                continue
            ids.append(random_id)
            
            student_info["StudentID"] = random_id
            print ("\n")
            print("Student ID (for Creating Passed Courses): "+ random_id)
            
            # Random first name
            student_info["FirstName"] = random.choice(f_names)

            # Random last name
            student_info["LastName"] = random.choice(l_names)

            # Random semester
            student_info["SSemester"] = semester

            # Random passed courses
            student_info["PassedCourses"] = []

            # Random schedule
            student_info["Schedule"] = []

            # Random GPA
            student_info["GPA"] = round(random.uniform(1, 4), 2)

            # Schedule status
            student_info["ScheduleStatus"] = "NotApproved"

            with open(f"students/{random_id}.json", "w") as file:
                json.dump(student_info, file)
                
            # Random passed courses
            courses = [f for f in os.listdir("courses/") if os.path.isfile(f"courses/{f}")]
            times = random.randint(1, int(semester) * 6)

            std = Student(random_id)

            for n in range(times):
                course = random.choice(courses)
                course_id = str(course)
                course_id = course.replace(".json","")
                c = Course(course_id)
                print("Course id:", course_id)
                register_to_passed_courses = Registration(std, c)
                for_adding_passed_course = CourseRegistrationSystem()
                for_adding_passed_course.register(register_to_passed_courses, semester)

                with open(f"students/{std.student_id}.json") as f:
                    jo = json.load(f)
                if( bool(jo["Schedule"]) & (jo["Schedule"] not in jo["PassedCourses"])):
                    jo["PassedCourses"].append(jo["Schedule"][0])

                with open(f"students/{std.student_id}.json", "w") as f:
                    json.dump(jo, f)

                with open(f"students/{std.student_id}.json") as f:
                    jo = json.load(f)
                    del jo["Schedule"]
                    sch = []
                    jo["Schedule"] = sch
                    with open(f"students/{std.student_id}.json", "w") as f:
                        json.dump(jo, f)

################################################################################################

class Registration:
    def __init__(self, student: 'Student', courses: 'Course'):
        self.student = student
        self.courses = courses
    
    def getStudent(self) -> 'Student':
        return self.student
    
    def set_student(self, student: 'Student'):
        self.student = student
    
    def getCourses(self) -> 'Course':
        return self.courses
    
    def set_courses(self, courses: 'Course'):
        self.courses = courses
    
    def add_course(self, course: 'Course'):
        pass
    
    def remove_course(self, course: 'Course'):
        pass
    
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

    #######################################################################################


#############################################################################################

import random
import json
from pathlib import Path

class CourseRegistrationSimulation:
    def __init__(self, courseRegistrationSystem):
        self.courseRegistrationSystem = courseRegistrationSystem

    @staticmethod
    def main():
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

    @staticmethod
    def registration_simulation(semester):
        # get student ids to register courses
        directory = Path("students/")
        students = list(directory.glob("*.json"))

        for student in students:
            student_id = student.stem
            print ("\nSudent ID (for simulation): "+student_id)
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


simulation = CourseRegistrationSimulation
simulation.main()







        
