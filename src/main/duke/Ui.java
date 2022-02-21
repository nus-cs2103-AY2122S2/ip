package main.duke;

import main.duke.tasks.Task;

import java.util.ArrayList;

public class Ui {
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public String respondBye() {
        return Ui.GOODBYE_MESSAGE;
    }

    /**
     * prints out all the tasks in the current list
     * @param taskList the current list of tasks
     */
    public String respondList(TaskList taskList) {
        int n = taskList.getTasksCount();
        if (n == 0) {
            return "The list is currently empty.";
        } else {
            StringBuilder response = new StringBuilder();
            for (int i = 0; i < n; i++) {
                response.append(String.format("%d.%s%n", i + 1, taskList.getTask(i)));
            }
            return response.toString();
        }
    }

    /**
     * prints out the task that has been marked
     * @param markTask the targeted task to mark
     */
    public String respondMark(Task markTask) {
        return String.format("Nice! I've marked this task as done: \n"
                + "    %s\n", markTask);
    }

    /**
     * prints out the task that has been unmarked
     * @param unmarkTask the targeted task to unmark
     */
    public String respondUnmark(Task unmarkTask) {
        return String.format("Nice! I've marked this task as not done: \n"
                + "    %s\n", unmarkTask);
    }

    /**
     * prints out the task that has been added as well as the current number of tasks after adding
     * @param newTask the targeted task to add
     * @param taskList the current list of tasks
     */
    public String respondAddTask(Task newTask, TaskList taskList) {
        return String.format("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                newTask, taskList.taskCountToString());
    }

    /**
     * prints out the task that has been added as well as the current number of tasks after removing
     * @param deleteTask the targeted task to add
     * @param taskList the current list of tasks
     */
    public String respondDeleteTask(Task deleteTask, TaskList taskList) {
        return String.format("Noted. I've removed this task: \n" + "    %s\n"
                + "%s\n", deleteTask, taskList.taskCountToString());
    }

    /**
     * prints out the task that was filtered by the user
     * @param foundTasks the targeted task to add
     */
    public String respondFindTask(ArrayList<Task> foundTasks) {
        int n = foundTasks.size();
        if (n == 0) {
            return "Cannot find any related tasks.";
        } else {
            StringBuilder response = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < n; i++) {
                response.append(String.format("%d.%s%n", i + 1, foundTasks.get(i)));
            }
            return response.toString();
        }
    }

    public String respondUndo(TaskList taskList) {
        return "Undo task successfully!\n" + this.respondList(taskList);
    }
}
