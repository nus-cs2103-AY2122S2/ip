package spark.exceptions.fileexceptions;

import spark.exceptions.SparkException;

/**
 * This is an exception that is thrown whenever there are any
 * issues encountered when accessing and modifying the save file
 * on the user's hard disk.
 */
public abstract class FileException extends SparkException {
    public FileException(String s) {
        super(String.format("Encountered an issue while handling save-file: %s", s));
    }
}
