package duke;

/**
 * The UiForGUI class deals with the interactions with the user made via the GUI.
 */
public class UiForGUI {
    private boolean isResponding;
    private String msg;

    public UiForGUI() {
        this.isResponding = false;
    }

    public String getResponse() {
        if (this.isResponding) {
            return this.msg;
        }
        return "";
    }

    public void setIsResponding(boolean isResponding) {
        this.isResponding = isResponding;
    }

    /**
     * Displays the error message of the bot application.
     *
     * @param errorMsg the error message to be displayed.
     */
    public void showError(String errorMsg) {
        this.setIsResponding(true);
        this.msg = errorMsg;
    }

    /**
     * Displays the goodbye message of the bot application.
     */
    public void showGoodbye() {
        this.setIsResponding(true);
        this.msg = "Okay, bye! Hope to see you again :)";
    }

    /**
     * Displays the intended message.
     *
     * @param msg a String containing the message to be displayed.
     */
    public void printMsg(String msg) {
        this.setIsResponding(true);
        this.msg = msg;
    }

}
