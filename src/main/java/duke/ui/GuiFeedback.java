package duke.ui;

/**
 * Represents a feedback from the internal duke system to the GUI, contains
 * any output string and information on whether the system should end.
 */
public class GuiFeedback {
    protected String outputString;
    protected boolean isExit;
    protected boolean isError;
    /**
     * Constructor of GuiFeedback.
     * @param outputString information on any String required to be displayed to the user.
     * @param isExit information on whether the program should exit.
     */
    public GuiFeedback(String outputString, boolean isExit, boolean isError) {
        this.outputString = outputString;
        this.isExit = isExit;
        this.isError = isError;
    }
}
