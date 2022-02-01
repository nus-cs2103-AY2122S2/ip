package mcbot;

import java.util.ArrayList;

import mcbot.exception.McBotException;
import mcbot.task.Task;


/**
 * Ui class is the user interface class.
 * This class handles inputs and outputs of the user.
 */
public class Ui {
    private final String frameLine = "==========================================";

    /**
     * Constructor for Ui. 
     */
    public Ui() {}

    /**
     * Method to print the welcome line.
     */
    public void welcomeLine() {
        String logo = "\n"
                + "___  ___    ______       _\n"
                + "|  \\/  |    | ___ \\     | |\n"
                + "| .  . | ___| |_/ / ___ | |_\n"
                + "| |\\/| |/ __| ___ \\/ _ \\| __|\n"
                + "| |  | | (__| |_/ / (_) | |_\n"
                + "\\_|  |_/\\___\\____/ \\___/ \\__|\n"
                + "\n\n";
        System.out.println(logo);
        System.out.println(frameLine);
        System.out.println("Ahoy! Me name be McBot.\nTell me lad, what do you want?");
        System.out.println(frameLine);
    }

    /**
     * Method to print the frame line. 
     */
    public void printFrame() {
        System.out.println(frameLine);
    }

    /**
     * Method to print loading error if file is not found. 
     */
    public void showLoadingError() {
        System.out.println("File not found. I will create one for you");
    }

    /**
     * Method to print goodbye line. 
     */
    public void byeLine() {
        System.out.println("Arghh! This ain't the last time ye see me lad");
    }

    /**
     * Method to print when a task is marked done. 
     */
    public void markLine() {
        System.out.println("Aye I'ave marked it done:");
    }

    /**
     * Method to print when a marked task is marked done again. 
     */
    public void markDuplication() {
        System.out.println("You fool!! It is already mark'd");
    }

    /**
     * Method to print when a task is unmarked done. 
     */
    public void unmarkLine() {
        System.out.println("Aye I'ave unmarked it:");
    }

    /**
     * Method to print when an unmarked task is unmarked again. 
     */
    public void unmarkDuplication() {
        System.out.println("You fool!! It is already unmark'd");
    }

    /**
     * Method to print the details of a task. 
     * 
     * @param t is the task to be printed. 
     */
    public void printTask(Task t) {
        System.out.println(t);
    }

    /**
     * Method to print all the task in the list. 
     * 
     * @param arrList the list of task to be printed. 
     */
    public void listTask(ArrayList<Task> arrList) {
        int i = 1;
        System.out.println("Here are yer tasks boi:");
        for (Task task : arrList) {
            System.out.println(i + "." + task);
            i++;
        }
    }

    /**
     * Method to print when a ToDo task is added successfully. 
     * 
     * @param t is the ToDo task to be added. 
     * @param size is the total number of tasks saved in the list. 
     */
    public void addTodoLine(Task t, int size) {
        System.out.println("Got 'em down as todo:");
        System.out.println(t);
        System.out.println("Ye now have " + size + " tasks in list lad");
    }

    /**
     * Method to print error in the case of a mark command. 
     * 
     * @param error is the type of error thrown. 
     */
    public void markError(String error) {
        switch(error) {
        case "missingData":
            System.out.println("I don't know which one yer referring to");
            break;
        case "notInteger":
            System.out.println("I only accept integers boi");
            break;
        case "integerNotFound":
            System.out.println("Don't mess with me boi, that number is not in the list");
            break;
        default:
            System.out.println("New Error detected");
            break;
        }
    }

    /**
     * Method to print error in the case of a delete command. 
     * 
     * @param error is the type of error thrown. 
     */
    public void deleteError(String error) {
        switch (error) {
        case "empty":
            System.out.println("Fool, I need a number to know which one to delete");
            break;
        case "notInteger":
            System.out.println("Boi, I only accept integers here for deletion");
            break;
        default:
            System.out.println("new error not caught");
        }
    }

    /**
     * Method to print error in the case of adding a Task command. 
     * 
     * @param error is the type of error thrown. 
     */
    public void taskError(String error) {
        switch (error) {
        case "emptyTask":
            System.out.println("Sorry boi, ye can't leave todo task empty");
            break;
        case "deadlineFormat":
            System.out.println("Fool, follow this format: deadline -TASKNAME- /by DD/MM/YYYY HHMM");
            break;
        case "eventFormat":
            System.out.println("Fool, follow this format: event -TASKNAME- /at DD/MM/YYYY HHMM-");
            break;
        case "datetimeFormat":
            System.out.println("Yer date and time should follow this format: DD/MM/YYYY HHMM");
            break;
        case "emptyFindTask":
            System.out.println("Yer cant leave it empty mate");
            break;
        default:
            System.out.println("new error not caught");
        }
    }

    /**
     * Method to print when a Deadline task is added successfully. 
     * 
     * @param t is the deadline task to be added. 
     * @param size is the total number of tasks saved in the list. 
     */
    public void addDeadlineLine(Task t, int size) {
        System.out.println("Got 'em down as deadline:");
        System.out.println(t);
        System.out.println("Ye now have " + size + " tasks in list lad");
    }

    /**
     * Method to print error.
     * 
     * @param e is the exception thrown.  
     */
    public void printError(McBotException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Method to print when a Event task is added successfully. 
     * 
     * @param t is the event task to be added. 
     * @param size is the total number of tasks saved in the list. 
     */
    public void addEventLine(Task t, int size) {
        System.out.println("Got 'em down as event:");
        System.out.println(t);
        System.out.println("Ye now have " + size + " tasks in list lad");
    }

    /**
     * Method to print when a task is deleted successfully.
     * 
     * @param t is the task to be deleted. 
     * @param size is the total number of tasks saved in the list. 
     */
    public void deleteLine(Task t, int size) {
        System.out.println("Aye, I 'ave deleted it");
        System.out.println(t);
        System.out.println("Ye now have " + size + " tasks in list lad");
    }

    /**
     * Method to print when the search found no match for any tasks. 
     */
    public void noMatch() {
        System.out.println("No matching task found, sorry mate");
    }

    /**
     * Method to print when the search successfully found at least 1 matching task. 
     */
    public void printFind() {
        System.out.println("I'ave found this: ");
    }
}
