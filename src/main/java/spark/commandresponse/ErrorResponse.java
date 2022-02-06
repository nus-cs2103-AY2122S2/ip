package spark.commandresponse;

import spark.exceptions.SparkException;

/**
 * Encapsulates the result of running a Command unsuccessfully
 */
public class ErrorResponse extends CommandResponse {
    /**
     * Creates a new ErrorResponse instance containing
     * the error message to be displayed to the user
     * in the GUI.
     *
     * @param error
     */
    public ErrorResponse(SparkException error) {
        super(error.getMessage());
    }

    @Override
    public boolean isWarning() {
        return false;
    }
    @Override
    public boolean isError() {
        return true;
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
