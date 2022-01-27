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
 * The task type Deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description of the deadline
     * @param by          the time of the deadline
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets time of the deadline.
     *
     * @return the time of the deadline
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        LocalDate localDate = by.toLocalDate();
        LocalTime localTime = by.toLocalTime();
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
        return "[D]" + super.toString() + "(by: " + displayDate + displayTime + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            Deadline deadline = (Deadline) obj;
            return (deadline.getDescription().equals(this.getDescription()) && deadline.by.equals(this.by));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getDescription(), by);
    }
}
