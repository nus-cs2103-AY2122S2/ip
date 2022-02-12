package duke;

/**
 * Response of a user input.
 */
public class Response {

    private final boolean isExit;
    private final String response;

    /**
     * Constructs a {@code Response} object.
     * @param isExit Boolean indicating whether the program should be exited
     * @param response The string response from Duke
     */
    public Response(boolean isExit, String response) {
        this.isExit = isExit;
        this.response = response;
    }

    /**
     * Indicates whether the program should be exited.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns the string response from Duke.
     */
    public String getResponse() {
        return response;
    }

}
