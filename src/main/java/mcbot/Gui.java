package mcbot;

import java.util.ArrayList;

import mcbot.exception.McBotException;
import mcbot.task.Task;

/**
 * Gui class is the user interface class.
 * This class handles outputs of the user.
 */
public class Gui {
    /**
     * Constructor for Gui.
     */
    public Gui() {
    }

    /**
     * Method to return the welcome line.
     *
     * @return String to welcome the user.
     */
    public String welcomeLine() {
        String logo = "\n"
                + "  __  __    ___      _   \n"
                + " |  \\/  |__| _ ) ___| |_ \n"
                + " | |\\/| / _| _ \\/ _ \\  _|\n"
                + " |_|  |_\\__|___/\\___/\\__|\n";
        return logo + "\n" + "Ahoy! Me name be McBot.\nTell me lad, what do you want?";
    }

    /**
     * Method to return loading error if file is not found.
     *
     * @return String to show when file is not found.
     */
    public String showLoadingError() {
        return "File not found. I will create one for you";
    }

    /**
     * Method to return when a task is marked done.
     * 
     * @return The string for marking a task.
     */
    public String markLine() {
        return "Aye I'ave marked it done:";
    }

    /**
     * Method to return when a marked task is marked done again.
     * 
     * @return The string for marking duplication.
     */
    public String markDuplication() {
        return "You fool!! It is already mark'd";
    }

    /**
     * Method to return when a task is unmarked done.
     * 
     * @return The string for unmarking a task.
     */
    public String unmarkLine() {
        return "Aye I'ave unmarked it:";
    }

    /**
     * Method to return when an unmarked task is unmarked again.
     * 
     * @return The string for unmarking duplication.
     */
    public String unmarkDuplication() {
        return "You fool!! It is already unmark'd";
    }

    /**
     * Method to return the details of a task.
     *
     * @param t is the task to be returned.
     * @return The string for the printed task.
     */
    public String printTask(Task t) {
        return t.toString();
    }

    /**
     * Method to return all the task in the list.
     *
     * @param arrList the list of task to be returned.
     * @return The string for printing a list of task.
     */
    public String listTask(ArrayList<Task> arrList) {
        int i = 1;
        StringBuilder sb = new StringBuilder("Here are yer tasks boi:\n");
        for (Task task : arrList) {
            sb.append(i + "." + task + "\n");
            i++;
        }
        return sb.toString();
    }

    /**
     * Method to return when a ToDo task is added successfully.
     *
     * @param t is the ToDo task to be added.
     * @param size is the total number of tasks saved in the list. 
     * @return The string for the added ToDo task.
     */
    public String addTodoLine(Task t, int size) {
        StringBuilder sb = new StringBuilder("Got 'em down as todo:\n");
        sb.append(t.toString() + "\n");
        sb.append("Ye now have " + size + " tasks in list lad");
        return sb.toString();
    }

    /**
     * Method to return error in the case of a mark command.
     *
     * @param error is the type of error thrown.
     * @return The string for the error when marking.
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
     * Method to return error in the case of a delete command.
     *
     * @param error is the type of error thrown.
     * @return The string for deletion error.
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
     * Method to return error in the case of adding a Task command.
     *
     * @param error is the type of error thrown.
     * @return The string for task error.
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
     * Method to return when a Deadline task is added successfully.
     *
     * @param t is the deadline task to be added.
     * @param size is the total number of tasks saved in the list.
     * @return The string for adding deadline task.
     */
    public String addDeadlineLine(Task t, int size) {
        StringBuilder sb = new StringBuilder("Got 'em down as deadline:\n");
        sb.append(t.toString() + "\n");
        sb.append("Ye now have " + size + " tasks in list lad");
        return sb.toString();
        
    }

    /**
     * Method to return error.
     *
     * @param e is the exception thrown.
     * @return The string for printing error.
     */
    public String printError(McBotException e) {
        return e.getMessage();
    }

    /**
     * Method to return when a Event task is added successfully.
     *
     * @param t is the event task to be added.
     * @param size is the total number of tasks saved in the list.
     * @return The string for adding event task.
     */
    public String addEventLine(Task t, int size) {
        StringBuilder sb = new StringBuilder("Got 'em down as event:\n");
        sb.append(t.toString() + "\n");
        sb.append("Ye now have " + size + " tasks in list lad");
        return sb.toString();
    }

    /**
     * Method to return when a task is deleted successfully.
     *
     * @param t is the task to be deleted.
     * @param size is the total number of tasks saved in the list.
     * @return The string for deleting a task.
     */
    public String deleteLine(Task t, int size) {
        StringBuilder sb = new StringBuilder("Aye, I 'ave deleted it\n");
        sb.append(t.toString() + "\n");
        sb.append("Ye now have " + size + " tasks in list lad");
        return sb.toString();
    }

    public String anomalyError() {
        return "Mate, this two events are clashing. I can't create this event.";
    }
}
