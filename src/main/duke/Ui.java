package main.duke;

import main.duke.tasks.Task;

import java.util.ArrayList;

public class Ui {
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public void respondBye() {
        System.out.println(Ui.GOODBYE_MESSAGE);
    }

    /**
     * prints out all the tasks in the current list
     * @param taskList the current list of tasks
     */
    public void respondList(TaskList taskList) {
        int n = taskList.getTasksCount();
        if (n == 0) {
            System.out.println("The list is currently empty.");
        } else {
            for (int i = 0; i < n; i++) {
                System.out.printf("%d.%s%n", i + 1, taskList.getTask(i));
            }
        }
    }

    /**
     * prints out the task that has been marked
     * @param markTask the targeted task to mark
     */
    public void respondMark(Task markTask) {
        System.out.printf("Nice! I've marked this task as done: \n"
                + "    %s\n", markTask);
    }

    /**
     * prints out the task that has been unmarked
     * @param unmarkTask the targeted task to unmark
     */
    public void respondUnmark(Task unmarkTask) {
        System.out.printf("Nice! I've marked this task as done: \n"
                + "    %s\n", unmarkTask);
    }

    /**
     * prints out the task that has been added as well as the current number of tasks after adding
     * @param newTask the targeted task to add
     * @param taskList the current list of tasks
     */
    public void respondAddTask(Task newTask, TaskList taskList) {
        System.out.printf("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                newTask, taskList.taskCountToString());
    }

    /**
     * prints out the task that has been added as well as the current number of tasks after removing
     * @param deleteTask the targeted task to add
     * @param taskList the current list of tasks
     */
    public void respondDeleteTask(Task deleteTask, TaskList taskList) {
        System.out.printf("Noted. I've removed this task: \n" + "    %s\n"
                + "%s\n", deleteTask, taskList.taskCountToString());
    }

    /**
     * prints out the task that was filtered by the user
     * @param foundTasks the targeted task to add
     */
    public void respondFindTask(ArrayList<Task> foundTasks) {
        int n = foundTasks.size();
        if (n == 0) {
            System.out.println("Cannot find any related tasks.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < n; i++) {
                System.out.printf("%d.%s%n", i + 1, foundTasks.get(i));
            }
        }
    }
}
