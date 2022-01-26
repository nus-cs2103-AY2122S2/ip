package juke.exception;

public class JukeInvalidArgumentCountException extends JukeException {
    private int expected;
    private int count;
    
    public JukeInvalidArgumentCountException(String cmd, int expected, int count) {
        super(cmd);
        this.expected = expected;
        this.count = count;
    }
    
    @Override
    public String getMessage() {
        return "Invalid number of arguments for " + this.getCommand() + ". Expected " + this.expected
            + ". Instead got " + this.count + ".";
    }
}
