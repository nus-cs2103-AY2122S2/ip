package funbox;

public class Result {
    private String response;
    private boolean isExit;

    /**
     * The constructor for the result class.
     *
     * @param response The response to be displayed to the user.
     * @param isExit Whether the command is an exit command.
     */
    public Result(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    public String getResponse() {
        return this.response;
    }

    /**
     * Check if the result should close the GUI.
     *
     * @return Returns true if the commAnd is to exit, otherwise, returns false.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
