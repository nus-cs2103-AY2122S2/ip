package duke.ui;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Reminder;
import duke.task.Task;

/**
 * Contains functionality relating to generating Duke's responses.
 */
public class ResponseGenerator {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String STARTUP_MESSAGE = "Hello I'm\n" + LOGO + "\nWhat can I do for you? =)";
    private static final String BYE_MESSAGE = "Bye t_t";

    private static final String FILE_ERROR_MESSAGE = "Error loading/parsing file ?.? Creating empty list!";
    private static final String IO_ERROR_MESSAGE = "I/O error x.x";
    private static final String DATETIME_FORMAT_ERROR_MESSAGE =
            "Error parsing date, please enter dates in YYYY-MM-DD format!";
    private static final String MAX_DATETIME_EXCEED_ERROR_MESSAGE =
            "Maximum date/time exceeded D: please try again!";

    private static final String ADD_TASK_MESSAGE = "added o.O:\n  %1$s\nNow there are %2$s tasks on the list x)";
    private static final String ADD_REMINDER_MESSAGE = ":D Reminder set at %1$s for task: %2$s";

    private static final String DEADLINE_REMINDER_MESSAGE = "!Reminder! %1$s by %2$s";
    private static final String EVENT_REMINDER_MESSAGE = "!Reminder! %1$s at %2$s";
    private static final String TODO_REMINDER_MESSAGE = "!Reminder! %1$s";

    private static final String EMPTY_LIST_MESSAGE = "There are no tasks on your list :O";
    private static final String LIST_START_MESSAGE = "Here are the tasks on your list :O\n";
    private static final String NO_ITEMS_FOUND_MESSAGE = "No items found :O";
    private static final String FILTERED_LIST_START_MESSAGE = "Here are the matching tasks on your list :O\n";

    /**
     * Creates a new Ui instance.
     */
    public ResponseGenerator() {
    }

    /**
     * Returns a startup message.
     *
     * @return A startup message.
     */
    public String getStartupMessage() {
        return STARTUP_MESSAGE;
    }

    /**
     * Returns a farewell message.
     *
     * @return A farewell message.
     */
    public String getByeMessage() {
        return BYE_MESSAGE;
    }

    /**
     * Returns an error message for file loading and parsing errors.
     *
     * @return An error message for file loading and parsing errors.
     */
    public String getFileErrorMessage() {
        return FILE_ERROR_MESSAGE;
    }

    /**
     * Returns an error message for I/O errors.
     *
     * @return An error message for I/O errors.
     */
    public String getIoErrorMessage() {
        return IO_ERROR_MESSAGE;
    }

    /**
     * Returns an error message for date/time format errors.
     *
     * @return An error message for date/time format errors.
     */
    public String getDateTimeFormatErrorMessage() {
        return DATETIME_FORMAT_ERROR_MESSAGE;
    }

    /**
     * Returns the error message for errors in the package duke.exception.
     *
     * @param e An exception in the package duke.exception.
     * @return The error message corresponding to the exception.
     */
    public String getDukeErrorMessage(Exception e) {
        return e.getMessage();
    }

    /**
     * Returns the error message when the maximum date/time is exceeded
     * for scheduling of reminders.
     *
     * @return The error message that maximum date/time is exceeded.
     */
    public String getMaxDateTimeExceededErrorMessage() {
        return MAX_DATETIME_EXCEED_ERROR_MESSAGE;
    }

    /**
     * Returns the message to be printed when a Task is added to the task list.
     *
     * @param latestTask The Task that was just added to the task list.
     * @param size The new size of the task list.
     * @return The message to be printed when a Task is added.
     */
    public String getAddTaskMessage(Task latestTask, int size) {
        return String.format(ADD_TASK_MESSAGE, latestTask, size);
    }

    /**
     * Returns the message to be printed when a Reminder is added.
     *
     * @param task The Task to remind the user of.
     * @param reminderTime The time to remind the user.
     * @return The message to be printed when a Reminder is added.
     */
    public String getAddReminderMessage(Task task, Reminder reminderTime) {
        return String.format(ADD_REMINDER_MESSAGE, reminderTime.getDateTime(), task.getDescription());
    }

    /**
     * Returns the message to be printed with a Reminder.
     *
     * @param t The Task to remind about.
     * @return The message to be printed to remind the user.
     */
    public String getReminderMessage(Task t) {
        if (t instanceof Event) {
            Event e = (Event) t;
            return String.format(EVENT_REMINDER_MESSAGE, e.getDescription(), e.formatTime());
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return String.format(DEADLINE_REMINDER_MESSAGE, d.getDescription(), d.formatTime());
        }
        return String.format(TODO_REMINDER_MESSAGE, t.getDescription());
    }

    /**
     * Prints the items in the task list.
     *
     * @param list The list of Tasks in the task list.
     * @return A String containing all the Tasks in the task list.
     */
    public String printItems(ArrayList<Task> list) {
        if (list.size() == 0) {
            return EMPTY_LIST_MESSAGE;
        }
        StringBuilder message = new StringBuilder();
        message.append(LIST_START_MESSAGE);
        for (int i = 1; i <= list.size(); i++) {
            message.append(i);
            message.append(". ");
            message.append(list.get(i - 1));
            if (i < list.size()) {
                message.append("\n");
            }
        }
        return message.toString();
    }

    /**
     * Prints the items found in the task list.
     * For when items are searched for using a specific criterion.
     *
     * @param list The list of Tasks found in the task list.
     * @return A String containing the Tasks found in the task list.
     */
    public String printFoundItems(ArrayList<Task> list) {
        if (list.size() == 0) {
            return NO_ITEMS_FOUND_MESSAGE;
        }
        StringBuilder message = new StringBuilder();
        message.append(FILTERED_LIST_START_MESSAGE);
        for (int i = 1; i <= list.size(); i++) {
            message.append(i);
            message.append(". ");
            message.append(list.get(i - 1));
            if (i < list.size()) {
                message.append("\n");
            }
        }
        return message.toString();
    }
}
