import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Log {
private String key;

public Log(){
  
}

public String getKey(){
    return key;
}
    public static void create_logFile(String semester) throws IOException{  
   /*The reason it is static is that we get an 
   error when we define the log in the 
   courseregistrationsimulation class. */
    JSONObject jsonObject = new JSONObject();
       int def = 0;
       jsonObject.put("SemesterError", def);
       jsonObject.put("GPAError", def);
       jsonObject.put("PrerequisiteError", def);
       jsonObject.put("SeatLimitError", def);
       FileWriter file = new FileWriter("errorLog_"+semester+".json");
       file.write(jsonObject.toJSONString());
       file.close();
}

    public void logging_error(String key, String semester) throws IOException, ParseException{
       
            Object obj = new JSONParser().parse(new FileReader("errorLog_"+semester+".json"));
            JSONObject jo = (JSONObject) obj;
            String value = String.valueOf(jo.get(getKey()));
           
            int new_value = Integer.parseInt(value) + 1;
           
            jo.remove(key);
            jo.put(key, new_value);
          
            FileWriter writer = new FileWriter(("errorLog_"+semester+".json"), false); //overwrites the content of file
            writer.write(jo.toString());
            writer.close();
         
            
        
    }
    
}
