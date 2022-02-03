package mcbot;

import java.util.ArrayList;

import mcbot.exception.McBotException;
import mcbot.task.Task;


/**
 * Ui class is the user interface class.
 * This class handles inputs and outputs of the user.
 */
public class Gui {
    private final String frameLine = "==========================================";

    /**
     * Constructor for Ui. 
     */
    public Gui() {}

    /**
     * Method to print the welcome line.
     */
    public String welcomeLine() {
        String logo = "\n"
                + "___  ___    ______       _\n"
                + "|  \\/  |    | ___ \\     | |\n"
                + "| .  . | ___| |_/ / ___ | |_\n"
                + "| |\\/| |/ __| ___ \\/ _ \\| __|\n"
                + "| |  | | (__| |_/ / (_) | |_\n"
                + "\\_|  |_/\\___\\____/ \\___/ \\__|\n"
                + "\n\n";
        return logo + "\n" + "Ahoy! Me name be McBot.\nTell me lad, what do you want?";
    }

    /**
     * Method to print loading error if file is not found. 
     */
    public String showLoadingError() {
        return "File not found. I will create one for you";
    }

    /**
     * Method to print goodbye line. 
     */
    public String byeLine() {
        return "Arghh! This ain't the last time ye see me lad";
    }

    /**
     * Method to print when a task is marked done. 
     */
    public String markLine() {
        return "Aye I'ave marked it done:";
    }

    /**
     * Method to print when a marked task is marked done again. 
     */
    public String markDuplication() {
        return "You fool!! It is already mark'd";
    }

    /**
     * Method to print when a task is unmarked done. 
     */
    public String unmarkLine() {
        return "Aye I'ave unmarked it:";
    }

    /**
     * Method to print when an unmarked task is unmarked again. 
     */
    public String unmarkDuplication() {
        return "You fool!! It is already unmark'd";
    }

    /**
     * Method to print the details of a task. 
     *
     * @param t is the task to be printed. 
     */
    public String printTask(Task t) {
        return t.toString();
    }

    /**
     * Method to print all the task in the list. 
     *
     * @param arrList the list of task to be printed. 
     */
    public String listTask(ArrayList<Task> arrList) {
        int i = 1;
        StringBuilder sb = new StringBuilder("Here are yer tasks boi:\n");
        for (Task task : arrList) {
            sb.append(i + "." + task + "\n") ;
            i++;
        }
        return sb.toString();
    }

    /**
     * Method to print when a ToDo task is added successfully. 
     *
     * @param t is the ToDo task to be added. 
     * @param size is the total number of tasks saved in the list. 
     */
    public String addTodoLine(Task t, int size) {
        StringBuilder sb = new StringBuilder("Got 'em down as todo:\n");
        sb.append(t.toString() + "\n");
        sb.append("Ye now have " + size + " tasks in list lad");
        return sb.toString();
    }

    /**
     * Method to print error in the case of a mark command. 
     *
     * @param error is the type of error thrown. 
     */
    public String markError(String error) {
        switch(error) {
        case "missingData":
            return "I don't know which one yer referring to";
        case "notInteger":
            return "I only accept integers boi";
        case "integerNotFound":
            return "Don't mess with me boi, that number is not in the list";
        default:
            return "New Error detected";
        }
    }

    /**
     * Method to print error in the case of a delete command. 
     *
     * @param error is the type of error thrown. 
     */
    public String deleteError(String error) {
        switch (error) {
        case "empty":
            return "Fool, I need a number to know which one to delete";
        case "notInteger":
            return "Boi, I only accept integers here for deletion";
        default:
            return "new error not caught";
        }
    }

    /**
     * Method to print error in the case of adding a Task command. 
     *
     * @param error is the type of error thrown. 
     */
    public String taskError(String error) {
        switch (error) {
        case "emptyTask":
            return "Sorry boi, ye can't leave todo task empty";
        case "deadlineFormat":
            return "Fool, follow this format: deadline -TASKNAME- /by DD/MM/YYYY HHMM";
        case "eventFormat":
            return "Fool, follow this format: event -TASKNAME- /at DD/MM/YYYY HHMM-";
        case "datetimeFormat":
            return "Yer date and time should follow this format: DD/MM/YYYY HHMM";
        case "emptyFindTask":
            return "Yer cant leave it empty mate";
        default:
            return "new error not caught";
        }
    }

    /**
     * Method to print when a Deadline task is added successfully. 
     *
     * @param t is the deadline task to be added. 
     * @param size is the total number of tasks saved in the list. 
     */
    public String addDeadlineLine(Task t, int size) {
        StringBuilder sb = new StringBuilder("Got 'em down as deadline:\n");
        sb.append(t.toString() + "\n");
        sb.append("Ye now have " + size + " tasks in list lad");
        return sb.toString();
        
    }

    /**
     * Method to print error.
     *
     * @param e is the exception thrown.  
     */
    public String printError(McBotException e) {
        return e.getMessage();
    }

    /**
     * Method to print when a Event task is added successfully. 
     *
     * @param t is the event task to be added. 
     * @param size is the total number of tasks saved in the list. 
     */
    public String addEventLine(Task t, int size) {
        StringBuilder sb = new StringBuilder("Got 'em down as event:\n");
        sb.append(t.toString() + "\n");
        sb.append("Ye now have " + size + " tasks in list lad");
        return sb.toString();
    }

    /**
     * Method to print when a task is deleted successfully.
     *
     * @param t is the task to be deleted. 
     * @param size is the total number of tasks saved in the list. 
     */
    public String deleteLine(Task t, int size) {
        StringBuilder sb = new StringBuilder("Aye, I 'ave deleted it\n");
        sb.append(t.toString() + "\n");
        sb.append("Ye now have " + size + " tasks in list lad");
        return sb.toString();
    }

    /**
     * Method to print when the search found no match for any tasks. 
     */
    public String noMatch() {
        return "No matching task found, sorry mate";
    }

    /**
     * Method to print when the search successfully found at least 1 matching task. 
     */
    public String printFind() {
        return "I'ave found this: ";
    }
}
