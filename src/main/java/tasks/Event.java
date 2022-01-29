package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Event extends Task {
    String event;
    LocalDate time;

    DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern(
                    "" +
                    "[yyyy-MM-dd HH:mm:ss]" +
                    "[yyyy-MM-dd]" +
                    "[yyyy/MM/dd]" +
                    "[yyyy-MM-dd HH:mm a]"
            ));

    DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();


    public Event(String event, String time) throws DateTimeException {
        super(event);
        this.event = event;
        System.out.println(time);

        try {
            this.time = LocalDate.parse(time, dateTimeFormatter);
        } catch (DateTimeException de) {
            System.out.println("sorry, this is not a valid time");
        }
    }

    @Override
    public String toString() {
        String temp = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + "(at: " + temp + ")";
    }
}
