import json

class Log:
    
# Creates a new log file for the given semester.
    def create_logFile(semester):
        json_object = {
            "SemesterError": 0,
            "GPAError": 0,
            "PrereqError": 0,
            "SeatLimitError": 0,
            "AlreadyPassedError": 0
        }
        with open(f"errorLog_{semester}.json", "w") as file:
            json.dump(json_object, file)
# Logs an error in the log file for the given semester.
    def logging_error(self, key, semester):
        with open(f"errorLog_{semester}.json", "r") as file:
            json_object = json.load(file)
        value = json_object[key]
        new_value = value + 1
        json_object[key] = new_value
        
        with open(f"errorLog_{semester}.json", "w") as file:
            json.dump(json_object, file)
# Writes the log file for the given semester to a .txt file.
    def write_txt(self, semester):
        with open(f"errorLog_{semester}.json", "r") as file:
            json_string = json.dumps(json.load(file))
        
        with open(f"errorLog_{semester}.txt", "w") as file:
            file.write(json_string)
