import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter dateOut = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter timeOut = DateTimeFormatter.ofPattern("hh:mm a");
    protected LocalDate d;
    protected LocalTime t;
    private final boolean hasDate;
    private final boolean hasTime;

    public Event(String description, LocalDate d) {
        super(description);
        this.d = d;
        hasDate = true;
        hasTime = false;
    }

    public Event(String description, LocalTime t) {
        super(description);
        this.t = t;
        hasDate = false;
        hasTime = true;
    }

    public Event(String description, LocalDate d, LocalTime t) {
        super(description);
        this.d = d;
        this.t = t;
        hasDate = true;
        hasTime = true;
    }

    private String getDate() {
        return d.format(dateOut);
    }

    private String getTime() {
        return t.format(timeOut);
    }

    private String getDateTime() {
        return d.format(dateOut) + " " + t.format(timeOut);
    }

    @Override
    public boolean isHasDate() {
        return hasDate;
    }

    @Override
    public boolean isHasTime() {
        return hasTime;
    }

    @Override
    public String getAppendData() {
        return "E | " + (super.isDone ? "1" : "0") + " | " + description + " | " +
                (hasDate ? d.toString() : "0") + " | " +
                (hasTime ? t.toString() : "0");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                (hasDate && hasTime
                        ? getDateTime()
                        : hasDate
                        ? getDate()
                        : getTime()) + ")";
    }
}
