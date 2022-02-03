package duke.constant;

/**
 * A class that contains constants related to message printing.
 */
public final class Message {
    public static final String LINE_PREFIX = "";
    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    public static final String SPACE = " ";
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String VERTICAL_BAR_WITH_SPACE = " | ";
    public static final String VERTICAL_BAR_REGEX = " \\| ";
    public static final String HORIZONTAL_LINE = "";
    public static final String GOOD_BYE = LINE_PREFIX + "Bye. Hope to see you again soon!";
    public static final String GREETING = LINE_PREFIX + "Hello! I'm Duke" + LINE_SEPARATOR + LINE_PREFIX + "What can I do for you?";
    public static final String LIST_TASK = LINE_PREFIX + "Here are the tasks in your list:";
    public static final String NO_TASK = LINE_PREFIX + "There are no tasks in your list";
    public static final String CONFIRM_MARK = LINE_PREFIX + "Nice! I've marked this task as done:";
    public static final String CONFIRM_UNMARK = LINE_PREFIX + "OK, I've marked this task as not done yet:";
    public static final String NO_TASK_MATCHED = LINE_PREFIX + "There are no tasks matching the keyword.";
    public static final String FOUND_TASK = LINE_PREFIX + "Here are the matching tasks in your list:";

    public static final String MESSAGE_EMPTY_TASK_EXCEPTION = LINE_PREFIX + "☹ OOPS!!! Missing arguments.";
    public static final String MESSAGE_MISSING_DATE_TIME_EXCEPTION = LINE_PREFIX + "☹ OOPS!!! Missing date/time. Please specify it.";
    public static final String MESSAGE_INVALID_INDEX_EXCEPTION = LINE_PREFIX + "☹ OOPS!!! Invalid index.";
    public static final String MESSAGE_UNKNOWN_COMMAND_EXCEPTION = LINE_PREFIX + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_DUKE_EXCEPTION = LINE_PREFIX + "☹ OOPS!!! ERROR!!!";
    public static final String MESSAGE_FILE_NOT_FOUND = LINE_PREFIX + "☹ OOPS!!! File not found.";
    public static final String MESSAGE_DATE_TIME_FORMAT_EXCEPTION = LINE_PREFIX + "☹ OOPS!!! Wrong date/time format.";
}
