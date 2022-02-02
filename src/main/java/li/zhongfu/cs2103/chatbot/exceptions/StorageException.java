package li.zhongfu.cs2103.chatbot.exceptions;

/**
 * Signals that a storage exception has occured, e.g. when loading or saving state to persistent storage.
 */
public class StorageException extends Exception {
    /**
     * Constructs a StorageException with the specified detail message.
     * 
     * @param message a String containing a detail message
     */
    public StorageException(String message) {
        super(message);
    }

    /**
     * Constructs a StorageException with the specified detail message and the cause of the exception.
     * 
     * @param message a String containing a detail message
     * @param cause a Throwable representing the cause of the exception
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
