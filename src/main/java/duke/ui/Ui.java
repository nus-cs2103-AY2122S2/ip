package duke.ui;

import java.util.ArrayList;

import duke.main.TaskList;
import duke.task.Task;

/**
 * User Interface class, print beautiful output to user
 */
public class Ui {
    private final String lines = "____________________________"
                                         + "________________________________\n";
    private final String endline = "___________________________"
                                           + "_________________________________\n";

    /**
     * constructor
     */
    public Ui() {

    }

    /**
     * Prints bye message
     *
     * @return a string of message
     */
    public String bye() {
        String result = "";
        result += "Bye! See you again\n";
        return result;
    }

    /**
     * Show all current tasks.
     *
     * @param taskList current list of tasks
     * @return a string of message
     */
    public String showAllTasks(TaskList taskList) {
        String result = "";
        result += "Here are all your tasks:\n";
        for (int i = 0; i < taskList.size(); i++) {
//            System.out.println((i + 1) + "." + taskList.get(i).toString());
            result += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Tells the user that the task is already done
     * @param markTask current list of tasks
     * @return a string of message
     */
    public String showMarkTaskAsAlreadyUndone(Task markTask) {
        String result = "";
        result += "You have already done this task!\n";
        result += markTask.markAsUndone();
        return result;
    }

    /**
     * Tells the user that the task is marked as done
     * @param markTask current list of tasks
     * @return a string of message
     */
    public String showMarkTaskAsUndone(Task markTask) {
        String result = "";
        result += "Good Job! You have marked this task as done!\n";
        result += markTask.markAsUndone();
        return result;
    }

    /**
     * Tells the user that the task is not done yet
     * @param markTask current list of tasks
     * @return a string of message
     */
    public String showMarkTaskAsAlreadyDone(Task markTask) {
        String result = "";
        result += "You have already done this task!\n";
        result += markTask.markAsDone();
        return result;
    }

    /**
     * Tells the user that the task is marked as undone
     * @param markTask current list of tasks
     * @return a string of message
     */
    public String showMarkTaskAsDone(Task markTask) {
        String result = "";
        result += "Good Job! You have marked this task as done!\n";
        result += markTask.markAsDone();
        return result;
    }

    /**
     * Tells the user that the todo task is added
     * @param todo the task to be added
     * @param taskList current list of tasks
     * @return a string of message
     */
    public String showTodoTaskAdded(Task todo, TaskList taskList) {
        String result = "";
        result += "Got it, I have added a TODO task:\n";
        result += todo.toString() + '\n';
        result += "Now you have " + taskList.size() + " tasks in the list.\n";
        return result;
    }

    /**
     * Tells the user that the deadline task is added
     * @param deadline the task to be added
     * @param taskList current list of tasks
     * @return a string of message
     */
    public String showDeadlineTaskAdded(Task deadline, TaskList taskList) {
        String result = "";
        result += "Got it, I have added a DEADLINE task:\n";
        result += deadline.toString() + '\n';
        result += "Now you have " + taskList.size() + " tasks in the list.\n";
        return result;
    }

    /**
     * Tells the user that the event task is added
     * @param event the task to be added
     * @param taskList current list of tasks
     * @return a string of message
     */
    public String showEventTaskAdded(Task event, TaskList taskList) {
        String result = "";
        result += "Got it, I have added an EVENT task:\n";
        result += event.toString() + '\n';
        result += "Now you have " + taskList.size() + " tasks in the list.\n";
        return result;
    }



    /**
     * Tells the user that the task is deleted
     * @param taskToBeDelete a task to be deleted
     * @param taskList current list of tasks
     * @return a string of message
     */
    public String showDeletedTask(Task taskToBeDelete, TaskList taskList) {
        String result = "";
        result += "Okay, I have removed this task:\n";
        result += taskToBeDelete + "\n";
        result += "Now you have " + taskList.size() + " tasks in the list.\n";
        return result;
    }

    /**
     * Tells the user how to delete a task correctly.
     * @return a string of message
     */
    public String showWhichTaskIsBeingDeleted() {
        return "Which task are you deleting? "
                                   + "Insert a number like this: delete <task number>\n";
    }

    /**
     * Shows error message
     * @param message the error message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Shows line
     */
    public void showLine() {
        System.out.println(lines);
    }

    /**
     * Shows endline
     */
    public void showEndline() {
        System.out.println(endline);
    }

    /**
     * Shows found task using find command
     * @param searchedTaskList founded task list, a string array
     * @return a string of message
     */
    public String showFoundTask(ArrayList<String> searchedTaskList) {
        if (searchedTaskList.size() == 0) {
            return "There are no matching tasks in your list\n";
        } else {
            String result = "Here are the matching tasks in your list:\n";
            result = showAllSearchedKeywords(searchedTaskList, result);
            return result;
        }
    }

    private String showAllSearchedKeywords(ArrayList<String> searchedTaskList, String result) {
        for (String s : searchedTaskList) {
            result += s + "\n";
        }
        return result;
    }
}
