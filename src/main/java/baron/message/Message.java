package baron.message;

import baron.tasks.TaskType;

/**
 * Contains and generates messages to be printed out as the output/partial output of a command.
 */
public class Message {
    public static final String MESSAGE_MARK_SUCCESS = "Nice! I've marked this task as done:\n  ";
    public static final String MESSAGE_UNMARK_SUCCESS = "OK, I've marked this task as not done yet:\n  ";
    public static final String MESSAGE_DELETE_SUCCESS = "Noted. I've removed this task:\n ";
    public static final String MESSAGE_ADD_TASK_SUCCESS = "Got it. I've added this task:\n  ";
    public static final String MESSAGE_LIST_TASK = "Here are the tasks in your list:\n";
    public static final String MESSAGE_NO_TASK = "You have no tasks.";
    public static final String MESSAGE_FILE_NOT_FOUND = "Data file is not found.";
    public static final String MESSAGE_INVALID_FILE_FORMAT = "Data file format is invalid.";
    public static final String MESSAGE_FILE_CREATION_FAIL = "Data file cannot be created.";
    public static final String MESSAGE_FILE_WRITE_FAIL = "Data file cannot be written to.";
    public static final String MESSAGE_DATE_TIME_FORMAT_INVALID = "The date/time should be in the following format: d/M/yyyy HH:mm";

    /**
     * Generates the message to show the number of tasks.
     *
     * @param noOfTasks the number of tasks to be display.
     * @return the message that shows the number of tasks.
     */
    public static String generateNoOfTasksMessage(int noOfTasks) {
        if (noOfTasks == 0) {
            return Message.MESSAGE_NO_TASK;
        } else if (noOfTasks == 1) {
            return "Now you have 1 task in your list.";
        }
        return String.format("Now you have %d tasks in your list.", noOfTasks);
    }

    /**
     * Generates the message to warn that the description of a {@code TaskType} cannot be empty.
     *
     * @param taskType the type of task.
     * @return the message that warns that the description of a {@code TaskType} cannot be empty.
     */
    public static String generateEmptyDescMessage(TaskType taskType) {
        return "The description of a " + taskType.getCommand() + " cannot be empty.";
    }

    /**
     * Generates the message to warn that the task index is out of bound and thus invalid.
     *
     * @param taskCount the number of tasks.
     * @return the message that warns that the task index is out of bound and thus invalid.
     */
    public static String generateTaskIndexOutOfBoundMessage(int taskCount) {
        return "The task index is invalid, only accepts 1 to " + taskCount + ".";
    }
}
