public class PacException extends Exception{
    PacException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Sorry! " + super.getMessage();
    }
}
