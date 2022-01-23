package SparkExceptions.FileExceptions;

import SparkExceptions.SparkException;

public class FileException extends SparkException {
    public FileException(String s) {
        super(String.format("Encountered an issue while handling save-file: %s", s));
    }
}
