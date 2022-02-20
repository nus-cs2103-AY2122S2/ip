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
     * Displays a greeting.
     *
     * @return String the message
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
     * Displays message when user inputs "list" as command.
     *
     * @return String the message
     */
    public static String list() {
        return "Here are the tasks on your list, sir.";
    }
    /**
     * Displays a message when the task was already done but the user tried to mark it as done.
     *
     * @return String the message
     */
    public static String alreadyDone(Task task) {
        return "This task was already done to begin with, sir: \n" + print(task);
    }
    /**
     * Displays a message when the task was not done but the user tried to mark it as not done.
     *
     * @return String the message
     */
    public static String alreadyNotDone(Task task) {
        return "This task was never done to begin with, sir: \n" + print(task);
    }
    /**
     * Displays a message when the task is completed by the user.
     *
     * @return String the message
     */
    public static String done(String description) {
        return "Good job, sir. This task is marked done: \n" + "  " + description;
    }
    /**
     * Displays a message when the task is marked not done by the user.
     *
     * @return String the message
     */
    public static String notDone(String description) {
        return "Slacking off, sir? This task is marked not done: \n" + "  " + description;
    }
    /**
     * Displays a message when the task is removed by the user.
     *
     * @return String the message
     */
    public static String remove(String description) {
        return "This task have been removed sir: \n" + "  " + description;
    }
    /**
     * Displays a message when the user inputs an unknown command.
     *
     * @return String the message
     */
    public static String unknownCommand(String command) {
        return "I apologize, sir. This command: " + command + " is not recognized in my database.";
    }
    /**
     * Displays a message when a new task is added by the user.
     *
     * @return String the message
     */
    public static String add(Task task) {
        return "I've added this to your tasks sir: \n" + print(task);
    }
    /**
     * Displays the task.
     *
     * @return String the message
     */
    public static String print(Task task) {
        return "  " + task;
    }
    /**
     * Displays a good bye message.
     *
     * @return String the message
     */
    public static String bye() {
        return "Good bye, sir.";
    }
    /**
     * Displays a message when user is searching for a task using a keyword.
     *
     * @return String the message
     */
    public static String find() {
        return "Here are the matching tasks in your list, sir: ";
    }
    /**
     * Displays a message when user indicates an invalid task id.
     *
     * @return String the message
     */
    public static String invalidId() {
        return "This is an invalid task id, sir.";
    }
    /**
     * Displays a message when user tries to add a duplicate task.
     *
     * @return String the message
     */
    public static String duplicateTask() {
        return "This Task is already in your list, sir.";
    }
}
