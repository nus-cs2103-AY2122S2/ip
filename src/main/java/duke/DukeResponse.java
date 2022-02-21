package duke;

/**
 * Represents the Duke chatbot's response to a user query.
 */
public class DukeResponse {
    private final String message;
    private final boolean isExit;

    /**
     * Returns a DukeResponse object that contains the Duke chatbot's
     * response to a user query.
     *
     * @param message the Duke chatbot's reply to a user query.
     * @param isExit indicates whether a Duke chatbot should exit after processing a user query.
     */
    public DukeResponse(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    /**
     * Returns the message of the Duke chatbot's response to the
     * current user query.
     *
     * @return The Duke chatbot's response message to the user.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Determines whether the Duke chatbot should exit after
     * processing the current user query.
     *
     * @return Whether the Duke chatbot should exit after processing
     * the current user query.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
