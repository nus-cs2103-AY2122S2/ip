package core.exceptions;

/**
 * Exception indicating that the file format/contents are corrupt.
 */
public class FileIsCorruptException extends DookeException {
    public FileIsCorruptException() {
        super("The file used to initialize the task list is corrupt!");
    }
}
