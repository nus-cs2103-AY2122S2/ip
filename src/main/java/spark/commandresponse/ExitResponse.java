package spark.commandresponse;

/**
 * Encapsulates the result of running a Command successfully.
 */
public class ExitResponse extends CommandResponse {
    private static final String GOODBYE_MESSAGE = "Okay bye!";
    /**
     * Creates a new ExitResponse instance containing
     * the goodbye message to be shown to the user
     * before exiting.
     */
    public ExitResponse() {
        super(GOODBYE_MESSAGE);
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
        return true;
    }
}
