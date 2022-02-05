package duke;

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
    public static void greet() {
        String logo =
                " ------    -------    -------   ---        ---   ---------    -------\n" +
                        "|____  |  |   _   |  |   _   |  \\  \\      /  /  |___   ___|  |   ____|\n" +
                        "     | |  |  |_|  |  |  |_|  |   \\  \\    /  /       | |      |  |____ \n" +
                        " _   | |  |   _   |  |      _|    \\  \\  /  /        | |      |_____  |\n" +
                        "| |__| |  |  | |  |  |  |\\  \\      \\  \\/  /      ___| |___    ____|  |\n" +
                        "|______|  |__| |__|  |__| \\__\\      \\____/      |_________|  |_______|\n"
                ;
        System.out.println("Welcome home, sir.\n" + logo);
    }

    /**
     * Displays a line to the standard output.
     */
    public static void showLine() {
        System.out.println("___________________________________________________________");
    }

    /**
     * Displays an error message to the standard output.
     */
    public static void showLoadingError() {
        System.out.println("Unable to load file, sir.");
    }

    /**
     * Displays message to the standard output when user inputs "list" as command.
     */
    public static void list() {
        System.out.println("Here are the tasks on your list, sir.");
    }

    /**
     * Displays a message to the standard output when the task was already done but the user tried to mark it as done.
     */
    public static void alreadyDone(Task task) {
        System.out.println("This task was already done to begin with, sir:");
        print(task);
    }

    /**
     * Displays a message to the standard output when the task was not done but the user tried to mark it as not done.
     */
    public static void alreadyNotDone(Task task) {
        System.out.println("This task was never done to begin with, sir:");
        print(task);
    }

    /**
     * Displays a message to the standard output when the task completed by the user.
     */
    public static void done(String description) {
        System.out.println("Good job, sir. This task is marked done: ");
        System.out.println("  " + description);
    }

    /**
     * Displays a message to the standard output when the task is marked not done by the user.
     */
    public static void notDone(String description) {
        System.out.println("Slacking off, sir? This task is marked not done: ");
        System.out.println("  " + description);
    }

    /**
     * Displays a message to the standard output when the task is removed by the user.
     */
    public static void remove(String description) {
        System.out.println("This task have been removed sir: ");
        System.out.println("  " + description);
    }

    /**
     * Displays a message to the standard output when the user inputs an unknown command.
     */
    public static void unknownCommand(String command) {
        System.out.println("I apologize, sir. This command: " + command + " is not recognized in my database.");
    }

    /**
     * Displays a message to the standard output when a new task is added by the user.
     */
    public static void create(Task task) {
        System.out.println("I've added this to your tasks sir: ");
        print(task);
    }

    /**
     * Displays the task to the standard output.
     */
    public static void print(Task task) {
        System.out.println("  " + task);
    }

    /**
     * Displays a good bye message to the standard output.
     */
    public static void bye() {
        Ui.showLine();
        System.out.println("Good bye, sir.");
        Ui.showLine();
    }
}
