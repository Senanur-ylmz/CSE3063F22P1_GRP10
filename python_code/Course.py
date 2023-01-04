import json
import os
import random

class Course:
    def __init__(self, course_id):
        self.__course_id = course_id
        try:
            with open(f'courses/{self.__course_id}.json') as f:
             data = json.load(f)
            self.__course_name = data['CourseName']
            self.__prerequisites = data['Prereq']
            self.__seat_limit = data['Seatlimit']
            self.__semester = data['CourseType'][0]
        except FileNotFoundError:
                print ("Not found course file!")
      
    
    
    def get_course_id(self):
        return self.__course_id
    
    """   def get_course_name(self):
        return self.course_name
 """
    
    """
    def set_course_name(self, course_name):
        self.course_name = course_name """
    
    def get_prerequisites(self):
        return self.__prerequisites
        
    
    
    """def set_prerequisites(self, prerequisites):
        self._prerequisites = prerequisites"""
    
    @property
    def seat_limit(self):
        return self.__seat_limit
    
    @seat_limit.setter
    def seat_limit(self, seat_limit):
        self.__seat_limit = seat_limit
    
    
    def getSemester(self):
        return self.__semester
    
   
    def setSemester(self, semester):
        self.__semester = semester
    
    def is_enough_seat_limit(self):
        return int(self.__seat_limit) > 0
    
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