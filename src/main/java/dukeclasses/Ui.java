package dukeclasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a Ui which deal with returning results to users through the use of print statements.
 */
public class Ui {

    /**
     * Class constructor to instantiate Ui.
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
     * Prints error if file does not exist when trying to read or write.
     */
    public void showFileError() {
        System.out.println("Invalid File");
    }

    /**
     * Prints error if storage fail to update when list of tasks are modified.
     */
    public void showStorageError() {
        System.out.println("Fail to update storage");
    }

    /**
     * Prints error if user input does not follow format.
     */
    public void showInputError() {
        System.out.println("    Invalid input detected. Please check your input");
    }

    /**
     * Prints greeting during boot up or when hi command is called.
     */
    public void greet() {
        System.out.println(Commands.HI.toString());
    }

    /**
     * Prints message when user initiate exit command.
     */
    public void sayBye() {
        System.out.println(Commands.BYE.toString());
    }

    /**
     * Prints details of the task.
     *
     * @param task Task which will have its details printed.
     */
    public void identifyTask(Task task) {
        if (task.getIsDone()) {
            String output = String.format(
                "%s    %s", Commands.MARK.toString(), task.identify());
            System.out.println(output);
        } else {
            System.out.println(String.format(
                "%s      %s", Commands.UNMARK.toString(), task.identify()));
        }
    }

    /**
     * Prints details of the newly added task and also current number of tasks in list.
     *
     * @param task Task which will have its details printed.
     * @param numOfTask Integer that denotes number of tasks in the list currently.
     */
    public void newTask(Task task, int numOfTask) {
        System.out.println(String.format("%s      %s", Commands.ADD.toString(), task.identify()));
        System.out.println(String.format("    Now you have %d tasks in the list.", numOfTask));
    }

    /**
     * Prints list of task.
     *
     * @param filePath File which contain list of task.
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
     * Prints details of the deleted task.
     *
     * @param task Task which will have its details printed.
     */
    public void deleteTask(Task task) {
        System.out.println(String.format("%s      %s", Commands.DELETE.toString(), task.identify()));
    }

}
