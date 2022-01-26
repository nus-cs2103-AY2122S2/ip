//solution below adapted from https://stackify.com/specify-handle-exceptions-java/
package duke;
public class DeadlineException extends DukeException{
    public DeadlineException(String errorMessage) {
        super(errorMessage);
    }
}
