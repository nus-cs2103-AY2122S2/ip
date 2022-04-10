package duke;

public class Message {
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke, What can I do for you?";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon! \n(Closing in 3 seconds)";

    public static final String ERRORMESSAGE_TODO = "Missing details! Please use the format: todo <description>";
    public static final String ERRORMESSAGE_DEADLINE =
            "Missing details! Please use the format: deadline <description> /by <dd/mm/yyyy hhmm>";
    public static final String ERRORMESSAGE_EVENT =
            "Missing details! Please use the format: event <description> /at <dd/mm/yyyy hhmm>";
    public static final String ERRORMESSAGE_INVALID_INTEGER = "Please enter an integer!";
    public static final String ERRORMESSAGE_MISSING_WORD = "Enter some words to search!";
}
