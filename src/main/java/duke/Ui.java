package duke;

public class Ui {
    // greeting message
    private String greetings = "    Hi there! 👋 I'm Duke\n"
            + "    What can I do for you?";

    // divider
    private final String LINES = "    ---------------------------------";

    /**Method for greeting the user*/
    public void greet() {
        System.out.println(LINES);
        System.out.println(greetings);
        System.out.println(LINES);
    }
}
