import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Event which is a subclass of Task
 * Overrides toString() from Task
 */
public class Event extends Task {
    public LocalDate dueDate;
    public LocalTime dueTime;
    public Event (String name, String date) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = null;
    }
    public Event (String name, String date, String time) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = LocalTime.parse(time);
    }

    /**
     * @override
     * @return String of Event task, eg: [E][X] Event (at:) vs [E] [✓] Event (at:)
     */
    public String toString() {
        String dueDateAndTime = (this.dueTime == null) ? dateConverterToString(this.dueDate) : dateConverterToString(this.dueDate) + " " + timeConverterToString(this.dueTime);
        return "[E]" + super.toString() + " (at:" + dueDateAndTime + ")"; }
}