package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a task to complete
 */
public class Task {
    boolean isDone;
    protected final String task;
    protected final String type;
    protected final String time;
    protected LocalDate date;
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructor of task
     * @param task Description of the task
     * @param type Type of the task
     */
    Task(String task, String type) {
        this.isDone = false;
        this.task = task;
        this.type = type;
        this.date = LocalDate.now();
        this.time = this.date.toString();
    }

    /**
     * Constructor of task
     * @param task Description of the task
     * @param type Type of the task
     * @param time time of the task
     */
    Task(String task, String type, String time) {
        this.isDone = false;
        this.task = task;
        this.type = type;
        this.time = time;
        String timeArr[] = time.split(" ");
        this.date = LocalDate.parse(timeArr[1]);
    }

    /**
     * Mark the task as done
     */
    void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the task as not done
     */
    void unMark() {
        this.isDone = false;
    }
    /**
     * Return the date of the task
     * @return Date of the task
     */
    LocalDate getDate() {
        return this.date;
    }

    /**
     * Return the task to standard saving format
     * @return String in standard saving format
     */

    String saveFormat() {
        if (this.isDone) {
            return this.type + " ### 1 ### " + this.task;
        } else {
            return this.type + " ### 0 ### " + this.task;
        }
    }

    /**
     * If the task is done, return a string contains type of task + [X] + description of task
     * Else change the [X] to [ ]
     * @return String contains type of task, isDone status and description of task
     */

    @Override
    public String toString() {
        if (this.isDone) {
            return "[" + this.type + "] [X] " + this.task;
        } else {
            return "[" + this.type + "] [ ] " + this.task;
        }
    }
}
