package jarvis;

/**
 * The Ui programs implements an application that simply displays responses given a user input.
 *
 * @author joey-chance
 * @version 1.0
 * @since 2022-02-05
 */
public class Ui {
    /**
     * Displays a greeting to the standard output.
     */
    public static String greet() {
        /* String logo =
                " ------    -------    -------   ---        ---   ---------    -------\n" +
                        "|____  |  |   _   |  |   _   |  \\  \\      /  /  |___   ___|  |   ____|\n" +
                        "     | |  |  |_|  |  |  |_|  |   \\  \\    /  /       | |      |  |____ \n" +
                        " _   | |  |   _   |  |      _|    \\  \\  /  /        | |      |_____  |\n" +
                        "| |__| |  |  | |  |  |  |\\  \\      \\  \\/  /      ___| |___    ____|  |\n" +
                        "|______|  |__| |__|  |__| \\__\\      \\____/      |_________|  |_______|\n"
                ;
         */
        return "Welcome home, sir.\n";
    }

    /**
     * Displays message to the standard output when user inputs "list" as command.
     */
    public static String list() {
        return "Here are the tasks on your list, sir.";
    }

    /**
     * Displays a message to the standard output when the task was already done but the user tried to mark it as done.
     */
    public static String alreadyDone(Task task) {
        return "This task was already done to begin with, sir: \n" + print(task);
    }

    /**
     * Displays a message to the standard output when the task was not done but the user tried to mark it as not done.
     */
    public static String alreadyNotDone(Task task) {
        return "This task was never done to begin with, sir: \n" + print(task);
    }

    /**
     * Displays a message to the standard output when the task completed by the user.
     */
    public static String done(String description) {
        return "Good job, sir. This task is marked done: \n" + "  " + description;
    }

    /**
     * Displays a message to the standard output when the task is marked not done by the user.
     */
    public static String notDone(String description) {
        return "Slacking off, sir? This task is marked not done: \n" + "  " + description;
    }

    /**
     * Displays a message to the standard output when the task is removed by the user.
     */
    public static String remove(String description) {
        return "This task have been removed sir: \n" + "  " + description;
    }

    /**
     * Displays a message to the standard output when the user inputs an unknown command.
     */
    public static String unknownCommand(String command) {
        return "I apologize, sir. This command: " + command + " is not recognized in my database.";
    }

    /**
     * Displays a message to the standard output when a new task is added by the user.
     */
    public static String add(Task task) {
        return "I've added this to your tasks sir: \n" + print(task);
    }

    /**
     * Displays the task to the standard output.
     */
    public static String print(Task task) {
        return "  " + task;
    }

    /**
     * Displays a good bye message to the standard output.
     */
    public static String bye() {
        return "Good bye, sir.";
    }

    /**
     * Displays a message to the standard output when user is searching for a task using a keyword.
     */
    public static String find() {
        return "Here are the matching tasks in your list, sir: ";
    }

    public static String invalidId() {
        return "This is an invalid task id, sir.";
    }
}
