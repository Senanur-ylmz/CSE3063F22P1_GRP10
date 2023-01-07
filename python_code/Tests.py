import unittest
from Course import Course
from Student import Student
class Tests(unittest.TestCase):

    def test_courseid(self):
        self.__course_object=Course('courseID')
        return self.assertTrue(self.__course_object.get_course_id()==('courseID'))
    
    def test_student(self):
        self.__student_object=Student('studentID')
        self.assertTrue(self.__student_object.get_student_id()=='studentID')


if __name__ == '__main__':
    unittest.main()
