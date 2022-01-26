package juke.exception;

public class JukeTaskListEmptyException extends JukeException {
    public JukeTaskListEmptyException(String cmd) {
        super(cmd);
    }
    
    @Override
    public String getMessage() {
        return "Task list is empty, " + this.getCommand() + " could not display list of tasks.";
    }
}
