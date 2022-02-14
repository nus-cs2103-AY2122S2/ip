package duke.task;

import java.io.Serializable;

import duke.utils.Ui;



/**
 * Abstract class to represent
 * the different types of possible
 * tasks
 */
public abstract class Task implements Serializable {

    /** Stores a string description of the task **/
    protected String description;

    /** Stores whether the task has been completed **/
    protected boolean isFinished;


    /**
     * Constructor method for task
     *
     * @param desc Description of the Task
     * @param isCompleted Completion Status of the Task
     */
    public Task(String desc, boolean isCompleted) {
        this.description = desc;
        this.isFinished = isCompleted;
    }

    /**
     * Marks this instance of task
     * as completed
     *
     * @return Message confirming the task has been marked as completed
     *
     */
    public String markCompleted() {
        this.isFinished = true;
        return Ui.printMarkCompletion(this);
    }

    /**
     * Marks this instance of task
     * as not completed
     *
     * @return Message confirming the task has been marked as not completed
     */
    public String markNotCompleted() {
        this.isFinished = false;
        return Ui.printMarkUncompletion(this);
    }

    /**
     * toString method to get String
     * representation of this task
     *
     * @return String representation of this
     * task
     */
    @Override
    public String toString() {
        if (isFinished) {
            String temp = "[X] " + description;
            return temp;
        } else {
            String temp = "[ ] " + description;
            return temp;
        }
    }

    /**
     * Returns the completion status of
     * this task
     *
     * @return Completion status of this task
     */
    public boolean getCompletionStatus() {
        return this.isFinished;
    }

    /**
     * Return description of this task
     *
     * @return String description of this Task
     */
    public String getDescription() {
        return this.description;
    }

}
