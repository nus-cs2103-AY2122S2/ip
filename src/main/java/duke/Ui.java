package duke;

import java.util.ArrayList;

/**
 * This class provides support the user interface, and to print messages out
 * neatly for user.
 */

public class Ui {

    private Parser parser;
    private String line = "____________________________________________________________";
    private String indentation = "    ";

    /**
     * Constructor for the user interface, will print the logo of the program on the terminal.
     */
    Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(indentation + line);
        System.out.println(indentation + "Hello! I'm Duke\n" + indentation
                + "What can I do for you?\n" + indentation
                + "Type /help to see the commands that I can run :)");
        System.out.println(indentation + line);
        parser = new Parser();
    }

    /**
     * Shows an error message when there is an error loading the file.
     */
    public void showLoadingError() {
        String message = indentation + "There is an error trying to load previous file.";
        System.out.println(indentation + line);
        System.out.println(indentation + message);
        System.out.println(indentation + line);
    }

    /**
     * Shows an error message that the user has typed in a wrong command.
     */
    public void showWrongCommand() {
        System.out.println(indentation + line);
        System.out.println(indentation + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(indentation + line);
    }

    /**
     * Shows an error message that the user has typed in the correct command, but in a wrong format.
     *
     * @param methodType the type of command that the user has entered.
     */
    public void showWrongFormat(String methodType) {
        System.out.println(indentation + line);
        System.out.println(indentation
                + " OOPS!!! You have entered an incorrect format of " + methodType);
        System.out.println(indentation + line);
    }

    /**
     * To present any messages to the user, enclosed in 2 lines.
     * @param message the message that is to be printed.
     */
    public void outputMessage(String message) {
        System.out.println(indentation + line);
        System.out.println(indentation + message);
        System.out.println(indentation + line);
    }

    /**
     * To print all the tasks current user has neatly.
     * @param stringArrayList the ArrayList of tasks to be printed.
     */
    public String printList(ArrayList<String> stringArrayList) {
        String string = new String();
        System.out.println(indentation + line);
        for (String s: stringArrayList) {
            System.out.println(indentation + s);
            string.concat(s);
        }
        System.out.println(indentation + line);
        return string;
    }

}
