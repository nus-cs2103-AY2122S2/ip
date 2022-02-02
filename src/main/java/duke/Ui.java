package duke;

import java.util.Scanner;

/**
 * Handles interaction with user
 */
public class Ui {
    /**
     * Scanner to read input of user
     */
    private static Scanner sc = new Scanner(System.in);


    /**
     * Gets Command from user and parses it
     *
     * @return Command enum representing command by user
     */
    public static Command getCommand() {
        Command actionType = null;
        while (actionType == null) {
            try {
                String command = sc.next();
                actionType = Parser.parseCommand(command);
            } catch (CommandNotFoundException e) {
                System.out.println("Sorry, i don't understand what you are saying");
                actionType = null;
                sc.nextLine();
            }
        }
        return actionType;
    }

    /**
     * Returns parsed inputs from user
     * Index 0: Description of task
     * Index 1: Time
     *
     * @return String array with parsed inputs from user
     */
    public static String[] getUserInputs() {
        String input = sc.nextLine();
        return Parser.parseInput(input);
    }

    /**
     * Prints Welcome message to user upon startup
     */
    public static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Welcome to Duke! \nWhat can i do for you?\n");
    }
}
