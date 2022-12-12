import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, java.text.ParseException{
     Student p =new Student("150122190");
     p.ReadName();

    Course c = new Course("ECON2003");
    System.out.println(c.getPrerequisites());

    Registration r = new Registration(p, c);
    CourseRegistrationSystem regSys = new CourseRegistrationSystem();
    regSys.register(r);
   //Course c1 = new Course("ATA122");
   // System.out.println("yeni course'un  seat limiti: "+c1.getSeatLimit());

    
    }
}
