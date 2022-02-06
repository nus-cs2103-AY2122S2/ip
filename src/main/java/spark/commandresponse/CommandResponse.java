package spark.commandresponse;

/**
 * Encapsulates the result produced by Spark after running a Command
 */
public abstract class CommandResponse {
    private String message;

    /**
     * Creates a new Command instance containing
     * the specified message to be displayed to the user
     * in the GUI.
     *
     * @param message
     */
    public CommandResponse(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
    public abstract boolean isWarning();
    public abstract boolean isError();
    public abstract boolean isExit();
}
