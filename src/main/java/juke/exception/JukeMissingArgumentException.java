package juke.exception;

public class JukeMissingArgumentException extends JukeException {
    
    public JukeMissingArgumentException(String cmd) {
        super(String.format("Missing argument for command %s.", cmd));
    }
}