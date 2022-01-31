package spark.exceptions.formatexceptions;

/**
 * This is an exception that is thrown when the user inputs an empty
 * search term when attempting to find Tasks by their title.
 */
public class EmptyKeywordException extends FormatException {
    public EmptyKeywordException() {
        super("No search keyword found! You have to give me something to search for :(");
    }
}
