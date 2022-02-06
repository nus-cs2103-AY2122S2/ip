package alfred.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encapsulates all Alfred Tasks.
 */
public abstract class Task {
    // class constants
    public static final String FORMAT_SPLIT = "`";
    protected static final String COMPLETION_MARK = "X";
    protected static final String INCOMPLETE_MARK = " ";
    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM);


    // instance attributes
    protected final String description;
    protected boolean isCompleted;

    /**
     * Constructor of the parent class, so every task must have
     * some description and completion status.
     *
     * @param description Task name.
     */
    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    private String markIfComplete() {
        if (this.isCompleted) {
            return Task.COMPLETION_MARK;
        } else {
            return Task.INCOMPLETE_MARK;
        }
    }

    /**
     * Updates internal state of the task to be complete.
     */
    public void markComplete() {
        this.isCompleted = true;
    }

    /**
     * Updates internal state of the task to be incomplete.
     */
    public void markIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Returns formatted date and time given a LocalDateTime object.
     * Intended as a helper method.
     *
     * @param dateTime LocalDateTime object encapsulating date and time.
     * @return String format as defined by an internal format.
     */
    public static String localDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(Task.dateTimeFormatter);
    }

    /**
     * Checks if a given input string matches the description of the task.
     *
     * @param text String input to be matched.
     * @return True if matching.
     */
    public boolean match(String text) {
        Pattern pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.description);
        return matcher.find();

    }

    /**
     * Parses a save string to return a Task object.
     *
     * @param input String representation of saved task.
     * @return Task object that was saved.
     */
    public static Task parseSavedInput(String input) {
        String[] arguments = input.split(Task.FORMAT_SPLIT);
        arguments = Arrays.stream(arguments).map(s -> s.trim()).toArray(String[]::new);
        String command = arguments[0];
        System.out.println(command);
        boolean marked = arguments[1].equals(Task.COMPLETION_MARK);
        switch (command) {
        case ToDo.type:
            return new ToDo(marked, arguments[2]);
        case Event.type:
            return new Event(marked, arguments[2], arguments[3]);
        case Deadline.type:
            return new Deadline(marked, arguments[2], arguments[3]);
        default:
            throw new RuntimeException("Invalid command saved!");
        }
    }

    /**
     * Converts a task to a save string for saving.
     *
     * @return String for saving.
     */
    public abstract String taskToSaveString();


    @Override
    public String toString() {
        return "[" + this.markIfComplete() + "] " + this.description;
    }
}
