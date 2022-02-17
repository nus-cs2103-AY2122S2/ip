package ann.data.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import ann.data.InputPattern;

/**
 * Represents a task with a deadline.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class Deadline extends Task {
    public static final String INPUT_FORMAT = "deadline [content] /by yyyy-MM-dd HH:mm";
    private static final TaskType TASK_TYPE = TaskType.DEADLINE;
    public static final String KEYWORD = TASK_TYPE.getKeyword();
    private String date;
    private String time;

    /**
     * Creates a new Deadline with the specified content and date and time.
     *
     * @param content a String which describes the task.
     * @param dateAndTime a String which describes the date and time (in the user input format).
     */
    public Deadline(String content, String dateAndTime) {
        super(content);
        assert InputPattern.isValidDateTimeString(dateAndTime) : "An illegal date-time format "
                + "scenario is handled by the Parser";
        setDateAndTime(dateAndTime);
    }

    /**
     * Creates a new Deadline with the specified content and completion status.
     *
     * @param content a String which describes the task.
     * @param isDone a boolean which indicates whether the task is completed.
     */
    public Deadline(String content, boolean isDone) {
        super(content, isDone);
    }

    /**
     * Returns a newly created Deadline from the specified content, date and time and completion status.
     *
     * @param content a String which describes the task.
     * @param dateAndTime a String which describes the date and time (in the file format).
     * @param isDone a boolean which indicates whether the task is completed.
     * @return a newly created Deadline from the specified content, dateAndTime and completion status.
     */
    public static Deadline createDeadlineFromStorage(String content, String dateAndTime, boolean isDone) {
        assert !content.isBlank() : "All Deadlines in storage should have a non-empty description";
        Deadline ddl = new Deadline(content, isDone);
        parseDateAndTime(ddl, dateAndTime);
        return ddl;
    }

    /**
     * Parses the date and time inputted by the user and sets the date and time to Strings in the file format.
     *
     * @param dateAndTime a String representing the date and time (in user input format).
     */
    public void setDateAndTime(String dateAndTime) {
        String[] dt = dateAndTime.split(" ");
        LocalDate date = LocalDate.parse(dt[0]);
        LocalTime time = LocalTime.parse(dt[1]);
        this.date = date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        this.time = time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * Sets the date and time of the given Deadline to the result of parsing the given date and time
     * String (in file format).
     *
     * @param ddl the Deadline whose date and time field is to be set.
     * @param dateAndTime the String representing the date and time of ddl.
     */
    private static void parseDateAndTime(Deadline ddl, String dateAndTime) {
        ddl.date = dateAndTime.substring(0, 11).trim();
        ddl.time = dateAndTime.substring(11).trim();
    }

    /**
     * Returns the String representation of the Deadline to be displayed to users.
     *
     * @return the String representation of the Deadline to be displayed to users.
     */
    @Override
    public String toString() {
        return "[D]" + (super.isDone ? "[X] " : "[ ] ") + super.content + " (by " + this.date + " " + this.time + ")";
    }

    /**
     * Returns the String representation of the Deadline to be written to a file.
     *
     * @return the String representation of the Deadline to be written to a file.
     */
    @Override
    public String toFileString() {
        return "D, " + (super.isDone ? "1, " : "0, ") + super.content + ", " + this.date + " " + this.time;
    }
}
