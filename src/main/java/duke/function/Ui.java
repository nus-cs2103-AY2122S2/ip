package duke.function;

import java.util.Scanner;

import duke.exception.DukeException;

/**
 * To handle any input output interaction with users.
 */
public class Ui {
    /**
     * For receiving user input.
     */
    private Scanner sc;

    /**
     * Initializes a Ui instance with a scanner receiving input from the System input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the boot up message to greet users.
     */
    public void printBootUp() {
        System.out.println("Hello! I am Duck 🐥\nWhat can I do for you?");
    }

    /**
     * Prints the error message when saved tasks fail to load.
     */
    public void printLoadingError() {
        System.out.println("Since loading the save file has failed, your task list is currently empty");
    }

    /**
     * Prints the line separator surrounding Duke's messages.
     */
    public void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the message of the provided exception with Duke's personality.
     *
     * @param e The provided exception.
     */
    public void printException(DukeException e) {
        System.out.println(String.format("%s *quack*", e.getMessage()));
    }

    /**
     * Returns the message of the provided exception with Duke's personality.
     *
     * @param e The provided exception.
     * @return The error message after adding personality.
     */
    public String printExceptionReturn(DukeException e) {
        return String.format("%s *quack*", e.getMessage());
    }

    /**
     * Prints a list of commands the user can use to interact with Duke.
     */
    public void printHelp() {
        System.out.println("These are the commands you can use *quack*:\n");
        System.out.println("  'list' to list out all your tasks");
        System.out.println("  'todo <description>' to add a todo task");
        System.out.println("  'deadline /<preposition> <description>' to add a task with a deadline");
        System.out.println("  'event /<preposition> <description>' to add an event with a date");
        System.out.println("  'mark <task number>' to mark a task as done");
        System.out.println("  'unmark <task number>' to unmark a task as done");
        System.out.println("  'delete <task number>' to delete a task");
        System.out.println("  'bye' to close your Duck app");
        System.out.println("*quack*");
    }

    /**
     * Returns a list of commands the user can use to interact with Duke.
     *
     * @return List of commands.
     */
    public String printHelpReturn(Ui ui) {
        String output = "";
        output += ui.printReturn("These are the commands you can use:");
        output += ui.printReturn("  'list' to list out all your tasks");
        output += ui.printReturn("  'list --tags' to list out all your tasks with their tags");
        output += ui.printReturn("  'todo <description>' to add a todo task");
        output += ui.printReturn(
                "  'deadline <description> /<preposition> <YYYY-MM-DD><HH:MM>' to add a task with a deadline"
        );
        output += ui.printReturn(
                "  'event <description> /<preposition> <YYYY-MM-DD> <HH:MM>' to add an event with a date"
        );
        output += ui.printReturn("  'mark <task number>' to mark a task as done");
        output += ui.printReturn("  'unmark <task number>' to unmark a task as done");
        output += ui.printReturn("  'delete <task number>' to delete a task");
        output += ui.printReturn("  'tag <task number> <tag name>' to tag a task");
        output += ui.printReturn("  'bye' to close your Duck app");
        return output;
    }

    /**
     * Prints any provided message with Duke's personality.
     *
     * @param message The provided message.
     */
    public void print(String message) {
        System.out.println(String.format("%s *quack*", message));
    }

    /**
     * Returns any provided message with Duke's personality.
     *
     * @param message The provided message.
     * @return The message after adding personality.
     */
    public String printReturn(String message) {
        return String.format("%s *quack*\n", message);
    }

    /**
     * Receives user input from the command line.
     *
     * @return The user input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }
}
