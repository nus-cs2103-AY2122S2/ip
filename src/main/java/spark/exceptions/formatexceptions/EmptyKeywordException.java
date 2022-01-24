package spark.exceptions.formatexceptions;

public class EmptyKeywordException extends FormatException {
    public EmptyKeywordException() {
        super("No search keyword found! You have to give me something to search for :(");
    }
}
