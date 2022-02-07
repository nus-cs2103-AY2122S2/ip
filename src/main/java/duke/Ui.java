package duke;

/**
 * UI class which entails methods that are related to the UI
 *
 * @author Benjamin Koh
 */

public class Ui {
    private static final String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = logo + "\n"
            + "    ____________________________________________________________\n"
            + "     Hello! I'm SquishyBot" + "\n"
            + "     What can I do for you?" + "\n"
            + "    ____________________________________________________________\n";

    private static final String GOODBYE_MESSAGE = "\n"
            + "    ____________________________________________________________\n"
            + "     Bye. Hope to see you again soon!" + "\n"
            + "    ____________________________________________________________\n";

    public static final String BLANK_LINE = "    ____________________________________________________________\n";

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

//    public static void add(String input) {
//        String temp = "\n" + BLANK_LINE + "added: " + input + "\n" + BLANK_LINE;
//        System.out.println(temp);
//    }

    /**
     * Prints the current taskList
     *
     * @param taskList current taskList
     */

    public static void list(TaskList taskList) {
        System.out.println("\n" + BLANK_LINE);
        for (int i = 1; i < taskList.getTaskListSize() + 1; i++) {
            Task curr = taskList.get(i - 1);
            String toPrint = "     " + i + "." + curr;
            System.out.println(toPrint);
        }
        System.out.println("\n" + BLANK_LINE);
    }

    /**
     * Method to mark the task as done
     *
     * @param numToMark index of the task to be marked
     * @param taskList current taskList
     */

    public static void mark(int numToMark, TaskList taskList) {
        Task markedTask = taskList.get(numToMark - 1);
        markedTask.markAsDone();
        System.out.println(BLANK_LINE + "     Nice! I've marked this task as done: " + "\n" + "     " + markedTask
                + "\n" + BLANK_LINE);
    }

    /**
     * Method to mark the task as not done
     *
     * @param numToUnmark index of the task to be marked as not done
     * @param taskList current taskList
     */

    public static void unmark(int numToUnmark, TaskList taskList) {
        Task markedTask = taskList.get(numToUnmark - 1);
        markedTask.markAsNotDone();
        System.out.println(BLANK_LINE + "     OK, I've marked this task as not done yet: " + "\n" + "     " + markedTask
                + "\n" + BLANK_LINE);
    }

    /**
     * Print and display to user that a to-do task has been added
     *
     * @param todoString to-do task name
     * @param taskList current taskList
     */

    public static void todo(String todoString, TaskList taskList) {
        String temp = "\n" + BLANK_LINE + "     Got it. I've added this task: \n" + "       [T][ ]" + todoString
                + "\n" + "     Now you have " + taskList.getTaskListSize() + " task(s) in the list.\n" + BLANK_LINE;
        System.out.println(temp);
    }

    /**
     * Print and display to user that a deadline task has been added
     *
     * @param deadlineName deadline task name
     * @param deadlineTime deadline by Date & Time
     * @param taskList current taskList
     */

    public static void deadline(String deadlineName, String deadlineTime, TaskList taskList) {
        String temp = "\n" + BLANK_LINE + "     Got it. I've added this task: \n" + "       [D][ ]" + deadlineName +
                "(by:" + deadlineTime + ")" + "\n" +
                "     Now you have " + taskList.getTaskListSize() + " task(s) in the list.\n" + BLANK_LINE;
        System.out.println(temp);
    }

    /**
     * Print and display to user that an event task has been added
     *
     * @param eventName event task name
     * @param eventTime event at Date & Time
     * @param taskList current taskList
     */

    public static void event(String eventName, String eventTime, TaskList taskList) {
        String temp = "\n" + BLANK_LINE + "     Got it. I've added this task: \n" + "       [E][ ]" + eventName +
                "(at: " + eventTime + ")" + "\n" +
                "     Now you have " + taskList.getTaskListSize() + " task(s) in the list.\n" + BLANK_LINE;
        System.out.println(temp);
    }

    /**
     * Delete a task from the taskList and informs user
     *
     * @param numToDelete index of task to be deleted
     * @param taskList current taskList
     */

    public static void delete(int numToDelete, TaskList taskList) {
        Task deleteTask = taskList.get(numToDelete - 1);
        taskList.remove(numToDelete);
        System.out.println(BLANK_LINE + "     Noted. I've removed this task: " + "\n" + "     " + deleteTask
                + "\n" + "     Now you have " + taskList.getTaskListSize() + " task(s) in the list.\n" + BLANK_LINE);
    }
}


