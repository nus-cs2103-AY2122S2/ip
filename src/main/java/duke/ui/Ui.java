package duke.ui;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a message to the user.
     *
     * @param message message to be printed.
     */
    public void send(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message.trim());
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the default greeting.
     */
    public String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = String.format("%s%nHello! I'm Duke\nWhat can I do for you?%n", logo);
        return greeting;
    }

    /**
     * Retrieves the next input from the user.
     *
     * @return input from the user.
     */
    public String getNextLine() {
        return scanner.nextLine();
    }

    /**
     * Exits from the program.
     */
    public void exit() {
        send("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
