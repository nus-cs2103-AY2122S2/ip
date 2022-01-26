package duke.exceptions;

public class TaskIndexException extends Exception{
    private String message;

    public TaskIndexException(String text){
        this.message = text;
    }

    @Override
    public String toString() {
        return "OOPS!!! You didn't give me a proper " + this.message +" typed task to include!";
    }
}
