package dates.sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import static dates.sql.Constants.DATE_PATTERN;
 

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Formatters {

    static String PAD = "%-15s";
    
    static DateTimeFormatter dtfReused = DateTimeFormatter.ofPattern(DATE_PATTERN);
    static SimpleDateFormat sdfReused = new SimpleDateFormat(DATE_PATTERN);

    public static void main(String[] args) throws JsonProcessingException {

        var ts = new Timestamp(System.currentTimeMillis());
        // two formatter produce the same string 
        System.out.println(ts.toLocalDateTime().format(dtfReused) + "\n"
                + sdfReused.format(ts) + "\n");
        // print headers
        System.out.println(Stream.of("tsAsIs", "ts.getTime()", "dtfReused", "dtfNew", "sdfReused", "sdfNew")
                .map(s -> String.format(PAD, s)).collect(Collectors.joining("")));

        for (int c = 0; c < 10; c++) {
            measure(i -> ts);
            //
            measure(i -> ts.getTime());
            // 
            measure(i -> ts.toLocalDateTime().format(dtfReused));
            measure(i -> ts.toLocalDateTime().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
            // 
            measure(i -> sdfReused.format(ts));
            measure(i -> new SimpleDateFormat(DATE_PATTERN).format(ts));
            System.out.println();
        }
    }

    static void measure(IntFunction f) throws JsonProcessingException {
        long s = System.currentTimeMillis();
        List l = IntStream.range(0, 1_000_000).mapToObj(f).toList();
        System.out.format(PAD, (System.currentTimeMillis() - s));
    }
}
