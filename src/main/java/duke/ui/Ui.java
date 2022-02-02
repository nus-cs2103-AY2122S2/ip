package duke.ui;

import duke.exception.DukeException;

public class Ui {

    /**
     * Print a welcome message.
     */
    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawDivider();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        drawDivider();
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
     * Print an error message based on the exception's message.
     *
     * @param e DukeException.
     */
    public void errorMessage(DukeException e) {
        drawDivider();
        System.out.println(e.getMessage());
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
