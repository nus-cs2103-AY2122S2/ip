package paggro.notableDate;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import java.util.ArrayList;

import paggro.task.Task;

/**
 * This class encapsulates a NotableDate object which contains a list of all the tasks on a given date.
 */
public class NotableDate {
    /** The date of the NotableDate object. */
    public LocalDate localDate;
    /** The list of tasks on the NotableDate. */
    public ArrayList<Task> tasks;

    /**
     * Constructor of NotableDate.
     * @param date The date of this NotableDate object.
     */
    public NotableDate(LocalDate date) {
        this.localDate = date;
        tasks = new ArrayList<>();
    }

    /**
     * Adds the given task to this NotableDate object's list of task.
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the String representation of this NotableDate object.
     * @return String representing this NotableDate.
     */
    @Override
    public String toString() {
        return localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }
}
