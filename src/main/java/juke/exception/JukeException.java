package juke.exception;

/**
 * Class for Juke related exceptions.
 */
public class JukeException extends Exception {
    private String cmd;
    
    /**
     * Constructor to initialize exception with a message.
     *
     * @param cmd Message.
     */
    public JukeException(String cmd) {
        this.cmd = cmd;
    }
    
    protected String getCommand() {
        return this.cmd;
    }
    
    /**
     * Returns exception message.
     *
     * @return Message.
     */
    @Override
    public String getMessage() {
        return this.getCommand();
    }
}