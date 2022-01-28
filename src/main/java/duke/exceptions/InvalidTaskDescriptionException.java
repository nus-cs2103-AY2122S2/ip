package duke.exceptions;

public class InvalidTaskDescriptionException extends DukeException {
    private String type;

    public InvalidTaskDescriptionException(String type){
        super(String.format("Invalid entry! Missing description when creating %s.",type));
        this.type = type;
    }

    public String toString(){
        return String.format("Invalid entry! Missing description when creating %s.",this.type);
    }
}
