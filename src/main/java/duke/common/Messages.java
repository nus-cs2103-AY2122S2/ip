package duke.common;

public class Messages {
    public static final String MESSAGE_WELCOME = "Welcome to MultiTask! \n";
    public static final String MESSAGE_GOOD_BYE = "Bye. Hope to see you again soon!";
    public static final String INPUT_FIELD_PROMPT = "What is your task today?";
    public static final String MESSAGE_PROMPT = "Do you want to know what I can do?\n"
            + "See the list of commands below:\n";
    public static final String COMMAND_SUMMARY = "/list - List all tasks\n"
            + "/todo TODO - Create a todo\n"
            + "/deadline DEADLINE /by DUE_DATE - Create a deadline\n"
            + "/event EVENT /at EVENT_DATE - Create an event\n"
            + "/mark INDEX - Mark a task\n"
            + "/unmark INDEX - Unmark a task\n"
            + "/delete INDEX - Delete a task\n"
            + "/sort TYPE - Sort tasks with dates\n"
            + "/filter TYPE - Filter tasks with type\n"
            + "/find KEYWORD - Find tasks with keyword\n"
            + "/bye - Exit MultiTask\n";
    public static final String MESSAGE_MARK = "Nice! I've marked this task as done.\n";
    public static final String MESSAGE_UNMARKED = "OK, I've marked this task as not done yet.\n";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task.\n";
    public static final String MESSAGE_FIND = "Here are the matching tasks in your list.\n";
    public static final String MESSAGE_ADD_TODO = "Got it. I've added this todo.\n";
    public static final String MESSAGE_ADD_DEADLINE = "Got it. I've added this deadline.\n";
    public static final String MESSAGE_ADD_EVENT = "Got it. I've added this event.\n";
    public static final String MESSAGE_ERROR_INVALID_COMMAND = "OOPS!!! I'm sorry, but I don't know what."
           + " that means :-(\n";
    public static final String MESSAGE_ERROR_EMPTY_TITLE = "The description of a todo cannot be empty.\n";
    public static final String MESSAGE_ERROR_INVALID_DATETIME_FORMAT = "Invalid date time format. "
            + "Please use the dd/MM/yyyy HHmm format.";
    public static final String MESSAGE_ERROR_INVALID_TASK_TYPE = "Invalid task type. Please add only todo, "
            + "event or deadline.\n";
    public static final String MESSAGE_ERROR_INVALID_SORT_TYPE = "Invalid sort type. Please sort either event or "
            + "deadline.\n";
    public static final String MESSAGE_ERROR_INVALID_FILTER_TYPE = "Invalid filter type. Please filter either todo, "
            + "event or deadline.\n";
}
