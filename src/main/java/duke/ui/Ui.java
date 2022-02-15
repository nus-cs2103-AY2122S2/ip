package duke.ui;

/**
 * Represents the user interface that the user will interact with.
 */
public class Ui {

    /**
     * Formats a hardcoded start up message to the console to indicate to the user that the program has started.
     */
    public String formatStartUpMessage() {
        return "Hello I'm\n\n" + "\tDUKE\n\n" + "What can I do for you?\n";
    }

    /**
     * Prints any feedback message generated from commands given by the user with this.LINE to seperate the messages.
     */
    public String formatFeedbackMessage(String mes) {
        if (mes == null) {
            return "";
        }
        return mes;
    }
}
