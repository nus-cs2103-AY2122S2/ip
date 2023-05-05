package spark.commandresponse;

/**
 * Encapsulates the result of running a Command that runs successfully
 * but may potentially have some error.
 */
public class WarningResponse extends CommandResponse {
    /**
     * Creates a new WarningResponse instance containing
     * the specified message to be displayed to the user
     * in the GUI.
     *
     * @param message
     */
    public WarningResponse(String message) {
        super(message);
    }
    @Override
    public boolean isWarning() {
        return true;
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
