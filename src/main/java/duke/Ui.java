package duke;

/**
 * Ui class to interact with the user in console.
 */
public class Ui {
    /**
     * Construct a Ui object.
     */
    public Ui() {
    }

    /**
     * Prints out the greeting message.
     *
     * @return Greeting message in Label format.
     */
    public String greet() {
        return "Hello! I'm Duke\n What can I do for you?";
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param taskList  TaskList object containing the ArrayList of tasks.
     * @return  Duke's reply.
     */
    public String addTask(TaskList taskList) {
        String output = "Got it. I've added this task:";
        output += "\n   " + taskList.getTaskList().get(taskList.getTaskList().size() - 1).toString();
        output += String.format("\nNow you have %d tasks in the list.", taskList.getTaskList().size());
        return output;
    }

    /**
     * Deletes a task from the tasklist.
     *
     * @param taskList  TaskList object containing the ArrayList of tasks.
     * @param removedTask   Task object to be removed.
     * @return  Duke's reply.
     */
    public String deleteTask(TaskList taskList, Task removedTask) {
        String output = "Noted. I've removed this task:";
        output += "\n   " + removedTask.toString();
        output += String.format("\nNow you have %d tasks in the list.", taskList.getTaskList().size());
        return output;
    }

    /**
     * Marks a task as done.
     *
     * @param markedTask    Task object which is done.
     * @return  Duke's reply.
     */
    public String markTask(Task markedTask) {
        String output = "Nice! I've marked this task as done:";
        output += "\n   " + markedTask.toString();
        return output;
    }

    /**
     * Marks a task as not done.
     *
     * @param unmarkedTask  Task object which is not done.
     * @return  Duke's reply.
     */
    public String unmarkTask(Task unmarkedTask) {
        String output = "OK, I've marked this task as not done yet:";
        output += "\n   " + unmarkedTask.toString();
        return output;
    }

    /**
     * Prints out a list of all tasks in the tasklist.
     *
     * @param taskList  Tasklist containing all tasks.
     * @return  Duke's reply.
     */
    public String list(TaskList taskList) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.getTaskList().size(); i++) {
            output.append(String.format("\n %d.%s", i, taskList.getTaskList().get(i - 1).toString()));
        }
        return output.toString();
    }

    /**
     * Prints out a list of all matching tasks in the tasklist.
     *
     * @param taskList  Tasklist containing all matching tasks.
     * @return  Duke's reply.
     */
    public String listMatching(TaskList taskList) {
        String output = "Here are the matching tasks in your list:";
        for (int i = 1; i <= taskList.getTaskList().size(); i++) {
            output += String.format("\n %d.%s", i, taskList.getTaskList().get(i - 1).toString());
        }
        return output;
    }

    /**
     * Throws error depending on what kind of error it is.
     *
     * @param type  The type of error to be thrown.
     * @return  Duke's reply.
     */
    public String throwError(String type) {
        DukeException error;
        if (type.equals("")) {
            error = new DukeException();
        } else {
            error = new DukeException(type);
        }
        return error.toString();
    }

    /**
     * Print out a goodbye line.
     *
     * @return  Duke's reply.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }
}
