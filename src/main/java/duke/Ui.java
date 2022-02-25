package duke;

import java.util.ArrayList;

/**
 * Class {@code Ui}, Implementation of User Interface for Duke.
 */
public class Ui {

    /**
     * Method to print Duke's initial UI.
     *
     *
     */
    public String start() {
        StringBuilder result = new StringBuilder();
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        result.append("Hello from\n" + logo).append("Hello! I'm Duke\r\nWhat can I do for you?");

        return result.toString();
    }

    /**
     * Method to print a goodbye message to users.
     *
     * @return Goodbye message
     */
    public String displayBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints All current tasks in supplied TaskList
     *
     * @param taskList ArrayList of Current Task
     * @return String representation of all tasks in ArrayList
     */
    public String displayTasks(ArrayList<Task> taskList) {
        StringBuilder list = new StringBuilder();
        if (taskList.isEmpty()) {
            list.append("The current task list is empty!");
        } else {
            for(int i = 0; i < taskList.size(); i++) {
                list.append(i + 1)
                        .append(".")
                        .append(taskList.get(i).toString())
                        .append("\r\n");
            }
        }
        return list.toString();
    }
    /**
     * Prints All found tasks in supplied TaskList.
     *
     * @param taskList ArrayList of Current Tasks
     * @param indexList ArrayList of Task indexes found by find function.
     * @return List of task found by index list.
     */
    public String displayFoundTasks(ArrayList<Task> taskList, ArrayList<Integer> indexList) {
        if (taskList.isEmpty()) {
            return "No task found by your given keyword";
        } else {
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < indexList.size(); i++) {
               int taskIndex = indexList.get(i);
               Task taskFound = taskList.get(i);
               result.append(taskIndex + "." + taskFound).append("\r\n");
            }
            return result.toString();
        }
    }
    /**
     * Prints message with the size of supplied TaskList
     *
     * @param taskList ArrayList of Current Task
     * @return Message with amount of tasks in tasklist
     */
    public String displayTaskAmount(ArrayList<Task> taskList) {
        return "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Prints String Representation of Task added.
     *
     * @param task Task to be added to TaskList
     * @return Message when user adds a task
     */
    public String addTask(Task task) {
        return "Got it. I've added this task: \r\n" + task;
    }

    /**
     * Prints String Representation of marked Task
     *
     * @param task Task needed to be marked
     * @return Message when task is successfully marked.
     */
    public String markTask(Task task) {
        StringBuilder result = new StringBuilder();
        result.append("Nice! I've marked this task as done: \n")
                .append(task.getStatusIcon())
                .append(" ")
                .append(task.getDescription());
        return result.toString();
    }

    /**
     * Prints String Representation of marked Task
     *
     * @param task Task needed to be marked
     * @return Message when task is successfully marked.
     */
    public String unmarkTask(Task task) {
        return "Nice! I've marked this task as not done: \n" +
                task.getStatusIcon() +
                " " +
                task.getDescription();
    }

    /**
     * Prints message to indicate that supplied Task has been deleted.
     *
     * @param task Task to be deleted
     * @return Message when deleting task
     */
    public String deleteTask(Task task) {
        return " Noted. I've removed this task: \r\n" + task;
    }

}
