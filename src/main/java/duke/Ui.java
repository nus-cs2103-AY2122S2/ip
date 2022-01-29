package duke;

public class Ui {
    private String welcomeMessage;
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
                + "Woof, I am (supposed to look like) a dog bot.\nWhat do you want from me?\n";

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
    public void printWelcomeMessage() {
        System.out.println(welcomeMessage);
    }
    /**
     * Prints a message to signal the termination of program.
     */
    public void printByeMessage() {
        System.out.println(byeMessage);
    }
}
