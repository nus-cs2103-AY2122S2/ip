package batman.ui;

public class Ui {

    private final String botName;

    /**
     * Handles the interaction with the user.
     *
     * @param botName Name of the chatbot
     */
    public Ui(String botName) {
        this.botName = botName;
    }

    /**
     * Displays the initialisation message.
     */
    public String greeting() {
        return "Hello! I'm " + botName + "\n" + "What can I do for you?\n";
    }

    /**
     * Displays the exit message.
     */
    public String exit() {
        return "Bye. This city needs me. na na na na na na BATMAN\n";
    }

    /**
     * Displays the error message.
     *
     * @param error Message of error
     */
    public String showLoadingError(String error) {
        return error;
    }
}
