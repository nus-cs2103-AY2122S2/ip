package juke.exception;

/**
 * Class for Juke related exceptions.
 */
public class JukeException extends Exception {
    
    /**
     * Constructor to initialize exception with a message.
     *
     * @param msg Message.
     */
    public JukeException(String msg) {
        super(msg);
    }
    
    /**
     * Returns exception message.
     *
     * @return Message.
     */
    @Override
    public String getMessage() {
        return String.format("[ERROR] %s", super.getMessage());
    }
}