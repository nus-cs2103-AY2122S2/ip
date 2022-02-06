package spark.commandresponse;

/**
 * Encapsulates the result of running a Command successfully.
 */
public class SuccessResponse extends CommandResponse {
    /**
     * Creates a new SuccessResponse instance containing
     * the specified message to be displayed to the user
     * in the GUI.
     *
     * @param message
     */
    public SuccessResponse(String message) {
        super(message);
    }
    @Override
    public boolean isWarning() {
        return false;
    }
    @Override
    public boolean isError() {
        return false;
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
