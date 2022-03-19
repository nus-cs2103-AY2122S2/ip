package sana;

import java.util.LinkedList;

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
     * Returns sana's response wehn a new task is added
     *
     * @param taskAdded task that was added
     * @param totalTaskAmount   total task amount in the tasklist
     *
     * @return  sana's response
     */
    public String addNewTask(Task taskAdded, Integer totalTaskAmount) {
        assert totalTaskAmount >= 0;
        assert taskAdded != null;
        StringBuilder responseText = new StringBuilder();
        responseText.append(addTaskText());
        responseText.append(printTaskInList(taskAdded));
        responseText.append(taskNumberText(totalTaskAmount));

        return responseText.toString();
    }

    /**
     * Returns sana's response when a task is deleted
     *
     * @param taskDeleted   task that was deleted
     * @param totalTaskAmount   total task amount in the tasklist
     *
     * @return  sana's response
     */
    public String deleteTask(Task taskDeleted, Integer totalTaskAmount) {
        assert totalTaskAmount >= 0;
        assert taskDeleted != null;
        StringBuilder responseText = new StringBuilder();
        responseText.append(deleteTaskText());
        responseText.append(printTaskInList(taskDeleted));
        responseText.append(taskNumberText(totalTaskAmount));

        return responseText.toString();
    }
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

    /** Prints to system IO how many tasks in user task
     *
     * @param taskNumber    number of tasks in the list
     * @return  sana's response that says the number of tasks in list
     */
    public String taskNumberText(int taskNumber) {
        String taskAmt = Integer.valueOf(taskNumber).toString();
        return "You have " + taskAmt + " things here.\n";
    }

    /**
     * Prints Sana's message when tasks are marked or unmarked
     *
     * @param isMarked   if sana.task is marked or unmarked
     * @return           Sana's response when a task is marked or unmarked
     */
    public String markText(boolean isMarked) {
        if (isMarked) {
            return "You've done it! Well done!\n";
        } else {
            return "Oopsies! I'll change it back!\n";
        }
    }

    /**
     * Prints Sana's message when tasks are updated
     *
     * @param updatedTask   the updated task
     * @return  Sana's response when a task is updated
     */
    public String updateTask(Task updatedTask) {
        return "I've updated this item!\n";
    }

    public String dateFormatError() {
        return "Give your date in YYYY-MM-DD format!\n";
    }

    public String taskNumberFormatError() {
        return "I don't know what sana task you're referring to!\n";
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
        return "See you next time!\n";
    }

}
