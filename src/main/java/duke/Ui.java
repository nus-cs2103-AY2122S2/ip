package duke;

public class Ui {
    private String welcomeMessage;
    private String reminders;
    private String byeMessage;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        welcomeMessage =
                "       __  \n"
                        + "(____()'`; \n"
                        + "/,    /` \n"
                        + "\\\\\"--\\\\\n"
                        + "Woof, I am (supposed to look like) a dog bot.\n"
                        + "What do you want from me?";

        byeMessage = "Bye! Hope not to see you again :)";
    };

    /**
     * Prints a message informing user an error with loading of file.
     */
    public void showLoadingError() {
        System.out.println("Error loading file! A new list will be created.");
    }
    /**
     * Prints a welcome message to user.
     */
    public String getWelcomeMessage() {
        return welcomeMessage;
    }
    /**
     * Prints a message to signal the termination of program.
     */
    public String getByeMessage() {
        return byeMessage;
    }
}
