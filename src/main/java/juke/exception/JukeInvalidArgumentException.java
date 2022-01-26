package juke.exception;

public class JukeInvalidArgumentException extends JukeException {
    private String expected;
    private String arg;
    
    public JukeInvalidArgumentException(String cmd, String expected, String arg) {
        super(cmd);
        this.expected = expected;
        this.arg = arg;
    }
    
    @Override
    public String getMessage() {
        return "Invalid argument for " + this.getCommand() + ". Expected " + this.expected
                + ". Instead got " + this.arg + ".";
    }
}
