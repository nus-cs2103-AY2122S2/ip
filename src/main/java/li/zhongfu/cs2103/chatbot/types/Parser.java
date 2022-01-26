package li.zhongfu.cs2103.chatbot.types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .appendPattern("[yyyy-MM-dd[ HH:mm[:ss]]]")
            .appendPattern("[MMM d yyyy[ HH:mm[:ss]]]")
            .appendPattern("[d MMM yyyy[ HH:mm[:ss]]]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

    private BufferedReader br;
    private DateTimeFormatter dtFormatter;

    public Parser(InputStream stream, DateTimeFormatter dateTimeFormatter) {
        this.br = new BufferedReader(new InputStreamReader(stream));
        this.dtFormatter = dateTimeFormatter;
    }

    public Parser(InputStream stream) {
        this(stream, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * Parses a string in the form {@code positional argument /arg1 foo bar /arg2 baz bax /arg3}
     * and returns a {@code Map<String, String>} containing the parsed arguments, e.g.:
     * 
     * <pre>{
     *      "": "positional argument",
     *      "arg1": "foo bar",
     *      "arg2": "baz bax",
     *      "arg3": ""
     * }</pre>
     * 
     * Empty argument names will be dropped; other duplicate argument names will have the value of
     * the last occurrence of the argument.
     * 
     * @param args a string containing unparsed arguments
     * @return a Map containing parsed arguments
     */
    private static Map<String, String> parseArgString(String argString) {
        Map<String, String> args = new HashMap<>();
        String[] parts = argString.split("(^|\\s+)/");
        args.put("", parts[0]);

        for (int i = 1; i < parts.length; i++) {
            String[] arg = parts[i].split("\\s+", 2);
            if ("".equals(arg[0])) {
                continue;
            }
            args.put(arg[0], arg.length == 2 ? arg[1].strip(): "");
        }

        return args;
    }

    public ParserResult parseNext() throws IOException {
        String input = this.br.readLine();
        String[] parts = input.split("\\s+", 2); // split into command and args
        String cmd = parts[0];
        if (parts.length == 1) { // only command
            return new ParserResult(cmd, new HashMap<>());
        } else { // length is 2
            Map<String, String> args = parseArgString(parts[1]);
            return new ParserResult(cmd, args);
        }
    }

    // er, probably not the best place for this, but whatever
    public LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeString, this.dtFormatter);
    }
}
