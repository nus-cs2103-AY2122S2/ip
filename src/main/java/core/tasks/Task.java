package core.tasks;

import core.exceptions.TaskAlreadyDoneException;
import core.exceptions.TaskAlreadyUnmarkedException;
import utilities.OutputFormatter;

/**
 * The Task class.
 *
 * @author s7manth
 * @version 0.1
 */
public class Task {
    private static int idCounter = 1;
    protected String taskDescription;
    protected boolean isCompleted;
    private final int taskId = createID();


    /**
     * Overloaded constructor for the Task class with a default status of being incomplete.
     *
     * @param taskDescription The description of the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;
    }

    /**
     * Constructor to create an instance of Task.
     *
     * @param taskDescription The description of the task.
     * @param isDone The status of whether the task has been completed or not.
     */
    private Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isCompleted = isDone;
    }

    /**
     * Method to create an id for the task.
     *
     * @return The id of the task.
     */
    static int createID() {
        return idCounter++;
    }

    /**
     * Factory method to obtain an instance of a task.
     *
     * @param taskDescription The description of the task.
     * @return An instance of the task with a given description.
     */
    public static Task getInstance(String taskDescription) {
        return new Task(taskDescription);
    }

    /**
     * Overloaded factory method to obtain an instance of a task.
     *
     * @param taskDescription The description of the task.
     * @param isDone The status of the task which tells about its completion.
     * @return An instance of the task with a given description.
     */
    public static Task getInstance(String taskDescription, boolean isDone) {
        return new Task(taskDescription, isDone);
    }

    /**
     * Method to obtain the associated id of the task.
     *
     * @return The id of the task.
     */
    public int getTaskId() {
        return this.taskId;
    }

    /**
     * Method to obtain the task description.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Method to obtain the string placeholder representing the completion of the task.
     *
     * @return The appropriate string representation according to whether the task has been completed or not.
     */
    public String status() {
        if (this.isCompleted) {
            return "X"; // "\u2713";
        } else {
            return " "; // "\u2718";
        }
    }

    /**
     * Method to mark the task as complete.
     *
     * @throws TaskAlreadyDoneException Throws an exception if the task is already done.
     */
    public void complete() throws TaskAlreadyDoneException {
        if (this.isCompleted()) {
            throw new TaskAlreadyDoneException();
        }
        this.isCompleted = true;
    }

    /**
     * Method to mark the task to be incomplete.
     *
     * @throws TaskAlreadyUnmarkedException Throws an exception if the task is already incomplete.
     */
    public void markAsNotComplete() throws TaskAlreadyUnmarkedException {
        if (!this.isCompleted()) {
            throw new TaskAlreadyUnmarkedException();
        }
        this.isCompleted = false;
    }

    /**
     * Method to obtain the label for the Task class.
     *
     * @return The label of Task.
     */
    public String getLabel() {
        return "G";
    }

    /**
     * Method to obtain peripheral info about the task.
     *
     * @return The peripheral info associated with the task.
     */
    public String getPeripheralInfo() {
        return "";
    }

    /**
     * Method to check the completion status of the task.
     *
     * @return A boolean indicating whether the task is complete or not.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Basic string representation of the task.
     *
     * @return The string representing the task.
     */
    @Override
    public String toString() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("[", status(), "] ", this.taskDescription);
        return outputFormatter.getFormattedOutput();
    }
}
