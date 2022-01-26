package juke.exception;

public class JukeMissingArgumentException extends JukeException {
    private String expected;
    
    public JukeMissingArgumentException(String cmd, String expected) {
        super(cmd);
        this.expected = expected;
    }
    
    @Override
    public String getMessage() {
        return "Missing argument for " + this.getCommand() + ". Expected " + this.expected + ".";
    }
}