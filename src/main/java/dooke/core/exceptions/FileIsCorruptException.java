package dooke.core.exceptions;

/**
 * Exception indicating that the file format/contents are corrupt.
 * @author s7manth
 * @version 0.3
 */
public class FileIsCorruptException extends DookeException {
    public FileIsCorruptException() {
        super("The file used to initialize the task list is corrupt!");
    }
}
