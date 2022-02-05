package duke.task;
import duke.duke.Duke;
import duke.ui.Parser;
import duke.ui.DukeException;
import duke.ui.InputHandler;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
/**
 * Represents a Deadline which is a subclass of Task
 * Includes a dueDate attribute. Overrides toString() from Task
 */
public class Deadline extends Task {
    public LocalDate dueDate;
    public LocalTime dueTime;

    /**
     * Constructor for Deadline with date
     * @param name Name of Deadline
     * @param date Date of deadline in yyyy-mm-dd format
     * @throws DateTimeParseException If date time is in wrong format
     */
    public Deadline(String name, String date) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = null;
    }

    /**
     * Constructor for Deadline with date and time
     * @param name Name of Deadline
     * @param date Date of deadline in yyyy-mm-dd format
     * @param time Time of deadline in hh:mm format
     * @throws DateTimeParseException If date time is in wrong format
     */
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