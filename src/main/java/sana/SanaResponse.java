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
public class SanaResponse {
    /** Constructor for the Ui object */
    public SanaResponse() {}

    /**
     * Prints the string representation of the sana.task
     *
     * @param task  sana.task to be printed
     */
    public String printTaskInList(Task task) {
        return task.toStringFromList();
    }

    /**
     * Prints the entire sana.task list
     *
     * @param taskList      sana.task list to be printed
     * @param isMatching    if method is called from a find command
     */
    public String printTaskList(LinkedList<Task> taskList, boolean isMatching) {
        int index = 1;
        StringBuilder taskListString = new StringBuilder();
        if (isMatching) {
            taskListString.append("Here's your matching tasks!\n");
        } else {
            taskListString.append("Here's your tasks!\n");
        }
        for (Task task : taskList) {
            String header = Integer.valueOf(index).toString() + ".";
            taskListString.append(header).append(task.toStringFromList());
            index++;
        }
        return taskListString.toString();
    }

    /** Prints to system IO how many tasks in user task */
    public String taskNumberText(int taskNumber) {
        String taskAmt = Integer.valueOf(taskNumber).toString();
        return "You have " + taskAmt + " things here.\n";
    }

    /**
     * Prints Sana's message when tasks are marked or unmarked
     *
     * @param isMarked   if sana.task is marked or unmarked
     */
    public String markText(boolean isMarked) {
        if (isMarked) {
            return "You've done it! Well done!\n";
        } else {
            return "Oopsies! I'll change it back!\n";
        }
    }

    /** Prints to system IO Sana's greeting */
    public void greet() {
        System.out.println("Hi! I'm BEEEEEEEG\nWhats up?");
    }

    /** Prints to system out Sana's add sana.task text */
    public String addTaskText() {
        return "I've added your sana.task!\n";
    }

    /** This method prints to system out Sana's delete sana.task text */
    public String deleteTaskText() {
        return "Yay! I'll take this out!\n";
    }

    /** Returns Sana's goodbye message */
    public String bye() {
        return "See you next time!";
    }

}
