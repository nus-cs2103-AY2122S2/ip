package duke.ui;

public class Ui {

    /**
     * Print a welcome message.
     *
     * @return A String of welcome message.
     */
    public String welcomeMessage() {
        return ("Hello Mr Rabbit! I am Alice.\n"
                + "What do you want to accomplish today?\n"
                + "Type 'help' to see the list of commands and learn about this app");
    }

    /**
     * Print an end program message.
     */
    public void endProgram() {
        drawDivider();
        System.out.println("Bye. Hope to see you again soon!");
        drawDivider();
    }

    /**
     * Print a divider.
     */
    public static void drawDivider() {
        System.out.println("________________________________________");
    }

    public String endProgramFX() {
        return "Bye. Hope to see you again soon!";
    }
}
