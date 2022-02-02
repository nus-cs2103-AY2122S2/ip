package li.zhongfu.cs2103.chatbot.types.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("[uuuu-MM-dd[ HH:mm[:ss]]['T'HH:mm[:ss]]]")
            .appendPattern("[MMM d uuuu[ HH:mm[:ss]]]")
            .appendPattern("[d MMM uuuu[ HH:mm[:ss]]]")
            .appendPattern("[MMMM d uuuu[ HH:mm[:ss]]]")
            .appendPattern("[d MMMM uuuu[ HH:mm[:ss]]]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);

    private DateTimeFormatter dtFormatter;

    public Parser(DateTimeFormatter dateTimeFormatter) {
        this.dtFormatter = dateTimeFormatter;
    }

    public Parser() {
        this(DEFAULT_DATE_TIME_FORMATTER);
    }

    // i'm literally just doing this because they said i had to
    // maybe one day it'll support different formats
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
     * @param argString a string containing unparsed arguments
     * @return a Map containing parsed arguments
     */
    public Map<String, String> parseArgString(String argString) {
        Map<String, String> args = new HashMap<>();
        String[] parts = argString.split("(^|\\s+)/");
        args.put("", parts[0].strip());

        for (int i = 1; i < parts.length; i++) {
            String[] arg = parts[i].split("\\s+", 2);
            if ("".equals(arg[0])) {
                continue;
            }
            args.put(arg[0], arg.length == 2 ? arg[1].strip() : "");
        }

        return args;
    }

    // er, probably not the best place for this, but whatever
    public LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeString, this.dtFormatter);
    }
}
