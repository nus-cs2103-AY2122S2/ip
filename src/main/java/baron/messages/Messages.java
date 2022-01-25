package baron.messages;

import baron.tasks.TaskType;

public class Messages {
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

    public static String generateNoOfTasksMessage(int noOfTasks) {
        if (noOfTasks == 0) {
            return Messages.MESSAGE_NO_TASK;
        } else if (noOfTasks == 1) {
            return "Now you have 1 task in your list.";
        }
        return String.format("Now you have %d tasks in your list.", noOfTasks);
    }

    public static String generateEmptyDescMessage(TaskType taskType) {
        return "The description of a " + taskType.getCommand() + " cannot be empty.";
    }
}
