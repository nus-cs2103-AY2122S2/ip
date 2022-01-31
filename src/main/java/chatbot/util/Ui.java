package chatbot.util;

import java.util.Random;

/**
 * Represents a user interaction handler.
 */
public class Ui {

    private static final String BORDER =
            "************************************************************************************************"
                    + "**********************";
    private static final String[] GREETING_QUOTES = {
        "Welcome to my inn",
        "Pull up a chair by the hearth!",
        "Come in, and shut the door, it's cold out there!",
        "Don't be scared. Come in, have a seat!",
    };
    private static final String[] GUIDE = {
        "list                                                            View your task list",
        "get <date*>                                                     View the tasks that you have "
                                                                                + "on the specified date",
        "todo <name of task>                                             Add a todo to your task list",
        "deadline <name of task> /by <date* or timestamp* of task>       Add a deadline to your task list",
        "event <name of task> /at <date* or timestamp* of task>          Add an event to your task list",
        "mark <index of task>                                            Mark a task as completed in your task list",
        "unmark <index of task>                                          Unmark a task in your task list",
        "bye                                                             Exit the program",
        "* date format is d/M/YYYY                                          eg; 24/4/2022",
        "* timestamp format is d/M/YYYY HHmm                                eg; 23/3/2022 1800",
    };

    private final Random random;

    /**
     * Instantiates a new Ui.
     */
    public Ui() {
        this.random = new Random();
    }

    /**
     * Greets the user when the ChatBot is started up.
     *
     * @param isEmpty Boolean indicating whether the user's task list is empty or not.
     */
    public void greet(boolean isEmpty) {
        System.out.println(BORDER + "\n");
        chat("Greetings, traveller!");
        // chat(getRandomGreetingQuote());
        if (isEmpty) {
            chat(
                    "I'm the innkeeper and im here to help you with whatever you need."
            );
        } else {
            chat("I've loaded up the tasks from your save file!");
        }

    }

    /**
     * Bids farewell to the user when the ChatBot is closed.
     */
    public void bye() {
        chat("Goodbye traveller! Hope to see you again soon!");
        System.out.println(BORDER);
    }

    /**
     * Outputs a message from the ChatBot to the user.
     *
     * @param message The message.
     */
    public void chat(String message) {
        System.out.print("Innkeeper: ");
        System.out.println(message + "\n");
    }

    /**
     * Print a formatted message.
     *
     * @param message The message.
     */
    public void print(String message) {
        if (message.startsWith(" ")) {
            chat("Here you go!");
            System.out.println(message);
        } else {
            chat(message);
        }
    }

    /**
     * Prompts the user for a command input.
     */
    public void prompt() {
        System.out.println(BORDER + "\n");
        chat("What can I do for you today?");
        System.out.println(BORDER);
        System.out.print("\nYou: ");
    }

    /**
     * Indicates to the user that some error has occurred.
     *
     * @param errorMessage The error message.
     */
    public void error(String errorMessage) {
        chat(errorMessage);
        chat("You can type guide for a list of valid commands to use!");
    }

    private String getRandomGreetingQuote() {
        int randomIndex = random.nextInt(GREETING_QUOTES.length);
        return GREETING_QUOTES[randomIndex];
    }

    /**
     * Prints the number of tasks in the user's task list.
     *
     * @param numTasks The number tasks in the user's task list.
     */
    public void printNumTasks(int numTasks) {
        chat(
                String.format(
                        "You now have %d %s!",
                        numTasks,
                        numTasks == 1 ? "task" : "tasks"
                )
        );
    }

    /**
     * Prints a guide for the user containing all valid commands and their formats.
     */
    public void printGuide() {
        chat("Here is a list of commands that you can use traveller!");
        for (int i = 0; i < GUIDE.length - 2; i++) {
            System.out.printf(
                    "             %d. %s%n", i + 1, GUIDE[i]
            );
        }
        System.out.println();
        for (int i = GUIDE.length - 2; i < GUIDE.length; i++) {
            System.out.printf("             %s%n", GUIDE[i]);
        }
        System.out.println();
    }
}
