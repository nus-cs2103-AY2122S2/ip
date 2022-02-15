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
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a Task. Contains a Task constructor, two methods to mark and unmark tasks, toString() method as well as a isMark() method to check if Task is marked
 */
public class Task {
    private boolean isMarked;
    public String name;

    /**
     * Constructor for Task
     *
     * @param name name of the task
     */
    public Task (String name) {
        this.name = name;
        this.isMarked = false;
    }

    /**
     * Marks current Task object as done
     *
     * markTask as done
     */
    public void setMarkedTask () {
        this.isMarked = true;
    }

    /**
     * Unmarks current Task object
     *
     * unmarkTask
     */
    public void setUnmarkedTask() {
        this.isMarked = false;
    }

    /**
     * Returns whether current Task object has been marked
     *
     * @return boolean on whether task is marked
     */
    public boolean hasBeenMarked() {
        return this.isMarked;
    }

    /**
     * Converts date to String format for display
     *
     * @param date LocalDate for Deadline/Event tasks
     * @return String format: converts from yyyy-mm-dd format to mmm dd yyyy format, eg: Aug 21 2022
     */
    public String dateConverterToString(LocalDate date) {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(date);
    }

    /**
     * Converts time to String format for display
     *
     * @param time LocalTime for Deadline/Event tasks
     * @return String format: converts hh:mm format to hh:mm am/pm format eg: 1:30pm
     */
    public String timeConverterToString(LocalTime time) {
        return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(time);
    }

    /**
     * String representation of Task
     *
     * @return String version of task, with marked and name. E.g. [X] Task vs [✓] Task
     */
    @Override
    public String toString() {
        if (this.isMarked) {
            String marked = "[✓] ";
            return marked + this.name;
        } else {
            String unmarked = "[X] ";
            return unmarked + this.name;
        }
    }
}