package duke.exceptions;

public class InvalidTaskTypeException extends DukeException {
    private String entry;

    public InvalidTaskTypeException(String entry){
        super(String.format("Invalid entry! Task type not specified.\n" +
                "You entered: %s",entry));
        this.entry = entry;
    }

    public String toString(){
        return String.format("Invalid entry! Task type not specified or invalid.\n" +
                "You entered: %s",entry);
    }
}
