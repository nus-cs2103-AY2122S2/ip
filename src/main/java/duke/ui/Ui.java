package duke.ui;

public class Ui {

    /**
     * Print a welcome message.
     *
     * @return A String of welcome message.
     */
    public String welcomeMessage() {
        return ("Hello! I'm Duke\n" + "What can I do for you?");
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
