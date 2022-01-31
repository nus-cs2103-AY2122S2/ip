package spark.exceptions.fileexceptions;

/**
 * This is an exception that is thrown when the user's save-file
 * on the hard-disk could not be read.
 */
public class ReadFileException extends FileException {
    public ReadFileException() {
        super("Save-File could not be read!");
    }
}
