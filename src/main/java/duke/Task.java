package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a task to complete
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private boolean isDone;
    private String task;
    private String type;
    private String time;
    private LocalDate date;

    private String tag;

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
        this.tag = " ";
    }

    /**
     * Constructs a task
     *
     * @param task Description of the task
     * @param type Type of the task
     * @param time time of the task
     */
    Task(String task, String type, String time) {
        this.isDone = false;
        this.task = task;
        this.type = type;
        this.time = time;
        String[] timeArr = time.split(" ");
        this.date = LocalDate.parse(timeArr[1]);
        this.tag = " ";
    }

    /**
     * Checks if the description of task contains certain word
     *
     * @param word The word wish to check
     * @return Boolean of whether the word is contained in task
     */
    boolean containsWord(String word) {
        return this.task.contains(word);
    }

    /**
     * Marks the task as done
     */
    void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done
     */
    void unMark() {
        this.isDone = false;
    }

    /**
     * Returns the date of the task
     *
     * @return Date of the task
     */
    String getDate() {
        return this.date.format(FORMATTER);
    }

    /**
     * Returns the task to standard saving format
     *
     * @return String in standard saving format
     */

    public String saveFormat() {
        if (this.isDone) {
            return this.type + " ### 1 ### " + this.task;
        } else {
            return this.type + " ### 0 ### " + this.task;
        }
    }

    boolean isDone() {
        return this.isDone;
    }
    String getTime() {
        return this.time;
    }
    String getTag() {
        return this.tag;
    }

    /**
     * Adds tag to the task
     *
     * @param tag the tag to be added
     */
    public void tag(String tag) {
        this.tag = tag;
    }

    /**
     * Removes all tags of the task
     */
    public void untag() {
        this.tag = " ";
    }

    /**
     * Returns a String to represent the task
     *
     * @return String contains type of task, isDone status and description of task
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[" + this.type + "] [X] " + this.task + "   ";
        } else {
            return "[" + this.type + "] [ ] " + this.task + "   ";
        }
    }
}
