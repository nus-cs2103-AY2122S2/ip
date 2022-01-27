package duke.exception;

/**
 * Custom exception class for printing set error texts.
 */
public class DukeException extends Exception{
    /**
     * Exception constructor.
     *
     * @param str Error message
     */
    public DukeException(String str){
        super(str);
    }
}