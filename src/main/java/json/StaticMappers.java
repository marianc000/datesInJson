package json;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static dates.sql.Constants.DATE_PATTERN;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.TimeZone;
import json.serializers.gson.DateSerializerToMs;
import json.serializers.gson.DateSerializerToString;
import json.serializers.jackson.JacksonDateToMsSerializer;
import json.serializers.jackson.JacksonDateToStringSerializer;

public class StaticMappers {

    public static ObjectMapper jackson = new ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

    public static ObjectMapper jacksonCustMs = new ObjectMapper()
            .registerModule(new SimpleModule() {
                {
                    addSerializer(Date.class, new JacksonDateToMsSerializer());
                }
            })
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

    public static Gson gsonCustMs = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateSerializerToMs())
            //  .setPrettyPrinting()
            .create();

    public static ObjectMapper jacksonStr = new ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
            .setDateFormat(new SimpleDateFormat(DATE_PATTERN));

    public static ObjectMapper jacksonStrDef = new ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setTimeZone(TimeZone.getDefault());

    public static ObjectMapper jacksonCustStr = new ObjectMapper()
            .registerModule(new SimpleModule() {
                {
                    addSerializer(Date.class, new JacksonDateToStringSerializer());
                }
            })
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

    public static Gson gson = new Gson();

    public static Gson gsonStr = new GsonBuilder()
            //  .setPrettyPrinting()
            .setDateFormat(DATE_PATTERN)
            .create();

    public static Gson gsonCustStr = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateSerializerToString())
            //  .setPrettyPrinting()
            .create();
}
