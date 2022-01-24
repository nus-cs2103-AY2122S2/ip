package dukeclasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a Ui
 */
public class Ui {

    /**
     * Class constructor to initiate Ui
     */
    public Ui(){

    }

    /**
     * Prints error if storage fail to load.
     */
    public void showLoadingError() {
        System.out.println("Storage load failed");
    }

    /**
     * Prints error if file is invalid.
     */
    public void showFileError() {
        System.out.println("Invalid File");
    }

    /**
     * Prints error if storage fail to updates.
     */
    public void showStorageError() {
        System.out.println("Fail to update storage");
    }

    /**
     * Prints error if user gives an invalid input.
     */
    public void showInputError() {
        System.out.println("    Invalid input detected. Please check your input");
    }

    /**
     * Prints greeting message.Usually immediately after boot up or when called using 'hi' command.
     */
    public void greet() {
        System.out.println(Commands.HI.toString());
    }

    /**
     * Prints bye message before the program closes.
     */
    public void sayBye() {
        System.out.println(Commands.BYE.toString());
    }

    /**
     * Prints a message indicating that task mentioned is not found.
     */
    public void sayTaskNotFound() {
        System.out.println("Task not found.");
    }

    /**
     * Prints the identity of the given task.
     *
     * @param task Task which will have its details printed.
     */
    public void identifyTask(Task task) {
        if (task.getIsDone()) {
            String output = String.format("%s    %s", Commands.MARK.toString(), task.identify());
            System.out.println(output);
        } else {
            System.out.println(String.format("%s      %s", Commands.UNMARK.toString(), task.identify()));
        }
    }

    /**
     * Prints message indicating new task was added.
     *
     * @param task Task that was added.
     * @param numOfTask Number of tasks in the list.
     */
    public void newTask(Task task, int numOfTask) {
        System.out.println(String.format(
            "%s      %s", Commands.ADD.toString(), task.identify()));
        System.out.println(String.format("    Now you have %d tasks in the list.", numOfTask));
    }

    /**
     * Prints list of task in storage file.
     *
     * @param filePath Path of storage file.
     */
    public void listTask(String filePath) {
        System.out.println(Commands.LIST.toString());
        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException errorMessage) {
            showFileError();
        }
    }

    /**
     * Prints list of task with the given tasklist.
     *
     * @param tasks Tasklist which will have its tasks printed.
     */
    public void listTaskUsingArrayList(TaskList tasks) {
        System.out.println(Commands.MATCH.toString());
        for (int i = 0; i < tasks.getTaskList().size(); i++) {
            Task task = tasks.getTaskList().get(i);
            System.out.println(String.format("    %s", task.identify()));
        }
    }

    /**
     * Prints message when task is deleted.
     *
     * @param task Task that was deleted.
     */
    public void deleteTask(Task task) {
        System.out.println(String.format(
                "%s      %s", Commands.DELETE.toString(), task.identify()));
    }
}
