package dates.sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import static json.StaticMappers.*;

public class MeasureMappers {

    static String PAD = "%-15s";
    static Date[][] array2d;

    public static void main(String[] args) throws JsonProcessingException, Exception {

        array2d = new Date[50_000][10];
        Arrays.stream(array2d).forEach(row
                -> Arrays.setAll(row, i -> new Timestamp(System.currentTimeMillis())));

        Map<String, ThrowingFunction> approaches = new LinkedHashMap<>() {
            {
                put("jackson", jackson::writeValueAsString );
                put("jacksonCustMs", jacksonCustMs::writeValueAsString );
                put("gsonCustMs", gsonCustMs::toJson );
                //
                put("jacksonStr",  jacksonStr::writeValueAsString );
                put("jacksonCustStr",  jacksonCustStr::writeValueAsString );
                //
                put("gsonStr",  gsonStr::toJson );
                put("gsonCustStr", gsonCustStr::toJson );
                put("gsonCustStr", gsonCustStr::toJson );
            }
        };
        // make sure the mappers use the expected date format
        ObjectWithDates obj = new ObjectWithDates();

        for (Entry<String, ThrowingFunction> e : approaches.entrySet()) {
            System.out.println(String.format(PAD, e.getKey()) + " "
                    + e.getValue().apply(obj));
        }

        // print headers 
        System.out.println();
        System.out.println(approaches.keySet().stream()
                .map(s -> String.format(PAD, s)).collect(Collectors.joining("")));
        
        // print measurements repeated 10 times
        for (int repeat = 0; repeat < 10; repeat++) {
            for (ThrowingFunction f : approaches.values()) {
                measure(f);
            }
            System.out.println();
        }
    }

    static void measure(ThrowingFunction f) throws Exception {
        long s = System.currentTimeMillis();
        f.apply(array2d);
        System.out.format(PAD, (System.currentTimeMillis() - s));
    }
}
