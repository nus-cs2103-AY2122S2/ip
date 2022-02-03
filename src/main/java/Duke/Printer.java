package Duke;

public class Printer {
    public static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String BORDER = "    ____________________________________________________________\n";
    private static final String SPACING = "    ";


    /**
     * Prints default greeting from Duke.
     */
    public void printGreeting() {
        System.out.println("Hello from\n" + LOGO);

        System.out.println(BORDER + SPACING
                + "Hello! I'm Duke\n" + SPACING
                + "What can I do for you?\n"
                + BORDER);
    }

    /**
     * Prints default goodbye from Duke.
     */
    public void printBye() {
        System.out.println(BORDER + SPACING + "Bye. Hope to see you again soon!\n" + BORDER);
    }

    /**
     * Prints task added to storage.
     *
     * @param task Task added to storage.
     * @param numberOfTasks Number of Tasks in storage.
     */
    public void printTask(Task task, int numberOfTasks) {
        System.out.println(BORDER
                + SPACING
                + "Got it. I've added this task:\n"
                + SPACING
                + task.toString()
                + "\n"
                + SPACING
                + "Now you have " + numberOfTasks + " tasks in the list.\n"
                + BORDER);
    }

    /**
     * Prints exception with error message.
     *
     * @param e Exception thrown with error message.
     */
    public void printExceptions(Exception e) {
        System.out.println(BORDER
                + SPACING
                + e.getMessage() + "\n"
                + BORDER);
    }

    /**
     * Prints message if ListStorage is empty.
     */
    public void printEmptyList() {
        System.out.println(BORDER
                + SPACING
                + "There is currently nothing in your list!\n"
                + BORDER);
    }

    /**
     * Prints items in ListStorage.
     *
     * @param myStorage ListStorage with Tasks.
     */
    public void printList(ListStorage myStorage) {
        System.out.println(BORDER + SPACING
                        + "Here are the tasks in your list:\n"
                        + myStorage.printList()
                        + BORDER);
    }

    /**
     * Prints message when task is unmarked.
     *
     * @param myStorage ListStorage with Tasks.
     * @param taskNumber Task number of task to be unmarked.
     */
    public void printUnmark(ListStorage myStorage, int taskNumber) {
        System.out.println(BORDER
                + SPACING
                + "OK, I've marked this task as not done yet:\n"
                + myStorage.printTask(taskNumber) + BORDER);
    }

    /**
     * Prints message when task is marked.
     *
     * @param myStorage ListStorage with Tasks.
     * @param taskNumber Task number of task to be marked
     */
    public void printMark(ListStorage myStorage, int taskNumber) {
        System.out.println(BORDER
                + SPACING
                + "Nice! I've marked this task as done:\n"
                + myStorage.printTask(taskNumber) + BORDER);
    }

    /**
     * Prints message when task is deleted
     *
     * @param myStorage ListStorage with Tasks.
     * @param taskNumber Task number of task to be deleted.
     */
    public void printDelete(ListStorage myStorage, int taskNumber) {
        System.out.println(BORDER
                + SPACING
                + "Noted. I've removed this task:\n"
                + myStorage.printTask(taskNumber)
                + SPACING
                + "Now you have " + (myStorage.length() - 1) + " tasks in the list.\n"
                + BORDER);
    }
}
