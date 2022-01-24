package baron.messages;

import baron.tasks.TaskType;

public class Messages {
    public static final String MESSAGE_MARK_SUCCESS = "Nice! I've marked this task as done:\n  ";
    public static final String MESSAGE_UNMARK_SUCCESS = "OK, I've marked this task as not done yet:\n  ";
    public static final String MESSAGE_DELETE_SUCCESS = "Noted. I've removed this task:\n ";
    public static final String MESSAGE_ADD_TASK_SUCCESS = "Got it. I've added this task:\n  ";
    public static final String MESSAGE_LIST_TASK = "Here are the tasks in your list:\n";
    public static final String MESSAGE_NO_TASK = "You have no tasks.\n";
    public static final String MESSAGE_FILE_NOT_FOUND = "Data file is not found.\n";
    public static final String MESSAGE_INVALID_FILE_FORMAT = "Data file format is invalid.\n";
    public static final String MESSAGE_FILE_CREATION_FAIL = "Data file cannot be created.\n";
    public static final String MESSAGE_FILE_WRITE_FAIL = "Data file cannot be written to.\n";

    public static String generateNoOfTasksMessage(int noOfTasks) {
        return String.format("Now you have %d tasks in the list.", noOfTasks);
    }

    public static String generateEmptyDescMessage(TaskType taskType) {
        return "The description of a " + taskType.getCommand() + " cannot be empty.";
    }
}
