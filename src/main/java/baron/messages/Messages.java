package baron.messages;

public class Messages {
    public static final String MESSAGE_MARK_SUCCESS = "Nice! I've marked this task as done:\n  ";
    public static final String MESSAGE_UNMARK_SUCCESS = "OK, I've marked this task as not done yet:\n  ";
    public static final String MESSAGE_ADD_TASK_SUCCESS = "Got it. I've added this task:\n  ";
    public static final String MESSAGE_LIST_TASK = "Here are the tasks in your list:\n";

    public static String getNoOfTasksMessage(int noOfTasks) {
        return String.format("Now you have %d tasks in the list.", noOfTasks);
    }
}
