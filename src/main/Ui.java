package main;

import main.tasks.Task;

public class Ui {
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public void respondBye() {
        System.out.println(Ui.GOODBYE_MESSAGE);
    }

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

    public void respondMark(Task markTask) {
        System.out.printf("Nice! I've marked this task as done: \n"
                + "    %s\n", markTask);
    }

    public void respondUnmark(Task unmarkTask) {
        System.out.printf("Nice! I've marked this task as done: \n"
                + "    %s\n", unmarkTask);
    }

    public void respondAddTask(Task newTask, TaskList taskList) {
        System.out.printf("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                newTask, taskList.taskCountToString());
    }

    public void respondDeleteTask(Task deleteTask, TaskList taskList) {
        System.out.printf("Noted. I've removed this task: \n" + "    %s\n"
                + "%s\n", deleteTask, taskList.taskCountToString());
    }
}
