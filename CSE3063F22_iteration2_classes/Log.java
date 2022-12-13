import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Log {
private String key;

public Log(String key){
    this.key=key;
}

public String getKey(){
    return key;
}

    public void logging_error(String semester) throws IOException, ParseException{
       
            Object obj = new JSONParser().parse(new FileReader("errorLog_"+semester+".json"));
            JSONObject jo = (JSONObject) obj;
            String value = String.valueOf(jo.get(getKey()));
           
            int new_value = Integer.parseInt(value) + 1;
           
            jo.replace(getKey(), new_value);
          
            FileWriter writer = new FileWriter(("errorLog_"+semester+".json"), false); //overwrites the content of file
            writer.write(jo.toString());
            writer.close();
         
            
        
    }
    
}
