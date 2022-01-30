package duke.ui;

/**
 * Represents the user interface that the user will interact with.
 */
public class Ui {
    private static final String LINE = "\t____________________________________________________________";

    /**
     * Prints a hardcoded start up message to the console to indicate to the user that the program has started.
     */
    public String formatStartUpMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello I'm\n" + logo + "\n" + "What can I do for you?\n" + LINE;
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
