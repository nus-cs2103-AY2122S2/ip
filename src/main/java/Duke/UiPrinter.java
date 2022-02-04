package Duke;

public class UiPrinter {
    public static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";


    /**
     * Prints default greeting from Duke.
     */
    public String printGreeting() {
        return ("Hello from\n"
                + "Hello! I'm Duke the blue wizard cat!\n"
                + "What can I do for you?\n");
    }

    /**
     * Prints default goodbye from Duke.
     */
    public String printBye() {
        return ("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints task added to storage.
     *
     * @param task Task added to storage.
     * @param numberOfTasks Number of Tasks in storage.
     */
    public String printTask(Task task, int numberOfTasks) {
        return (
                "Got it. I've added this task:\n"
                + task.toString()
                + "\n"
                + "Now you have " + numberOfTasks + " tasks in the list.\n");
    }

    /**
     * Prints exception with error message.
     *
     * @param e Exception thrown with error message.
     */
    public String printExceptions(Exception e) {
        return (e.getMessage() + "\n");
    }

    /**
     * Prints message if ListStorage is empty.
     */
    public String printEmptyList() {
        return ("There is currently nothing in your list!\n");
    }

    /**
     * Prints items in ListStorage.
     *
     * @param myStorage ListStorage with Tasks.
     */
    public String printList(ListStorage myStorage) {
        return ("Here are the tasks in your list:\n"
                + myStorage.printList());
    }

    /**
     * Prints message when task is unmarked.
     *
     * @param myStorage ListStorage with Tasks.
     * @param taskNumber Task number of task to be unmarked.
     */
    public String printUnmark(ListStorage myStorage, int taskNumber) {
        return ("OK, I've marked this task as not done yet:\n"
                + myStorage.printTask(taskNumber));
    }

    /**
     * Prints message when task is marked.
     *
     * @param myStorage ListStorage with Tasks.
     * @param taskNumber Task number of task to be marked
     */
    public String printMark(ListStorage myStorage, int taskNumber) {
        return ("Nice! I've marked this task as done:\n"
                + myStorage.printTask(taskNumber));
    }

    /**
     * Prints message when task is deleted
     *
     * @param myStorage ListStorage with Tasks.
     * @param taskNumber Task number of task to be deleted.
     */
    public String printDelete(ListStorage myStorage, int taskNumber) {
        return ("Noted. I've removed this task:\n"
                + myStorage.printTask(taskNumber));
    }
}
