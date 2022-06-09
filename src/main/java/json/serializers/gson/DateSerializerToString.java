package json.serializers.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import static dates.sql.Constants.DATE_PATTERN;
 
import java.lang.reflect.Type;
 
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateSerializerToString implements JsonSerializer< Date> {

    static DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @Override
    public JsonElement serialize(Date value, Type typeOfSrc, JsonSerializationContext context) {

        return value == null ? null : new JsonPrimitive(LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault()).format(format));
    }
}
