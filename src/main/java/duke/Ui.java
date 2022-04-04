package duke;

/**
 * UI class which entails methods that are related to the UI
 *
 * @author Benjamin Koh
 */

public class Ui {
    public static final String BLANK_LINE = "    ________________________________\n";

    private static final String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = logo + "\n"
            + "    ________________________________\n"
            + "     Hello! I'm SquishyBot" + "\n"
            + "     What can I do for you?" + "\n"
            + "    ________________________________\n";

    private static final String GOODBYE_MESSAGE = "\n"
            + "    ________________________________\n"
            + "     Bye. Hope to see you again soon!" + "\n"
            + "    ________________________________\n";

    /**
     * Welcomes the User
     */

    public static void welcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Announce the exit of the program
     */

    public static void exit() {
        System.out.println(GOODBYE_MESSAGE);
        System.exit(0);
    }

    /**
     * Prints the current taskList
     *
     * @param taskList current taskList
     */

    public static void printList(TaskList taskList) {
        if (taskList.getSize() < 1) {
            System.out.println("\n" + "     This list is empty! Let's add a task!" + "\n");
        }
        System.out.println("\n");
        for (int i = 1; i < taskList.getSize() + 1; i++) {
            Task curr = taskList.get(i - 1);
            String toPrint = "     " + i + "." + curr;
            System.out.println(toPrint);
        }
        System.out.println("\n");
    }

    /**
     * Method to mark the task as done
     *
     * @param numToMark index of the task to be marked
     * @param taskList current taskList
     */

    public static void printMark(int numToMark, TaskList taskList) {
        Task markedTask = taskList.get(numToMark - 1);
        markedTask.markAsDone();
        System.out.println("     Nice! I've marked this task as done: " + "\n" + "     " + markedTask
                + "\n");
    }

    /**
     * Method to mark the task as not done
     *
     * @param numToUnmark index of the task to be marked as not done
     * @param taskList current taskList
     */

    public static void printUnmark(int numToUnmark, TaskList taskList) {
        Task markedTask = taskList.get(numToUnmark - 1);
        markedTask.markAsNotDone();
        System.out.println("     OK, I've marked this task as not done yet: " + "\n" + "     " + markedTask
                + "\n");
    }

    /**
     * Print and display to user that a to-do task has been added
     *
     * @param todoString to-do task name
     * @param taskList current taskList
     */

    public static void printTodo(String todoString, TaskList taskList) {
        String temp = "\n" + "     Got it. I've added this task: \n" + "       [T][ ] " + todoString
                + "\n" + "     Now you have " + taskList.getSize() + " task(s) in the list.\n";
        System.out.println(temp);
    }

    /**
     * Print and display to user that a deadline task has been added
     *
     * @param deadlineName deadline task name
     * @param deadlineTime deadline by Date & Time
     * @param taskList current taskList
     */

    public static void printDeadline(String deadlineName, String deadlineTime, TaskList taskList) {
        String temp = "\n" + "     Got it. I've added this task: \n" + "       [D][ ]" + deadlineName
                + "(by:" + deadlineTime + ")" + "\n"
                + "     Now you have " + taskList.getSize() + " task(s) in the list.\n";
        System.out.println(temp);
    }

    /**
     * Print and display to user that an event task has been added
     *
     * @param eventName event task name
     * @param eventTime event at Date & Time
     * @param taskList current taskList
     */

    public static void printEvent(String eventName, String eventTime, TaskList taskList) {
        String temp = "\n" + "     Got it. I've added this task: \n" + "       [E][ ]" + eventName
                + "(at: " + eventTime + ")" + "\n"
                + "     Now you have " + taskList.getSize() + " task(s) in the list.\n";
        System.out.println(temp);
    }

    /**
     * Delete a task from the taskList and informs user
     *
     * @param numToDelete index of task to be deleted
     * @param taskList current taskList
     */


    public static void printDelete(int numToDelete, TaskList taskList) {
        Task deleteTask = taskList.get(numToDelete - 1);
        taskList.remove(numToDelete);
        System.out.println("     Noted. I've removed this task: " + "\n" + "     " + deleteTask
                + "\n" + "     Now you have " + taskList.getSize() + " task(s) in the list.\n");
    }

    /**
     * Finds a specific task and print it out.
     *
     * @param listString list of all tasks
     */

    public static void printFind(StringBuilder listString) {
        if (listString.length() == 0) {
            System.out.println("\n" + "     No task with that keyword! Please try again!" + "\n");
        } else {
            String temp = "\n" + "     Here are the matching tasks in your list: " + "\n" + "     "
                    + listString + "\n";
            System.out.println(temp);
        }
    }
}


