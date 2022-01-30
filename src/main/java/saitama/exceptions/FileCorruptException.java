package saitama.exceptions;

/**
 * An exception to be thrown when the stored data is corrupted.
 */
public class FileCorruptException extends SaitamaException {
    public FileCorruptException() {
        super("The saved data is corrupted! Returning new task list...");
    }
}
