package SparkExceptions.FileExceptions;

public class WriteFileException extends FileException {
    public WriteFileException() {
        super("Save-File could not be written to!");
    }
}
