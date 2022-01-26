package exceptions;

public class ToDosException extends TaskException {
    public ToDosException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "The description of a todo cannot be empty.";
    }
}
