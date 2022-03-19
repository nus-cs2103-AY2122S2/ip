package pac;

public class PacException extends Exception{
    public PacException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Sorry! " + super.getMessage();
    }
}
