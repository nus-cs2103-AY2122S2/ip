package duke.exceptions;

public class InvalidTaskDataTimeException extends DukeException {
    private String type;

    public InvalidTaskDataTimeException(String type){
        super(String.format("Invalid entry! Missing Date/Time when creating %s.",type));
        this.type = type;
    }

    public String toString(){
        return String.format("Invalid entry! Missing Date/Time when creating %s.",this.type);
    }
}
