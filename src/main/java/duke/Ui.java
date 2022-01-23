package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {

    public Ui() {
        this.showWelcome();
    }

    private void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n");
    }

    public void showLoadingError(String errorMessage) {
        System.out.println("There was an error in retrieving saved data:");
        System.out.println(errorMessage);
        System.out.println("I couldn't find any data to load, so I've created an empty task list.\n");
    }

    public void showLoadingSuccess(TaskList taskList) {
        if (taskList.getLength() != 0) {
            System.out.println("I've retrieved your latest task list data");
            this.showTaskList(taskList);
        }
    }

    public void showSavingError(String errorMessage) {
        System.out.println("There was an error in saving the data:");
        System.out.println(errorMessage);
        System.out.println();
    }

    public String readCommand() {
        System.out.println("What can I do for you next?");
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

    public void showCommandError(String errorMessage) {
        System.out.println("Oops, there might be an error in the command entered:");
        System.out.println(errorMessage);
        System.out.println();
    }

    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList);
    }

    public void showTaskAdded(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getLength() + " tasks in the list.\n");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println();
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println();
    }

    public void showTaskDeleted(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getLength() + " tasks in the list.\n");
    }

    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again!");
    }
}
