package duke;

/**
 * Represents interaction with user.
 */
public class Ui {
    // greeting message
    private String greetings = "    Hi there! 👋 I'm Duke\n"
            + "    What can I do for you?";

    /**Method for greeting the user*/
    public void greet() {
        System.out.println(greetings);
    }
}
