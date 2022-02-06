package duke.task;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task. Each Task has a name and its completion can be tracked.
 */
public class Task {
    private String name;
    private boolean done;
    private LocalDate date;
    private LocalTime time;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private String type;

    /**
     * Defaults to constructor for ToDo.
     * Task is not done by default.
     *
     * @param type Type of Task.
     * @param name Name of Deadline.
     */
    public Task(String type, String name) {
        this.type = type;
        this.name = name;
        this.done = false;
    }

    /**
     * Defaults to constructor for Deadline.
     * Deadline is not done by default.
     *
     * @param type Type of Deadline.
     * @param name Name of Deadline.
     * @param date Date to complete Deadline by.
     * @param time Time to complete Deadline by.
     */
    public Task(String type, String name, LocalDate date, LocalTime time) {
        this.type = type;
        this.name = name;
        this.date = date;
        this.time = time;
        this.done = false;
    }

    /**
     * Defaults to constructor for Event.
     * Event is not done by default.
     *
     * @param type Type of Event.
     * @param name Name of Event.
     * @param date Date Event starts.
     * @param timeFrom Time Event starts.
     * @param timeTo Time Event ends.
     */
    public Task(String type, String name, LocalDate date, LocalTime timeFrom, LocalTime timeTo) {
        this.type = type;
        this.name = name;
        this.date = date;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.done = false;
    }

    /**
     * Marks task.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Unmarks task.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Returns type of task.
     * Used for writing into log file.
     *
     * @return String (D/E/T) representing type of task.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns whether task is completed.
     * Used for writing into log file.
     *
     * @return String representing if task is completed.
     */
    public String getDone() {
        if (done) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Returns name of Task.
     * Used for writing into log file.
     *
     * @return Name of task.
     */
    public String getName(){
        return name;
    }

    /**
     * Returns date of Task.
     * Used for writing into log file.
     *
     * @return Date of task.
     */
    public String getDate() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return date.format(outputFormatter).toString();
    }

    /**
     * Returns time to complete Deadline by.
     * Used for writing into log file.
     *
     * @return Time to complete Deadline by.
     */
    public String getTime() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HHmm");
        return time.format(outputFormatter).toString();
    }

    /**
     * Returns time Event starts.
     * Used for writing into log file.
     *
     * @return Time Event starts.
     */
    public String getTimeFrom() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HHmm");
        return timeFrom.format(outputFormatter).toString();
    }

    /**
     * Returns time Event ends.
     * Used for writing into log file.
     *
     * @return Time Event ends.
     */
    public String getTimeTo() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HHmm");
        return timeTo.format(outputFormatter).toString();
    }

    /**
     * Overrides toString to display whether or not Task is completed, and name of Task.
     *
     * @return String containing above information
     */
    @Override
    public String toString() {
        String str = "[";
        //Completion
        if (this.done) {
            str += "X";
        } else {
            str += " ";
        }
        str += "] " + this.name;
        return str;
    }
}
