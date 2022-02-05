package duke;

import java.util.ArrayList;

/**
 * Class {@code Ui}, Implementation of User Interface for Duke.
 */
public class Ui {

    /**
     * Method to print Duke's initial UI.
     */
    public void start() {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            System.out.println("Hello! I'm Duke\r\nWhat can I do for you?");
    }

    /**
     * Method to print a goodbye message to users.
     */
    public void displayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints All current tasks in supplied TaskList
     *
     * @param taskList ArrayList of Current Task
     */
    public void displayTasks(ArrayList<Task> taskList) {
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    /**
     * Prints message with the size of supplied TaskList
     *
     * @param taskList ArrayList of Current Task
     */
    public void displayTaskAmount(ArrayList<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints String Representation of Task added.
     *
     * @param task Task to be added to TaskList
     */
    public void addTask(Task task) {
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
    }

    /**
     * Prints String Representation of marked Task
     *
     * @param task Task needed to be marked
     */
    public void markTask(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.getStatusIcon() + " " + task.getDescription());
    }

    /**
     * Prints message to indicate that supplied Task has been deleted.
     *
     * @param task Task to be deleted
     */
    public void deleteTask(Task task) {
        System.out.println(" Noted. I've removed this task: ");
        System.out.println(task);
    }

//    public void showLoadingError() {
//        System.out.println("There was an error loading the file!");
//    }
//
//    public void readCommand() {
//
//    }

    // Can display all commands
//    public void displayCommands() {
//        System.out.println("DISPLAY LIST OF COMMANDS");
//    }
}
