package gene.exception;

public class BadLocationDescException extends Exception {
    @Override
    public String getMessage() {
        return "--------------------------------------------------------\n"
                + ":( OOPS!!! The description of the location is badly formatted\n"
                + "Write it like this instead: \"location Here /at 138598, hostel\""
                + "\n--------------------------------------------------------";
    }
}
