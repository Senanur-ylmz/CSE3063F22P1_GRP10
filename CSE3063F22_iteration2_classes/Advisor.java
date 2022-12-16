import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Advisor {
    
    public void ApproveRegistration(Student std) throws FileNotFoundException, IOException, ParseException{
        std.setStatus(getRandomBoolean(70));
    };

    public boolean getRandomBoolean(float probability) {
        double randomValue = Math.random()*100;  //0.0 to 99.9
        return randomValue <= probability;
    };
}

