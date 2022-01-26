public class Ui {
    // attributes
    public static final String LINE = "____________________________________________________________";

    // constructor
    public Ui() {
    }

    /**
     * Prints out the greeting message
     */
    public void greet() {
        String output = LINE + "\n Hello! I'm Duke\n What can I do for you?\n" + LINE;
        System.out.println(output);
    }

    public void showLoadingError() {
    }

    public void addTask(TaskList taskList) {
        System.out.println(LINE + "\n Got it. I've added this task:");
        System.out.println("   " + taskList.getTaskList().get(taskList.getTaskList().size() - 1).toString());
        System.out.printf(" Now you have %d tasks in the list.\n", taskList.getTaskList().size());
        System.out.println(LINE);
    }

    public void deleteTask(TaskList taskList, Task removedTask) {
        System.out.println(LINE + "\n Noted. I've removed this task:");
        System.out.println("   " + removedTask.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", taskList.getTaskList().size());
        System.out.println(LINE);
    }

    public void markTask(Task markedTask) {
        System.out.println(LINE + "\n Nice! I've marked this task as done:");
        System.out.println("   " + markedTask.toString());
        System.out.println(LINE);
    }

    public void unmarkTask(Task unmarkedTask) {
        System.out.println(LINE + "\n OK, I've marked this task as not done yet:");
        System.out.println("   " + unmarkedTask.toString());
        System.out.println(LINE);
    }

    /**
     * Prints out a list of all tasks in the tasklist
     */
    public void list(TaskList taskList) {
        System.out.println(LINE + "\n Here are the tasks in your list:");
        for (int i = 1; i <= taskList.getTaskList().size(); i++) {
            System.out.printf(" %d.%s\n", i, taskList.getTaskList().get(i - 1).toString());
        }
        System.out.println(LINE);
    }

    /**
     * Throw error depending on what kind of error it is
     *
     * @param type  The type of error to be thrown
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
     * Print out a goodbye line
     */
    public void exit() {
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }
}
