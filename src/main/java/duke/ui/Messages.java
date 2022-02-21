package duke.ui;

/**
 * A class that consists of messages that the Ui will show to the user as needed.
 */
public class Messages {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String EXCLAMATION = "!";

    public static final String BOT_NAME = "Koro";

    public static final String WELCOME_MESSAGE = "Yoooo! My name is "
            + BOT_NAME + EXCLAMATION + LINE_SEPARATOR + "How can i help you bro?";
    public static final String FAREWELL_MESSAGE = "See you next time" + EXCLAMATION;

    public static final String UNKNOWN_COMMAND = "Unknown command! Here is the list of commands: ";
    public static final String TRY_HELP_MSG = "If unsure, try 'help'.";

    public static final String UNKNOWN_TODO = "todo.. todo what?";

    public static final String UNKNOWN_DEADLINE = "Sorry but.. deadline of what??";
    public static final String DATETIME_FORMAT = "<dd/MM/yyyy HHMM>";
    public static final String UNKNOWN_DATETIME = "Invalid date-time format! Format: " + DATETIME_FORMAT
            + System.lineSeparator() + "An example: 12/12/2222 0800";

    public static final String UNKNOWN_EVENT = "What event? No event stated.";
    public static final String UNKNOWN_LOCATION = "At where? Please specify again";

    public static final String DELETE_ERROR = "Error saving.";

    public static final String UNKNOWN_MARK = "Please specify what task number to mark clearly.";
    public static final String MARK_SUCCESS = "Nice! I've marked this task as done:";

    public static final String UNKNOWN_UNMARK = "Please specify what task number to unmark clearly.";
    public static final String UNMARK_SUCCESS = "OK, I've marked this task as not done yet:";

    public static final String UNKNOWN_DELETE = "Please specify what task number to delete clearly.";
    public static final String DELETE_SUCCESS = "Noted. I've removed this task:";

    public static final String UNKNOWN_FIND = "Please provide a keyword to search for your task.";
    public static final String FINDING_MSG = "Finding matching tasks in your list:";
    public static final String NO_FIND_MATCH_MSG = "I cannot find any task associated with the given keyword!";

    public static final String SAVE_ERROR = "Error saving file";

    public static final String LIST_MSG = "Here are the tasks in your list:";

    // whole block involves the help messages of each command.
    public static final String HELP_INTRO = "New to the App? Fret not bro!" + LINE_SEPARATOR
            + "This app allows you to add Tasks to your TaskList, so that you can keep track of them!" + LINE_SEPARATOR
            + "Here are the list of commands:" + LINE_SEPARATOR;
    public static final String TODO_HELP = "'todo' adds a task to do, without any constraints.";
    public static final String DEADLINE_HELP = "'deadline', with the valid format provided,"
            + " adds a task that has a deadline.";
    public static final String EVENT_HELP = "'event' adds a task with a location or destination,"
            + " i.e, where the task is at.";
    public static final String LIST_HELP = "'list' prints out your whole task list currently!";
    public static final String BYE_HELP = "'bye' ends the program- Don't worry, I would have saved your tasks!";
    public static final String MARK_AND_UNMARK_HELP = "'mark' and 'unmark' marks the task as done, "
            + "or not done respectively.";
    public static final String DELETE_HELP = "'delete' deletes a task, obviously.";
    public static final String FIND_HELP = "'find' is very useful when searching for a task with the given keyword,"
            + " especially when the list is long!";
    public static final String HELP_HELP = "'help' gives you an overview of our commands. You literally just used it.";
    //body of help.
    public static final String HELP_SUMMARY = "todo, deadline, and event are commands that adds the respective "
            + "type of task into your list." + LINE_SEPARATOR
            + TODO_HELP + LINE_SEPARATOR
            + DEADLINE_HELP + LINE_SEPARATOR
            + EVENT_HELP + LINE_SEPARATOR
            + LIST_HELP + LINE_SEPARATOR
            + BYE_HELP + LINE_SEPARATOR
            + MARK_AND_UNMARK_HELP + LINE_SEPARATOR
            + DELETE_HELP + LINE_SEPARATOR
            + FIND_HELP + LINE_SEPARATOR
            + HELP_HELP + LINE_SEPARATOR;
    //conclusion of Help.
    public static final String HELP_CONCLUSION = "Go ahead and manage and your tasks bro! I'll be here to help.";
    public static final String HELP_BODY = HELP_SUMMARY + HELP_CONCLUSION;

    public static String getMatchCountMsg (int matchCount) {
        return String.format("There are %d task(s) that match your keyword.", matchCount);
    }

    /**
     * Returns the message indicating the task number does not exist.
     *
     * @param taskNumber Task number that does not exist.
     * @return The message indicating that that task number does not exist.
     */
    public static String getOutOfBoundsMsg (int taskNumber) {
        return String.format("The task %d does not exist!", taskNumber);
    }

}
