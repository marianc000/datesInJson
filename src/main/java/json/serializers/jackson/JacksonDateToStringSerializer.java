package json.serializers.jackson;

import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import static dates.sql.Constants.DATE_PATTERN;
 
 
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JacksonDateToStringSerializer extends StdSerializer<Date> {

    static DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public JacksonDateToStringSerializer() {
        super(Date.class);
    }
 
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null) {
            gen.writeString(LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault()).format(format));
        } else {
            gen.writeNull();
        }
    }
}
