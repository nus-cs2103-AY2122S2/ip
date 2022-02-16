package angela.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Stores the information about the task
 */
public class Task {
    private static final String MARK_TASK = " Nice! I've marked this task as done: " + "\n" + "  ";
    private static final String UNMARK_TASK = " OK, I've marked this task as not done yet: " + "\n" + "  ";
    private static final String DATABASE_DATE_FORMAT = "d/M/yyyy";
    private static final String DISPLAY_DATE_FORMAT = "MMM d yyyy";
    private final String description;
    private boolean isComplete;
    private final String type;
    private LocalDate time;
    private String timeCommand;

    /**
     * Initialize a task object
     *
     * @param description Description of the task
     * @param type Type of the task
     */
    public Task(String description, String type) {
        int slashIndex = parseDescription(description, type);
        this.description = description.substring(0, slashIndex);
        this.isComplete = false;
        this.type = type;
    }

    /**
     * Parses the description into smaller components, such as time and the time command
     * and returns the index at the start of the time command
     *
     * @param description The description of the task
     * @param type The type of the task
     * @return The index at the start of time command
     */
    private int parseDescription(String description, String type) {
        int slashIndex = -1;
        int command = -1;

        if (!type.equals("T")) {
            slashIndex = description.indexOf("/");
            command = description.indexOf(" ", slashIndex);
            String timeString = description.substring(command + 1);
            this.time = LocalDate.parse(timeString,
                    DateTimeFormatter.ofPattern(DATABASE_DATE_FORMAT));
            this.timeCommand = description.substring(slashIndex + 1, command);
        } else {
            slashIndex = description.length();
            this.timeCommand = "";
        }
        assert slashIndex != -1 : "Slash Index should not be -1";
        return slashIndex;
    }

    /**
     * Changes status of the task
     *
     * @param isComplete New status of the task
     * @return String represent the changed status
     */
    public ArrayList<String> changeTaskStatus(boolean isComplete) {
        ArrayList<String> wordArray = new ArrayList<>();
        this.isComplete = isComplete;
        if (isComplete) {
            wordArray.add(MARK_TASK + this);
        } else {
            wordArray.add(UNMARK_TASK + this);
        }
        return wordArray;
    }

    /**
     * Returns the string used to append to the database file
     *
     * @return The string with the database file format
     */
    public String createTextDatabase() {
        String done = createStatus(true);
        String timeDue = createTimeDue(true);
        return mergeTextDatabase(done, timeDue, true);
    }

    /**
     * Returns the display string of the task
     *
     * @return The string that describes the task
     */
    @Override
    public String toString() {
        String bracketString = createStatus(false);
        String timeCommandString = createTimeDue(false);
        return mergeTextDatabase(bracketString, timeCommandString, false);
    }

    /**
     * Returns the string represent the status of task
     *
     * @return The string represent the status
     */
    private String createStatus(boolean isDatabase) {
        if (isDatabase) {
            return createStatusDatabase();
        } else {
            return createStatusDisplay();
        }
    }

    /**
     * Returns the string represent the status of task for database file
     *
     * @return The string represent the status for the database file
     */
    private String createStatusDatabase() {
        if (isComplete) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Returns the string represent the status of task for GUI display
     *
     * @return The string represent the status for GUI display
     */
    private String createStatusDisplay() {
        if (isComplete) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns the string represent due time of task
     *
     * @return The string represent the time
     */
    private String createTimeDue(boolean isDatabase) {
        if (isDatabase) {
            return createTimeDueDatabase();
        } else {
            return createTimeDueDisplay();
        }
    }

    /**
     * Returns the string represent due time of task for database file
     *
     * @return The string represent the time for database file
     */
    private String createTimeDueDatabase() {
        if (this.timeCommand.equals("")) {
            return " |";
        } else {
            return "| " + timeCommand + " "
                    + this.time.format(DateTimeFormatter.ofPattern(DATABASE_DATE_FORMAT)) + " |";
        }
    }

    /**
     * Returns the string represent due time of task for GUI display
     *
     * @return The string represent the time for GUI display
     */
    private String createTimeDueDisplay() {
        String timeCommandString = "";
        if (!this.timeCommand.equals("")) {
            timeCommandString = "(" + this.timeCommand + ": "
                    + time.format(DateTimeFormatter.ofPattern(DISPLAY_DATE_FORMAT)) + ")";
        }
        return timeCommandString;
    }

    /**
     * Returns the string from merging two status and time due of the task
     *
     * @param done Status of the task
     * @param timeDue Time due of the task
     * @return The string from merging them together
     */
    private String mergeTextDatabase(String done, String timeDue, boolean isDatabase) {
        if (isDatabase) {
            return "| " + type + " | " + done + " | " + description + timeDue;
        } else {
            return "[" + type + "]" + done + " " + description + timeDue;
        }
    }

    public LocalDate getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }
}
