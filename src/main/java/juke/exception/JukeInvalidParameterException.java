package juke.exception;

public class JukeInvalidParameterException extends JukeException {
    
    public JukeInvalidParameterException(String param) {
        super(String.format("Invalid parameter: -%s.", param));
    }
}
