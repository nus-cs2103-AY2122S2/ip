package bernie.ui;

import bernie.tasks.Task;
import bernie.tasks.TaskList;

/**
 * Class to deal with any interactions with the user. It is responsible for printing out
 * relevant messages to the user.
 */
public class InputResponder {
    /**
     * Returns the message when user starts the program
     * @return String, the message
     */
    public String greet() {
        return "Hello! I'm Bernie\nWhat's up?";
    }

    /**
     * Returns the message when user exits out of the program
     * @return String, the message
     */
    public String showLeaveMsg() {
        return "See ya!";
    }

    /**
     * Returns the error message when faced with any errors
     * @param msg String, the error message
     * @return String, the error message
     */
    public String showErrorMsg(String msg) {
        return msg;
    }

    /**
     * Returns the message whenever the user adds a new task: consists of the number
     * of tasks left and the tasks description
     * @param newTask Task, newTask added by the user
     * @param numTasksLeft int, the number of tasks left not done
     * @return String, the message
     */
    public String showAddedMsg(Task newTask, int numTasksLeft) {
        StringBuilder s = new StringBuilder();
        return s.append(String.format("Got ya. Added:\n%s\nYou got %d tasks waiting for ya!\n",
                newTask, numTasksLeft))
                .toString();
    }

    /**
     * Returns the message whenever the user deletes a task
     * @param deletedTask Task, task deleted by the user
     * @param numTasksLeft int, the number of tasks left not done
     * @return String message
     */
    public String showDeleteMsg(Task deletedTask, int numTasksLeft) {
        StringBuilder s = new StringBuilder();
        return s.append(String.format("Got ya. Removed:\n%s\nYou got %d tasks waiting for ya!\n",
                deletedTask, numTasksLeft))
                .toString();
    }

    /**
     * Returns the current taskList when the user inputs list into the program
     * @param tasks TaskList, empty if no tasks, length of 1 tasks if tasks contains tasks
     * @return String, the message of the tasks containing in the task list currently
     */
    public String showListTasksMsg(TaskList... tasks) {
        StringBuilder s = new StringBuilder();
        s.append("Here's what you need to do buddy:\n");
        if (tasks.length == 0) {
            s.append("NOTHING! :D");
            return s.toString();
        } else {
            return s.append(tasks[0].listTasks())
                    .toString();
        }
    }

    /**
     * Returns the message when a user marks a task number
     * @param markedTask Task that is marked done
     * @return String, the message
     */
    public String showDoneMsg(Task markedTask) {
        StringBuilder s = new StringBuilder();
        return s.append(String.format("This is now done:\n%s\n", markedTask))
                .toString();
    }

    /**
     * Returns the message when a user unmarks a task number
     * @param unmarkedTask Task that is marked not done
     * @return String, the message
     */
    public String showUndoneMsg(Task unmarkedTask) {
        StringBuilder s = new StringBuilder();
        return s.append(String.format("This is now undone:\n%s\n", unmarkedTask))
                .toString();
    }

    /**
     * Returns the message for matching tasks in the list
     * @param tasks, the current tasklist
     * @param description, the description which we want to find within the tasks
     * @return String, the message
     */
    public String showFoundTasksMsg(TaskList tasks, String description) {
        StringBuilder s = new StringBuilder();
        return s.append("We found these tasks in your list:\n")
                .append(tasks.findTasks(description))
                .toString();
    }
}
