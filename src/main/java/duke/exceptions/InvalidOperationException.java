package duke.exceptions;

public class InvalidOperationException extends Exception{
    private String error;

    public InvalidOperationException(String s) {
        this.error = s;
    }

    @Override
    public String toString() {
        return "This task is already " + error;
    }

}
