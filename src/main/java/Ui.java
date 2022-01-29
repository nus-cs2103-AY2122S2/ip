package duke;

/**
 * Deals with interactions with the user
 */
public class Ui {
    Ui() {
    }

    /**
     * Prints the welcome statement.
     */
    public void start() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the goodbye statement.
     */
    public void finalBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message for loading error.
     */
    public void showLoadingError() {
        System.out.println("System data could not be loaded!");
    }

    /**
     * Prints the error message for empty description.
     */
    public void emptyInput() {
        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Prints the error message for invalid commands.
     */
    public void doNotUnderstand() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints the message for marking a task as done.
     * @param task is the task which is marked as done.
     */
    public void markDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
    }

    /**
     * Prints the message for marking a task as done.
     *  @param task is the task which is marked as undone.
     */
    public void unmarkDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }


    /**
     * Prints the message for removing a task.
     */
    public void removedTask(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.",tasks.size()));
    }

    /**
     * Prints the message for adding a task to the taskList.
     */
    public void addTask(TaskList tasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));

    }

    /**
     *
     */
    public void findTasks(TaskList tasks){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i =0; i<tasks.size(); i++) {
            System.out.println(tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
