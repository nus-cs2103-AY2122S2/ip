package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A class that handles the interactions with the user.
 */
public class Ui {
    public static final String EMOJI_FACE = "\u2282(  \u0361\u2022  \u035c  \u2022\u0361  )\u2283";

    public static final String GREET_NEW_USER_MESSAGE = "Hello! I'm Duke, your Personal Assistant ChatBot. "
            + EMOJI_FACE
            + System.lineSeparator()
            + "Nice to meet you! ^_^"
            + System.lineSeparator()
            + "What can I do for you?";

    public static final String GREET_OLD_USER_MESSAGE = "Hello! I'm Duke, your Personal Assistant ChatBot. "
            + EMOJI_FACE
            + System.lineSeparator()
            + "Welcome back! ^_^"
            + System.lineSeparator()
            + "Enter 'list' command to see your task list.";

    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon! ^_^"
            + System.lineSeparator()
            + "The program will exit in 10 seconds.";

    /**
     * Constructor to initialize an instance of Ui class.
     */
    public Ui() {
    }

    /**
     * Returns the message when the task is added.
     *
     * @param task Task that is added
     * @return The string representation of the message
     */
    public String taskAddedMessage(Task task) {
        assert task != null : "Task should not be null";

        return "Got it. I've added this task:"
                + System.lineSeparator() + "\t" + task;
    }

    /**
     * Returns the message when the task is marked as done.
     *
     * @param task Task that is marked as done
     * @return The string representation of the message
     */
    public String taskDoneMessage(Task task) {
        assert task != null : "Task should not be null";

        return "Nice! I've marked this task as done:"
                + System.lineSeparator() + "\t" + task;
    }

    /**
     * Returns the message when the task is marked as not done yet.
     *
     * @param task Task that is marked as not done yet
     * @return The string representation of the message
     */
    public String taskNotDoneMessage(Task task) {
        assert task != null : "Task should not be null";

        return "OK, I've marked this task as not done yet:"
                + System.lineSeparator() + "\t" + task;
    }

    /**
     * Returns the message when the task is removed.
     *
     * @param task Task that is removed
     * @return The string representation of the message
     */
    public String taskDeletedMessage(Task task) {
        assert task != null : "Task should not be null";

        return "Noted. I've removed this task:"
                + System.lineSeparator() + "\t" + task;
    }

    /**
     * Returns the message with the number of tasks in the task list.
     *
     * @param taskList Task list containing all the tasks
     * @return The string representation of the message
     */
    public String numOfTasksInListMessage(TaskList taskList) {
        assert taskList.getNumOfTasks() != 0
                : "List of tasks in the task list should not be empty";

        return "Now you have " + taskList.getNumOfTasks()
                + (taskList.getNumOfTasks() > 1 ? " tasks" : " task")
                + " in the list.";
    }

    /**
     * Returns the message with all the tasks in the task list.
     *
     * @param taskList Task list containing all the tasks
     * @return The string representation of the message
     */
    public String tasksInListMessage(TaskList taskList) {
        assert taskList.getNumOfTasks() != 0
                : "List of tasks in the task list should not be empty";

        return "Here"
                + (taskList.getNumOfTasks() > 1 ? " are the tasks " : " is the task ")
                + "in your list:"
                + System.lineSeparator()
                + "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator()
                + taskList;
    }

    /**
     * Returns the message with the tasks in the task list that
     * occurs on the specified date.
     *
     * @param taskList Task list containing all the tasks
     * @param dateStr Specified date
     * @return The string representation of the message
     */
    public String tasksOnDateMessage(TaskList taskList, String dateStr) {
        assert taskList.getNumOfFilteredTasks() != 0
                : "List of filtered tasks in the task list should not be empty";

        return "Here"
                + (taskList.getNumOfFilteredTasks() > 1 ? " are the tasks " : " is the task ")
                + "on this date (" + processDateStr(dateStr) + "):"
                + System.lineSeparator()
                + "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator()
                + taskList;
    }

    /**
     * Returns the specified date string after processing.
     *
     * If the specified date is a valid date format accepted by
     * the system, return the date in LocalDate (MMM d yyyy) format,
     * otherwise return the original specified date.
     *
     * @param dateStr Specified date
     * @return Specified date string after processing
     */
    private String processDateStr(String dateStr) {
        assert !dateStr.equals("") : "Date should not be empty";

        LocalDate date;
        String processedDateStr;

        try {
            date = LocalDate.parse(dateStr);
            processedDateStr = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            processedDateStr = dateStr;
        }

        return processedDateStr;
    }

    /**
     * Returns the message with the tasks in the task list that
     * contains the keyword in the description.
     *
     * @param taskList Task list containing all the tasks
     * @param keyword Keyword in the description
     * @return The string representation of the message
     */
    public String tasksWithKeywordMessage(TaskList taskList, String keyword) {
        assert taskList.getNumOfFilteredTasks() != 0
                : "List of filtered tasks in the task list should not be empty";

        return "Here"
                + (taskList.getNumOfFilteredTasks() > 1 ? " are the matching tasks " : " is the matching task ")
                + "in your list:"
                + System.lineSeparator()
                + "[Keyword Search: " + keyword + "]"
                + System.lineSeparator()
                + "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator()
                + taskList;
    }

    /**
     * Returns the reminder message of the existing filtered tasks.
     *
     * @param filteredTasksMessage Filtered tasks message
     * @return The string representation of the reminder message
     */
    public String filteredTasksReminderMessage(String filteredTasksMessage) {
        assert !filteredTasksMessage.equals("")
                : "Filtered tasks message should not be empty";

        return "[FILTERED TASKS]"
                + System.lineSeparator()
                + System.lineSeparator()
                + filteredTasksMessage;
    }
}
