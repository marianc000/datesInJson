package dates.sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import static json.StaticMappers.*;
 

public class Json {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectWithDates dates = new ObjectWithDates();

        System.out.println("byJackson: " + jackson.writeValueAsString(dates));
        System.out.println("byGson: " + gson.toJson(dates));
 
    }
}
