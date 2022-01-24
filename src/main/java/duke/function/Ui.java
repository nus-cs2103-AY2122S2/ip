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
    Scanner sc;

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
     * Prints a list of commands the user can use to interact with Duke.
     */
    public void printHelp() {
        System.out.println("These are the commands you can use *quack*:");
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
     * Prints any provided message with Duke's personality.
     *
     * @param message The provided message.
     */
    public void print(String message) {
        System.out.println(String.format("%s *quack*", message));
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
