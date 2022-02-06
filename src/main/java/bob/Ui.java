package bob;

/**
 * This class handles all output by the program to the user.
 */
public class Ui {
    /**
     * Greets the user on program startup.
     */
    public static void greet() {
        Ui.printLine();
        System.out.println("Hello! I'm Bob!\n" + "Loading saved entries...");
        System.out.println("What can I do for you?");
        Ui.printLine();
    }

    /**
     * Prints UI message for the list command.
     */
    public static void list() {
        Ui.printLine();
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints UI message when a task is marked.
     *
     * @param task Task to be marked.
     */
    public static void mark(Task task) {
        Ui.printLine();
        System.out.println("Nice! I've marked this task as done:\n    " + task);
        Ui.printLine();
    }

    /**
     * Prints UI message when a task is unmarked.
     *
     * @param task Task to be unmarked.
     */
    public static void unmark(Task task) {
        Ui.printLine();
        System.out.println("OK, I've marked this task as not done yet:\n    " + task);
        Ui.printLine();
    }

    /**
     * Prints UI message when a Task is deleted.
     *
     * @param tasksSize New size of TaskList.
     * @param task      Task to be removed.
     */
    public static void delete(int tasksSize, Task task) {
        Ui.printLine();
        System.out.println("Noted. I've removed this task:\n    " + task);
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
        Ui.printLine();
    }

    /**
     * Prints UI message when a new Task is added.
     *
     * @param task      Newly added Task.
     * @param tasksSize New size of TaskList.
     */
    public static void newTask(Task task, int tasksSize) {
        Ui.printLine();
        System.out.println("Got it. I've added this task:\n    " + task);
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
        Ui.printLine();
    }

    /**
     * Prints UI message when an invalid command is given.
     *
     * @param cmd Invalid command to be echoed.
     */
    public static void invalidCommand(String cmd) {
        Ui.printLine();
        System.out.println("I don't understand: " + cmd);
        System.out.println("Please repeat!:(");
        Ui.printLine();
    }

    /**
     * Prints UI message when insufficient arguments are given.
     */
    public static void insufficientArgs() {
        Ui.printLine();
        System.out.println("Insufficient arguments for this command! :(");
        Ui.printLine();
    }

    /**
     * Prints UI message when no such item exists in the TaskList.
     */
    public static void noSuchItem() {
        Ui.printLine();
        System.out.println("There is no such item! :0");
        Ui.printLine();
    }

    /**
     * Prints UI message when invalid integers are given.
     */
    public static void invalidInt() {
        Ui.printLine();
        System.out.println("Please use a valid integer! 12345!");
        Ui.printLine();
    }

    /**
     * Prints UI message when time is in invalid format.
     */
    public static void invalidTimeFormat() {
        Ui.printLine();
        System.out.println("Please input the time in the correct format! yyyy-mm-ddThh:mm:ss");
        Ui.printLine();
    }

    /**
     * Prints a line to format output.
     */
    public static void printLine() {
        System.out.println("----------------------------------------");
    }
}
