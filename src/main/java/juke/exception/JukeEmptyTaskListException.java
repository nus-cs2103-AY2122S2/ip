package juke.exception;

public class JukeEmptyTaskListException extends JukeException {
    public JukeEmptyTaskListException() {
        super("Task list is empty.");
    }
}
