package lily;

/**
 * Exceptions thrown by Lily-related functions
 * 
 * @@author bingsen0806-reused Referenced Bing Sen's ingenious and organised duke.exception.DukeException
 * Last Updated: Feb 2022 (AY21/22 Sem 2)
 */
public class LilyException extends Exception {
    public static final String ERROR_TOO_MANY_SEARCH_TERMS = "bro you can only find 1 word at a time";
    public static final String ERROR_ALREADY_MARKED = "bro this task is already marked.";
    public static final String ERROR_ALREADY_UNMARKED = "bro you haven't done this.";
    public static final String ERROR_404 = "bro, your list has nothing with that inside";
    public static final String ERROR_MISSING_INDEX = "can type that again? which task are you talking about";
    public static final String ERROR_MISSING_DESC = "can type that again? what is the task about?";
    public static final String ERROR_MISSING_DATE = "can type that again? when is it happening?";
    public static final String ERROR_UNKNOWN_COMMAND = "hey, i don't know what this command means";
    public static final String ERROR_UNKNOWN_TASK_TYPE = "hey, i don't know what kind of task this is";
    public static final String ERROR_OUT_OF_BOUNDS = "eh bro your list is shorter than that";
    public static final String ERROR_LOAD_FILE = "there's no save file.";
    public static final String ERROR_LOAD_FILE_UNKNOWN_CLASS = "you shouldn't see this, "
            + "but apparently there's a class that you're trying to load that doesn't exist in my program.";
    public static final String ERROR_WRITE_FILE = "I had trouble saving the file.";
    public static final String ERROR_QUIT_INTERRUPT = "oi I thought you said byebye already";
    public static final String ERROR_NO_DESC_TODO = "You gotta tell me what the todo is about!";
    public static final String ERROR_NO_DESC_DEADLN = "You gotta tell me what the deadline is about!";
    public static final String ERROR_NO_DESC_EVENT = "You gotta tell me what the event is about!";
    public static final String FORMAT_DATE = "can you say the date again in this form: year-mm-dd";
    public static final String FORMAT_IDX = "eh can you type its number instead?";

    /**
     * Creates a LilyException, which is an exception specific to the Lily application.
     * 
     * @param msg What the exception is about.
     */
    public LilyException(String msg) {
        super(msg);
    }
}