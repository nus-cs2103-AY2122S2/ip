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
     *
     * @return String illustrating the load error message.
     */
    public String showLoadingError() {
        return "Storage load failed\n";
    }

    /**
     * Prints error if file is invalid.
     *
     * @return String illustrating the file error message.
     */
    public String showFileError() {
        return "Invalid File\n";
    }

    /**
     * Prints error if storage fail to updates.
     *
     * @return String illustrating the storage error message.
     */
    public String showStorageError() {
        return "Fail to update storage\n";
    }

    /**
     * Prints error if user gives an invalid input.
     *
     * @return String illustrating the input error message.
     */
    public String showInputError() {
        return "    Invalid input detected. Please check your input\n";
    }

    /**
     * Prints greeting message.Usually immediately after boot up or when called using 'hi' command.
     *
     * @return String illustrating the greeting message.
     */
    public String greet() {
        return Commands.HI.toString();
    }

    /**
     * Prints bye message before the program closes.
     *
     * @return String illustrating the bye message.
     */
    public String sayBye() {
        return Commands.BYE.toString();
    }

    /**
     * Prints a message indicating that task mentioned is not found.
     *
     * @return String illustrating the task cannot be found error message.
     */
    public String sayTaskNotFound() {
        return "Task not found.\n";
    }

    /**
     * Prints the identity of the given task.
     *
     * @param task Task which will have its details printed.
     * @return String to be printed which illustrates identity of the task.
     */
    public String identifyTask(Task task) {
        if (task.getIsDone()) {
            String output = String.format("%s    %s", Commands.MARK.toString(), task.identify());
            return output;
        } else {
            return String.format("%s      %s", Commands.UNMARK.toString(), task.identify());
        }
    }

    /**
     * Prints message indicating new task was added.
     *
     * @param task Task that was added.
     * @param numOfTask Number of tasks in the list.
     * @return String to be printed which illustrates identity of the new task.
     */
    public String newTask(Task task, int numOfTask) {
        return String.format("%s    %s    Now you have %d tasks in the list.",
                Commands.ADD.toString(), task.identify(),  numOfTask);
    }

    /**
     * Prints list of task in storage file.
     *
     * @param filePath Path of storage file.
     * @return String to be printed which illustrates the list of tasks.
     */
    public String listTask(String filePath) {
        String output = Commands.LIST.toString();
        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            for (int i = 0; sc.hasNext(); i++) {
                if (i == 0) {
                    output = output  + sc.nextLine();
                } else {
                    output = output + "\n" + sc.nextLine();
                }

            }
            sc.close();
        } catch (FileNotFoundException errorMessage) {
            showFileError();
        }
        return output;
    }

    /**
     * Prints list of task with the given tasklist.
     *
     * @param tasks Tasklist which will have its tasks printed.
     * @return String to be printed which illustrates list of tasks.
     */
    public String listTaskUsingArrayList(TaskList tasks) {
        String output = Commands.MATCH.toString();
        for (int i = 0; i < tasks.getTaskList().size(); i++) {
            Task task = tasks.getTaskList().get(i);
            output = output + String.format("    %s", task.identify());
        }
        return output;
    }

    /**
     * Prints message when task is deleted.
     *
     * @param task Task that was deleted.
     * @return String to be printed which illustrates identity of the deleted task.
     */
    public String deleteTask(Task task) {
        return String.format(
                "%s      %s", Commands.DELETE.toString(), task.identify());
    }
}
