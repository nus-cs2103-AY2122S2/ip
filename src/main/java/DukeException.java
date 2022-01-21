/**
 * The type Duke exception.
 */
public class DukeException extends Exception{
    /**
     * Instantiates a new Duke exception.
     *
     * @param message the message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Exception response.
     */
    public void exceptionResponse() {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";

        System.out.println(divString);
        System.out.println(strPadding + this);
        System.out.println(divString);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
