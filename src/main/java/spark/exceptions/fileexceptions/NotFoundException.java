package spark.exceptions.fileexceptions;

/**
 * This is an exception that is thrown when a user's save-file
 * is supposed to exist on the hard-disk but could not be located.
 */
public class NotFoundException extends FileException {
    public NotFoundException() {
        super("File could not be found at the specified relative file-path! Was it deleted or moved?");
    }
}
