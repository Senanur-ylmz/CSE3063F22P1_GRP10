import unittest
from Course import Course
from Student import Student
from Transcript import Transcript
from Registration import Registration
from RandomStudentAccSemester import RandomStudentAccSemester
from RandomStudentMix import RandomStudentMix
class Tests(unittest.TestCase):

    def test_courseid(self):
        self.__course_object=Course('courseID')
        return self.assertTrue(self.__course_object.get_course_id()==('courseID'))
    
    def test_student(self):
        self.__student_object=Student('studentID')
        return self.assertTrue(self.__student_object.get_student_id()=='studentID')

    def test_transcript(self):
        self.__transcript_object=Transcript('Studentid')
        return self.assertTrue(self.__transcript_object.get_student_id()=='Studentid')
    
    def test_RandomStudentAccSemester(self):
        self.__RandomStudentAccSemester=RandomStudentAccSemester('number','semester')
        return self.assertTrue(self.__RandomStudentAccSemester.getNumber()=='number')

    def test_RandomStudentMix(self):
        self.__RandomStudentMix=RandomStudentMix('number','semester')
        return self.assertTrue(self.__RandomStudentMix.getNumber()=='number')
    
   """ def test_registration(self):
        self.__registration_object=Registration('student')
        return self.assertTrue(self.__registration_object.getStudent()=='student')

    def test_registration(self):
        self.__registration_object=Registration('courses')
        return self.assertTrue(self.__registration_object.getCourses()=='courses')
   """



if __name__ == '__main__':
    unittest.main()
