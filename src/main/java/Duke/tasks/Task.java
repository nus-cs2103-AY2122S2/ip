package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.ui.DukeException;
/**
 * Represents the task user want to do.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns X if task is done, else empty.
     *
     * @return "X" if task is done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Sets the task that is done to true.
     *
     */
    public void setChecked() {
        isDone = true;
    }

    /**
     * Sets the task that is not done to false.
     *
     */
    public void setUnchecked() {
        isDone = false;
    }

    abstract public String saveToFileString();

    /**
     * Returns the date that was converted from String to LocalDate.
     *
     * @param input The input of date and time by user.
     * @return as the date that was input by user instead of String.
     * @throws DukeException throws error if the user did not input correct format.
     */
    public LocalDate getTaskDate(String input) throws DukeException {
        try {
            String[] dateList = input.split(" ", 2);
            String dateFormat = "[dd/MM/yyyy][d/MM/yyyy][dd/M/yyyy][d/MM/yyyy]";
            LocalDate date = LocalDate.parse(dateList[0],
                    DateTimeFormatter.ofPattern(dateFormat));
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeException("ERROR! Please input your date format as dd/mm/yyy");
        }
    }

    /**
     * Returns the time that was converted from String to LocalTime.
     *
     * @param input The input of date and time by user.
     * @return as the time that was input by user instead of String.
     * @throws DukeException throws error if the user did not input correct format.
     */
    public LocalTime getTaskTime(String input) throws DukeException {
        try {
            String[] dateList = input.split(" ", 2);
            String timeFormat = "[HHmm][hha][hhmma][hmma][ha]";
            LocalTime time = LocalTime.parse(dateList[1], DateTimeFormatter.ofPattern(timeFormat));
            return time;
        } catch (DateTimeParseException e) {
            throw new DukeException("ERROR! Please input your time format in 24hrs format or AM/PM");
        }
    }

    /**
     * The String representation of the Task.
     *
     * @return the status and description of the task.
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
