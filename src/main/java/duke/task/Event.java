package duke.task;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Objects;

import duke.utils.Ui;

/**
 * The task type Event.
 */
public class Event extends Task {
    private final LocalDateTime at;

    /**
     * Instantiates a new Event.
     *
     * @param description the description of the event
     * @param at          the time of the event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets the time of the event.
     *
     * @return the time of the event
     */
    public LocalDateTime getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        LocalDate localDate = at.toLocalDate();
        LocalTime localTime = at.toLocalTime();
        String displayDate = String.format("%s, %s %s, %s",
                localDate.getDayOfWeek().toString().substring(0, 1).toUpperCase()
                        + localDate.getDayOfWeek().toString().substring(1).toLowerCase(),
                localDate.getMonth().toString().substring(0, 1).toUpperCase()
                        + localDate.getMonth().toString().substring(1).toLowerCase(),
                localDate.getDayOfMonth(),
                localDate.getYear());
        String displayTime = "";
        if (localTime != LocalTime.MAX) {
            /* has time */
            DateFormat inputFormat = new SimpleDateFormat("HHmm");
            DateFormat outputFormat = new SimpleDateFormat("hhmma", Locale.ENGLISH);
            try {
                DecimalFormat timeFormatter = new DecimalFormat("00");
                String time = timeFormatter.format(localTime.getHour()) + timeFormatter.format(localTime.getMinute());
                displayTime = " " + outputFormat.format(inputFormat.parse(time));
            } catch (ParseException e) {
                new Ui().showErrorMessage(e.getMessage());
            }
        }
        return "[E]" + super.toString() + "(at: " + displayDate + displayTime + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            Event event = (Event) obj;
            return (event.getDescription().equals(this.getDescription()) && event.at.equals(this.at));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getDescription(), at);
    }
}
