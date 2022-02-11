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
    public static final String ERROR_MISSING_INDEX = "bro, your list has nothing with that inside";
    public static final String ERROR_OUT_OF_BOUNDS = "eh bro your list is shorter than that";
    public static final String ERROR_LOAD_FILE = "There's no save file.";
    public static final String ERROR_LOAD_FILE_UNKNOWN_CLASS = "You shouldn't see this, "
            + "but apparently there's a class that you're trying to load that doesn't exist in my program.";
    public static final String ERROR_WRITE_FILE = "I had trouble saving the file.";
    public static final String ERROR_QUIT_INTERRUPT = "oi I thought you said byebye already";
    public static final String FORMAT_DATE = "can you say the date again in this form: year-mm-dd";
    public static final String FORMAT_IDX = "eh can you type its number instead?";
    /**
     * Creates a LilyException, which is an exception specific to Lily.
     * 
     * @param msg What the exception is about.
     */
    public LilyException(String msg) {
        super(msg);
    }
}