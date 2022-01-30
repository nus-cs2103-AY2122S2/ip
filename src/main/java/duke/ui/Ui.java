package duke.ui;

import java.util.Scanner;

/**
 * Text UI of the chatbot.
 */
public class Ui {
    private static final String divider = "    ============================================================";
    private final Scanner userInput;

    /**
     * Creates a new UI object.
     */
    public Ui() {
        userInput = new Scanner(System.in);
    }

    /**
     * Adds four empty white spaces to an input.
     *
     * @param s the target input
     * @return input with prefix of four empty white spaces
     */
    public String tab(String s) {
        return "    " + s;
    }

    /**
     * Replies the user.
     *
     * @param reply the message to be replied
     */
    public void replyUser(String reply) {
        String s = reply.replace("\n", "\n    ");

        showLine();
        System.out.println(tab(s));
        showLine();
    }

    /**
     * Greets the user
     */
    public void greetUser() {
        String poogie = "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⠉⠉⠉⠉⠉⠉⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n"
                + "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⢀⣠⣶⣶⣶⣶⣤⡀⠄⠄⠹⣿⣿⣿⣿⣿⣿⣿⣿\n"
                + "    ⣿⣿⣿⣿⣿⣿⣿⣿⡏⠄⠄⣾⡿⢿⣿⣿⡿⢿⣿⡆⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿\n"
                + "    ⣿⣿⣿⣿⣿⣿⣿⡿⠃⠄⠄⢿⣇⣸⣿⣿⣇⣸⡿⠃⠄⠄⠸⣿⣿⣿⣿⣿⣿⣿\n"
                + "    ⣿⣿⣿⣿⣿⡿⠋⠄⠄⠄⠄⠄⠉⠛⠛⠛⠛⠉⠄⠄⠄⠄⠄⠄⠙⣿⣿⣿⣿⣿\n"
                + "    ⣿⣿⣿⣿⡟⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⣿⣿\n"
                + "    ⣿⣿⣿⡟⠄⠄⠄⠠⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⣿\n"
                + "    ⣿⣿⡟⠄⠄⠄⢠⣆⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⣧⠄⠄⠄⠈⢿⣿\n"
                + "    ⣿⣿⡇⠄⠄⠄⣾⣿⡀⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢰⣿⣧⠄⠄⠄⠘⣿\n"
                + "    ⣿⣿⣇⠄⣰⣶⣿⣿⣿⣦⣀⡀⠄⠄⠄⠄⠄⠄⠄⢀⣠⣴⣿⣿⣿⣶⣆⠄⢀⣿\n"
                + "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⠄⢸⣿⠇⠄⠄⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n"
                + "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣤⣴⣾⣿⣶⣤⣤⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n";
        System.out.println(poogie);
        replyUser("Hello! My name is Poogie.\n" + "How may I be of service to you?");
    }

    /**
     * Prints a divider line
     */
    public void showLine() {
        System.out.println(divider);
    }

    /**
     * Reads input from the user
     */
    public String readCommand() {
        return userInput.nextLine();
    }
}
