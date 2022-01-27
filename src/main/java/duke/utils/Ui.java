package duke.utils;


import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;


/**
 * Class that handles user interactions
 */
public class Ui {

    /**
     * Instance of scanner to handle user inputs
     */
    private Scanner sc;

    /**
     * Stores the last command given by the user
     */
    private String lastCommand;

    /**
     * Constructor method for Ui class
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Print the list of tasks, handle
     * case where there are no tasks in
     * the list
     *
     * @param tasks Arraylist of tasks
     */
    public static void printList(ArrayList<Task> tasks) {

        if (tasks.size() == 0) {
            System.out.println("No Tasks Right Now");
        } else {
            for (int x = 0; x < tasks.size(); x++) {
                System.out.println((x + 1) + ". " + tasks.get(x).toString());
            }
        }
    }


    /**
     * Print the addition of a new task to the list
     *
     * @param curr Task that has been added to the list
     * @param size number of tasks in the list
     */
    public static void printTaskAddition(Task curr, int size) {
        System.out.println("Got it! I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }


    /**
     * Print the removal of a task from the list
     *
     * @param curr Task that has been removed from the list
     * @param size number of tasks in the list
     */
    public static void printTaskDeletion(Task curr, int size) {
        System.out.println("Got it! I've removed this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }



    /**
     * Print the welcome message for the user
     */
    public static void printHello() {
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
    }


    /**
     * Print a line for interface
     * between two actions
     */
    public static void printLine(){
        System.out.println("-------------------");
    }

    /**
     * Print the goodbye message for the user
     */
    public static void printBye() {
        System.out.println("Bye! Hope to see you again soon");
    }

    /**
     *  Read the user's input
     *
     *  @return the String input from the user
     */
    public String readCommand() {
        String userInput = sc.nextLine();
        lastCommand = userInput;
        return userInput;
    }

    /**
     * Return whether the last user
     * command is to exit
     *
     * @return true if the last command is "bye"
     */
    public boolean isExit() {
        return this.lastCommand.equals("bye");
    }


    /**
     * Print a task has been marked
     * as completed
     *
     * param t Task that has been marked as complete
     */
    public static void printMarkCompletion(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
    }

    /**
     * Print a task has been marked
     * as not completed
     *
     * param t Task that has been marked as not complete
     */
    public static void printMarkUncompletion(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
    }

    /**
     * Print the error message for
     * a particular DukeException
     *
     * @param e Exception received
     */
    public static void showError(DukeException e){
        System.out.println(e);
    }

}
