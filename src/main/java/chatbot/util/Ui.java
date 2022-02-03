package chatbot.util;

import java.util.Random;


/**
 * Represents a user interaction handler.
 */
public class Ui {

    private static final String[] GREETING_QUOTES = {
        "Welcome to my inn!",
        "Pull up a chair by the hearth!",
        "Come in, and shut the door, it's cold out there!",
        "Don't be scared. Come in, have a seat!",
    };
    private static final String[] GUIDE = {
        "list\n     -- View your task list",
        "get <date*>\n     -- View the tasks that you have on the specified date",
        "todo <name of task>\n     -- Add a todo to your task list",
        "deadline <name of task> /by <date* or timestamp* of task>\n     -- Add a deadline to your task list",
        "event <name of task> /at <date* or timestamp* of task>\n     -- Add an event to your task list",
        "mark <index of task>\n     -- Mark a task as completed in your task list",
        "unmark <index of task>\n     -- Unmark a task in your task list",
        "clear\n     -- Clear the screen",
        "bye\n     -- Exit the program",
        "* date format is d/M/YYYY\n     -- eg; 24/4/2022",
        "* timestamp format is d/M/YYYY HHmm\n     -- eg; 23/3/2022 1800",
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
     * @return The greeting.
     */
    public String greet(boolean isEmpty) {
        String greeting = "Greetings traveller!\n";
        greeting = greeting.concat(getRandomGreetingQuote());
        if (isEmpty) {
            greeting = greeting.concat("\nI'm the innkeeper and im here to help you with whatever you need.");
        } else {
            greeting = greeting.concat("\nI've loaded up the tasks from your save file!");
        }
        return greeting;
    }

    /**
     * Bids farewell to the user when the ChatBot is closed.
     */
    public String bye() {
        return "Goodbye traveller! Hope to see you again soon!\nThe application will close shortly.";
    }

    /**
     * Indicates to the user that some error has occurred.
     *
     * @param errorMessage The error message.
     * @return The error message and a suggestion to use the guide command.
     */
    public String error(String errorMessage) {
        return errorMessage.concat("\nYou can type guide for a list of valid commands to use!");
    }

    private String getRandomGreetingQuote() {
        int randomIndex = random.nextInt(GREETING_QUOTES.length);
        return GREETING_QUOTES[randomIndex];
    }

    /**
     * Prints the number of tasks in the user's task list.
     *
     * @param numTasks The number tasks in the user's task list.
     * @return A message indicating the number of tasks in the user's task list.
     */
    public String printNumTasks(int numTasks) {
        return String.format(
                        "You now have %d %s!",
                        numTasks,
                        numTasks == 1 ? "task" : "tasks"
                );
    }

    /**
     * Prints a guide for the user containing all valid commands and their formats.
     *
     * @return The guide.
     */
    public String printGuide() {
        String guide = "Here is a list of commands that you can use traveller!";
        for (int i = 0; i < GUIDE.length - 2; i++) {
            guide = guide.concat(String.format("\n%d. %s%n", i + 1, GUIDE[i]));
        }
        guide = guide.concat("\n");
        for (int i = GUIDE.length - 2; i < GUIDE.length; i++) {
            guide = guide.concat(String.format("\n%s%n", GUIDE[i]));
        }
        guide = guide.concat("\n");
        return guide;
    }
}
