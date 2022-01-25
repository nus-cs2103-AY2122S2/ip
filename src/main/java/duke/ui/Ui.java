package duke.ui;

import duke.DukeException;

public class Ui {

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

    public void endProgram() {
        drawDivider();
        System.out.println("Bye. Hope to see you again soon!");
        drawDivider();
    }

    public void errorMessage(DukeException e) {
        drawDivider();
        System.out.println(e.getMessage());
        drawDivider();
    }

    public static void drawDivider() {
        System.out.println("________________________________________");
    }
}
