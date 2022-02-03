import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Deadline which is a subclass of Task
 * Includes a dueDate attribute. Overrides toString() from Task
 */
public class Deadline extends Task {
    public LocalDate dueDate;
    public LocalTime dueTime;
    public Deadline(String name, String date) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = null;
    }

    public Deadline(String name, String date, String time) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = LocalTime.parse(time);
    }

    /**
     * @override
     * @return String of Deadline task, eg [D][X] Deadline (by:XX) vs [D][✓] Deadline (by;XX)
     */

    public String toString() {
        String dueDateAndTime = (this.dueTime == null) ? dateConverterToString(this.dueDate) : dateConverterToString(this.dueDate) + " " + timeConverterToString(this.dueTime);
        return "[D]" + super.toString() + " (by:" + dueDateAndTime + ")"; }
}