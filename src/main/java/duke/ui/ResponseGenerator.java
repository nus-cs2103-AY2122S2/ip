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
    private final String logo;

    /**
     * Creates a new Ui instance.
     */
    public ResponseGenerator() {
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }

    /**
     * Returns a startup message.
     *
     * @return A startup message.
     */
    public String getStartupMessage() {
        return new StringBuilder().append("Hello! I'm\n").append(logo)
                .append("\nWhat can I do for you? =)").toString();
    }

    /**
     * Returns a farewell message.
     *
     * @return A farewell message.
     */
    public String getByeMessage() {
        return "Bye t_t";
    }

    /**
     * Returns an error message for file loading and parsing errors.
     *
     * @return An error message for file loading and parsing errors.
     */
    public String getFileErrorMessage() {
        return "Error loading/parsing file ?.? Creating empty list!";
    }

    /**
     * Returns an error message for I/O errors.
     *
     * @return An error message for I/O errors.
     */
    public String getIoErrorMessage() {
        return "I/O error x.x";
    }

    /**
     * Returns an error message for date/time format errors.
     *
     * @return An error message for date/time format errors.
     */
    public String getDateTimeFormatErrorMessage() {
        return "Error parsing date, please enter dates in YYYY-MM-DD format!";
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
        return "Maximum date/time exceeded D: please try again!";
    }

    /**
     * Returns the message to be printed when a Task is added to the task list.
     *
     * @param latestTask The Task that was just added to the task list.
     * @param size The new size of the task list.
     * @return The message to be printed when a Task is added.
     */
    public String getAddTaskMessage(Task latestTask, int size) {
        return new StringBuilder().append("added o.O:\n  ").append(latestTask.toString())
                        .append("\nNow there are ").append(size).append(" tasks on the list x)").toString();
    }

    /**
     * Returns the message to be printed when a Reminder is added.
     *
     * @param task The Task to remind the user of.
     * @param reminderTime The time to remind the user.
     * @return The message to be printed when a Reminder is added.
     */
    public String getAddReminderMessage(Task task, Reminder reminderTime) {
        return ":D Reminder set at " + reminderTime.getDateTime() + " for task: " + task.getDescription();
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
            return "!Reminder! " + e.getDescription() + " at " + e.formatTime();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "!Reminder! " + d.getDescription() + " by " + d.formatTime();
        }
        return "!Reminder! " + t.getDescription();
    }

    /**
     * Prints the items in the task list.
     *
     * @param list The list of Tasks in the task list.
     * @return A String containing all the Tasks in the task list.
     */
    public String printItems(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "There are no tasks on your list :O";
        }
        StringBuilder message = new StringBuilder();
        message.append("Here are the tasks on your list :O\n");
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
            return "No items found :O";
        }
        StringBuilder message = new StringBuilder();
        message.append("Here are the matching tasks on your list :O\n");
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
