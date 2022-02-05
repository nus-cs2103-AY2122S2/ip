import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task. Contains a Task constructor, two methods to mark and unmark tasks, toString() method as well as a isMark() method to check if Task is marked
 */
public class Task {
    private boolean mark;
    public String name;

    /**
     * Constructor for Task. Is called in subclasses Todo, Event and Deadline
     * @param name name of the task
     */
    public Task (String name) {
        this.name = name;
        this.mark = false;
    }

    /**
     * Marks task as done
     */
    public void setMarkedTask () {
        String markedMessage = "Nice! I've marked this task as done:\n";
        this.mark = true;
        System.out.println(markedMessage + "  " + this);
    }

    /**
     * Sets task to be unmarked
     */
    public void setUnmarkedTask() {
        String unmarkedMessage = "OK, I've marked this task as not done yet:\n";
        this.mark = false;
        System.out.println(unmarkedMessage + "  " + this);
    }

    /**
     * Whether task has been marked
     * @return boolean on whether task is marked
     */
    public boolean hasBeenMarked() {
        return this.mark;
    }

    /**
     * Converts date to String format for printing
     * @param date LocalDate for Deadline/Event tasks
     * @return String format: converts from yyyy-mm-dd format to Aug dd, yyyy format
     */
    public String dateConverterToString(LocalDate date) {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(date);
    }

    /**
     * Converts time to String format for printing
     * @param time LocalTime for Deadline/Event tasks
     * @return String format: converts hh:mm format to hh:mm am/pm format
     */
    public String timeConverterToString(LocalTime time) {
        return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(time);
    }

    /**
     * @override
     * @return String version of task, with marked and name. E.g. [X] Task vs [✓] Task
     */
    public String toString() {
        if (this.mark) {
            String marked = "[✓] ";
            return marked + this.name;
        } else {
            String unmarked = "[X] ";
            return unmarked + this.name;
        }
    }
}