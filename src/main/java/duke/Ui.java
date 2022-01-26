package duke;

/**
 * Ui class to interact with the user in console.
 */
public class Ui {

    public static final String LINE = "____________________________________________________________";

    /**
     * Construct a Ui object.
     */
    public Ui() {
    }

    /**
     * Prints out the greeting message.
     */
    public void greet() {
        String output = LINE + "\n Hello! I'm Duke\n What can I do for you?\n" + LINE;
        System.out.println(output);
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param taskList  TaskList object containing the ArrayList of tasks.
     */
    public void addTask(TaskList taskList) {
        System.out.println(LINE + "\n Got it. I've added this task:");
        System.out.println("   " + taskList.getTaskList().get(taskList.getTaskList().size() - 1).toString());
        System.out.printf(" Now you have %d tasks in the list.\n", taskList.getTaskList().size());
        System.out.println(LINE);
    }

    /**
     * Deletes a task from the tasklist.
     *
     * @param taskList  TaskList object containing the ArrayList of tasks.
     * @param removedTask   Task object to be removed.
     */
    public void deleteTask(TaskList taskList, Task removedTask) {
        System.out.println(LINE + "\n Noted. I've removed this task:");
        System.out.println("   " + removedTask.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", taskList.getTaskList().size());
        System.out.println(LINE);
    }

    /**
     * Marks a task as done.
     *
     * @param markedTask    Task object which is done.
     */
    public void markTask(Task markedTask) {
        System.out.println(LINE + "\n Nice! I've marked this task as done:");
        System.out.println("   " + markedTask.toString());
        System.out.println(LINE);
    }

    /**
     * Marks a task as not done.
     *
     * @param unmarkedTask  Task object which is not done.
     */
    public void unmarkTask(Task unmarkedTask) {
        System.out.println(LINE + "\n OK, I've marked this task as not done yet:");
        System.out.println("   " + unmarkedTask.toString());
        System.out.println(LINE);
    }

    /**
     * Prints out a list of all tasks in the tasklist.
     *
     * @param taskList  Tasklist containing all tasks.
     */
    public void list(TaskList taskList) {
        System.out.println(LINE + "\n Here are the tasks in your list:");
        for (int i = 1; i <= taskList.getTaskList().size(); i++) {
            System.out.printf(" %d.%s\n", i, taskList.getTaskList().get(i - 1).toString());
        }
        System.out.println(LINE);
    }

    /**
     * Prints out a list of all matching tasks in the tasklist.
     *
     * @param taskList  Tasklist containing all matching tasks.
     */
    public void listMatching(TaskList taskList) {
        System.out.println(LINE + "\n Here are the matching tasks in your list:");
        for (int i = 1; i <= taskList.getTaskList().size(); i++) {
            System.out.printf(" %d.%s\n", i, taskList.getTaskList().get(i - 1).toString());
        }
        System.out.println(LINE);
    }

    /**
     * Throws error depending on what kind of error it is.
     *
     * @param type  The type of error to be thrown.
     */
    public void throwError(String type) {
        System.out.println(LINE);
        DukeException error;
        if (type.equals("")) {
            error = new DukeException();
        } else {
            error = new DukeException(type);
        }
        System.out.println(error);
        System.out.println(LINE);
    }

    /**
     * Print out a goodbye line.
     */
    public void exit() {
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }
}
