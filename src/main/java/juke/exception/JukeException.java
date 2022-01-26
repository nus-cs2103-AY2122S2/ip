package juke.exception;

public class JukeException extends Exception {
    private String cmd;
    
    public JukeException(String cmd) {
        this.cmd = cmd;
    }
    
    protected String getCommand() {
        return this.cmd;
    }
    
    @Override
    public String getMessage() {
        return "Exception with " + this.getCommand();
    }
}