package spark.exceptions.fileexceptions;

/**
 * This is an exception that is thrown when modifications to the Task list
 * could not be saved to the user's save-file on the hard disk.
 */
public class WriteFileException extends FileException {
    public WriteFileException() {
        super("Save-File could not be written to!");
    }
}
