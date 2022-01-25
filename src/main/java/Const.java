import java.time.format.DateTimeFormatter;

public final class Const {
    private Const() {}

    protected static final String INPUT_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    protected static final String OUTPUT_DATE_TIME_FORMAT = "MMM d yyyy HH:mm";
    protected static final DateTimeFormatter IN_TIME_FORMATTER = DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT);
    protected static final DateTimeFormatter OUT_TIME_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT);
}
