package duke;

/**
 * Deals with interactions with the user
 */
public class Ui {
    public Ui() {
    }

    /**
     * Prints the welcome statement.
     */
    public String start() {
        return ("Hello! I'm Duke %n What can I do for you?");
    }

    /**
     * Prints the goodbye statement.
     */
    public String finalBye() {
        return("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message for loading error.
     */
    public String showLoadingError() {
        return("System data could not be loaded!");
    }

    /**
     * Prints the error message for empty description.
     */
    public String emptyInput() {
        return(" ☹ OOPS!!! The description of a task cannot be empty.");
    }

    /**
     * Prints the error message for invalid commands.
     */
    public String doNotUnderstand() {
        return("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints the message for marking a task as done.
     * @param task is the task which is marked as done.
     */
    public String markDone(duke.Task task) {
        return("Nice! I've marked this task as done: ")+ System.lineSeparator() + task.toString();
    }

    /**
     * Prints the message for marking a task as done.
     *  @param task is the task which is marked as undone.
     */
    public String unmarkDone(duke.Task task) {
        return("OK, I've marked this task as not done yet:") + System.lineSeparator() + task.toString();
    }

    public String list(duke.TaskList tasks){
        String output = tasks.print();
        output = "PIKAAA-CHUUUUUUU: HERE ARE YOUR TASKS!!" + System.lineSeparator() + output;
        return output;
    }


    /**
     * Prints the message for removing a task.
     */
    public String removedTask(duke.Task task, duke.TaskList tasks) {
        String output = ("Noted. I've removed this task:") + System.lineSeparator();;
        output += task.toString() + System.lineSeparator();
        output += String.format("Now you have %d tasks in the list.", tasks.size());
        return output;
    }

    /**
     * Prints the message for adding a task to the taskList.
     */
    public String addTask(duke.TaskList tasks) {
        String output = ("Got it. I've added this task:") + System.lineSeparator();
        output += (tasks.get(tasks.size() - 1)).toString() + System.lineSeparator();
        output += String.format("Now you have %d tasks in the list.", tasks.size());
        return output;
    }

    /**
     *
     */
    public String findTasks(duke.TaskList tasks) {
        String output = ("____________________________________________________________") + System.lineSeparator();
        output += ("Here are the matching tasks in your list:") + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            output += (tasks.get(i)) + System.lineSeparator();
        }
        output += ("____________________________________________________________");
        return output;
    }
}
