package json.serializers.jackson;

import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
 
import java.util.Date;

public class JacksonDateToMsSerializer extends StdSerializer<Date> {

    public JacksonDateToMsSerializer() {
        super(Date.class);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null) {
            gen.writeNumber(value.getTime());
        } else {
            gen.writeNull();
        }
    }
}
