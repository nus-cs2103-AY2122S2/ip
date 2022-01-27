package pikabot;

import pikabot.task.Task;

/**
 * Interacts with the user by printing out statements.
 */
public class Ui {

    static String LINE = "_________________________________";
    static String INDENTATION = "     ";

    /**
     * Prints out welcome message when PikaBot starts running.
     */
    public static void printWelcomeText() {
        System.out.println(INDENTATION + LINE + "\n" + INDENTATION + "Hello! I'm PikaBot" +
            "\n" + INDENTATION + "What can I do for you? シ\n" + INDENTATION + LINE);
    }

    /**
     * Prints out exit text when PikaBot is closed.
     */
    public static void printClosingText() {
        System.out.println(INDENTATION + LINE + "\n" + INDENTATION + "Bye. Hope to see you again!" +
            "\n" + INDENTATION + LINE);
    }

    /**
     * Prints out list of tasks.
     *
     * @param taskList TaskList containing tasks to print.
     */
    public static void printListOfTasks(TaskList taskList) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Here are the tasks in your list:");

        int taskNumber = 1;
        int length = taskList.noOfTasks();

        while (taskNumber <= length) {
            System.out.println(INDENTATION + taskNumber + "." +
                taskList.get(taskNumber - 1));
            taskNumber++;
        }
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Prints out list of tasks containing a specified keyword.
     *
     * @param taskList TaskList containing tasks with the specified keyword.
     * @param keyword Keyword to search for that is given by user.
     */
    public static void printListOfMatchedTasks(TaskList taskList, String keyword) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Here are the tasks containing \"keyword\" in your list:");

        int taskNumber = 1;
        int length = taskList.noOfTasks();

        while (taskNumber <= length) {
            System.out.println(INDENTATION + taskNumber + "." +
                taskList.get(taskNumber - 1));
            taskNumber++;
        }

        System.out.println(INDENTATION + LINE);
    }

    /**
     * Prints out indication that a task has been marked as done.
     *
     * @param task Task that has been marked as done.
     */
    public static void indicateMarked(Task task) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + task);
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Prints out indication that a task has been unmarked.
     *
     * @param task Task that has been unmarked.
     */
    public static void indicateUnmarked(Task task) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Ok, I've marked this task as not done yet:");
        System.out.println(INDENTATION + task);
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Prints out indication that a task has been added.
     *
     * @param task Task that has been added.
     * @param taskList TaskList that task has been added to.
     */
    public static void indicateAddedTask(Task task, TaskList taskList) {
        System.out.println(INDENTATION + LINE + "\n" +
            INDENTATION + "Got it. I've added this task:" + "\n" +
            INDENTATION + "  " + task + "\n" +
            INDENTATION + "Now you have " + taskList.noOfTasks() + " tasks in the list." +
            "\n" + INDENTATION + LINE);
    }

    /**
     * Prints out indication that a task has been removed.
     *
     * @param task Task that has been removed.
     * @param taskList TaskList that task has been removed from.
     */
    public static void indicateRemovedTask(Task task, TaskList taskList) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + "  " + task);
        System.out.println(INDENTATION + "Now you have " + taskList.noOfTasks() + " tasks in the list");
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Prints out exception message.
     *
     * @param e Exception caught.
     */
    public static void printExceptionMessage(Exception e) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + e.getMessage());
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Prints out customised exception message.
     *
     * @param message Customised message.
     */
    public static void printExceptionCustomisedMessage(String message) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + message);
        System.out.println(INDENTATION + LINE);
    }
}
