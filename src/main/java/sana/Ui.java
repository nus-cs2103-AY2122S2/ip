package sana;

import java.util.LinkedList;
import java.util.Scanner;

import sana.task.Task;

/**
 * Ui represents the user interface object that deals with user interaction
 *
 * @author Jan
 * @version 1.0
 */
public class Ui {
    /** The scanner used to read from system input */
    private Scanner in;

    /** The border for Sana's replies */
    private static final String border = "_____________________________________________";

    /**
     * Constructor for the Ui object
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Returns the next line from the user input
     *
     * @return  the next user input
     */
    public String nextLine() {
        return in.nextLine();
    }

    /**
     * Closes the scanner used to read inputs
     */
    public void closeScanner() {
        in.close();
    }

    /**
     * Prints the string representation of the sana.task
     *
     * @param task  sana.task to be printed
     */
    public void printTaskInList(Task task) {
        System.out.println(task);
    }

    /**
     * Prints the entire sana.task list
     *
     * @param taskList  sana.task list to be printed
     */
    public void printTaskList(LinkedList<Task> taskList) {
        int index = 1;
        System.out.println("Here's your tasks!");
        for (Task task : taskList) {
            String header = Integer.valueOf(index).toString() + ".";
            System.out.println(header + task);
            index++;
        }
    }

    /**
     * This method prints to system IO how many tasks in user tasks
     */
    public void taskNumberText(int taskNumber) {
        String taskAmt = Integer.valueOf(taskNumber).toString();
        System.out.println("You have " + taskAmt + " things here.");
    }

    /**
     * Prints Sana's message when tasks are marked or unmarked
     *
     * @param isMarked   if sana.task is marked or unmarked
     */
    public void markText(boolean isMarked) {
        if (isMarked) {
            System.out.println("You've done it! Well done!");
        } else {
            System.out.println("Oopsies! I'll change it back!");
        }
    }

    /**
     * This method prints to system IO Sana's greeting
     */
    public void greet() {
        border();
        System.out.println("Hi! I'm BEEEEEEEG\nWhats up?");
        border();
    }

    /**
     * This method prints the border
     */
    public void border() {
        System.out.println(border);
    }

    /**
     * This method prints to system out Sana's add sana.task text
     */
    public void addTaskText() {
        System.out.println("I've added your sana.task!");
    }

    /**
     * This method prints to system out Sana's delete sana.task text
     */
    public void deleteTaskText() {
        System.out.println("Yay! I'll take this out!");
    }

    /**
     * This method prints to system IO Sana's bye
     */
    public void bye() {
        System.out.println("See you next time!");
    }

}
