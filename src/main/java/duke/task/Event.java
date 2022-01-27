package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class Event extends Task {
    private static final char EVENT_SYMBOL = 'E';
    private LocalDate eventDate;
    private LocalTime eventTime;

    public Event() {
        super();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        this.eventDate = LocalDate.parse("2020-12-12"); // just get default date
        this.eventTime = LocalTime.parse("2359", timeFormatter);
    }

    public Event(String taskDescription, LocalDate eventDate, LocalTime eventTime) {
        super(taskDescription);

        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    @Override
    public String saveFileFormat() {
        return EVENT_SYMBOL + "|" + this.isDone + "|" + taskDescription + "|" + this.eventDate.format(
                DateTimeFormatter.ofPattern("MMM d yyyy")) + "|" + this.eventTime.format(
                DateTimeFormatter.ofPattern("hh:mm a")) + "\n";
    }

    @Override
    public void extractFileData(String data) {
        StringTokenizer st = new StringTokenizer(data, "|");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        st.nextToken(); // remove the type symbol
        isDone = Boolean.parseBoolean(st.nextToken());
        taskDescription = st.nextToken();
        eventDate = LocalDate.parse(st.nextToken(), dateFormatter);
        eventTime = LocalTime.parse(st.nextToken(), timeFormatter);
    }

    @Override
    public String toString() {
        return "[" + EVENT_SYMBOL + "]" + super.toString() + " (at: " + this.eventDate.format(
                DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.eventTime.format(
                DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}
