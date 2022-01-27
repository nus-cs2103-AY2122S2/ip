package seedu.duke;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.io.File;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    void showStartLoading() {
        System.out.println("Hold on...I am checking if you have previous tasks saved...");
    }

    //returns name of user for exit command
    String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Thanks for waiting, let me introduce myself! Hello from\n" + logo);
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Hello " + name);
        return name;
    }

    String readCommand() {
        return sc.nextLine();
    }

    void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    void showLoadingResult(TaskList oldTaskList) {
        System.out.println("Take a look at your previous tasks:");
        oldTaskList.printTasks();
    }

    void showFileCreated(File myObj) {
        System.out.println("File Created: " + myObj.getName());
    }

    public void showAddResult(TaskList taskList, Task newTask) {
        System.out.println(String
                .format("Got it, I've added this task: \n %s\nNow you have %d task in the list",
                        newTask.toString(),
                        taskList.getNumberOfTasks()));
    }

    public void showDeleteResult(TaskList taskList, Task taskToRemove) {
        System.out.println(String
                .format("Got it, I've deleted this task: \n %s\nNow you have %d task in the list",
                        taskToRemove.toString(),
                        taskList.getNumberOfTasks()));
    }

    public static void showUnmarkedResult(Task unmarkedTask) {
        System.out.println(String
                .format("OK, I've marked this task as not done yet:\n %s", unmarkedTask.toString()));
    }

    public static void showMarkedResult(Task markedTask) {
        System.out.println(String
                .format("Nice! I've marked this task as done: \n%s", markedTask.toString()));
    }

    public void showCompleteUpdateOfFile() {
        System.out.println("Database has been updated");
    }

    public void showCompletedSearch(TaskList taskList) {
        System.out.println("Here are the matched tasks:");
        taskList.printTasks();
    }
}
