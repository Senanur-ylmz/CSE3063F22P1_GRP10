import json
import os
import random
from Course import Course
from CourseRegistrationSystem import CourseRegistrationSystem
from Registration import Registration

from Student import Student


class RandomStudentAccSemester(object):
    def __init__(self,number,semester):
        self.__number=number
        self.__semester = semester

    def getSemester(self):
        return self.__semester

    def getNumber(self):
        return self.__number

    def generate_randomStudent(self, semester: str, number: int):
        f_names = ["Zeynep", "Sena","Hazal","Bihter","Nilufer","Sule", "Beyza","Safa","Esref","Emre","Nur","Yagmur","Betul","Merve","Irem"]
        l_names = ["Destan","Yilmaz","Boylan","Akdem","Koca","Cabuk","San","Yildirim","Bakirci","Uslu","Ersan","Kalem","Keskin","Tunc","Okumus"]

        student_number = number
        ids = []
        
        for i in range(int(student_number)):
            student_info = {}

            # Random student ID
            random_id = "150"
            for x in range(6):
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
                register_to_passed_courses = Registration(std, c)
                for_adding_passed_course = CourseRegistrationSystem()
                for_adding_passed_course.register(register_to_passed_courses, semester)

                with open(f"students/{std.get_student_id()}.json") as f:
                    jo = json.load(f)
                if( bool(jo["Schedule"]) & (jo["Schedule"] not in jo["PassedCourses"])):
                    jo["PassedCourses"].append(jo["Schedule"][0])

                with open(f"students/{std.get_student_id()}.json", "w") as f:
                    json.dump(jo, f)

                with open(f"students/{std.get_student_id()}.json") as f:
                    jo = json.load(f)
                    del jo["Schedule"]
                    sch = []
                    jo["Schedule"] = sch
                    with open(f"students/{std.get_student_id()}.json", "w") as f:
                        json.dump(jo, f)
