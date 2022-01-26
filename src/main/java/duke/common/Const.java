package duke.common;

import java.time.format.DateTimeFormatter;

public final class Const {
    private Const() {
    }

    public static final String INPUT_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    public static final DateTimeFormatter IN_TIME_FORMATTER = DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT);
    public static final String OUTPUT_DATE_TIME_FORMAT = "MMM d yyyy HH:mm";
    public static final DateTimeFormatter OUT_TIME_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT);
}
