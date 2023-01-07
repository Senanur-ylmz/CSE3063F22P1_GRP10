import random
from Student import Student
class Advisor(Person,object):

    def approve_registration(self, std:Student):
        std.set_status (self.get_random_boolean(70))
    
    def get_random_boolean(self, probability):
        random_value = random.random() * 100  # 0.0 to 99.9
        return random_value <= probability
