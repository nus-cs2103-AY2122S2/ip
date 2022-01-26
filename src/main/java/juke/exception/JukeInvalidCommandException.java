package juke.exception;

public class JukeInvalidCommandException extends JukeException {
    public JukeInvalidCommandException(String cmd) {
        super(cmd);
    }
    
    @Override
    public String getMessage() {
        return "Invalid command " + this.getCommand() + ". Did you mean to type something else?";
    }
}
