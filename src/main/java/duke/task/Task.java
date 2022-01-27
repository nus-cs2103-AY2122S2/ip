package duke.task;

import duke.utils.Ui;

import java.io.Serializable;


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
     * @param x Description of the Task
     * @param y Completion Status of the Task
     */
    public Task(String x, boolean y){
        this.description = x;
        this.isFinished = y;
    }

    /**
     * Marks this instance of task
     * as completed
     */
    public void markCompleted(){
        this.isFinished = true;
        Ui.printMarkCompletion(this);
    }

    /**
     * Marks this instance of task
     * as not completed
     */
    public void markNotCompleted(){
        this.isFinished = false;
        Ui.printMarkUncompletion(this);
    }

    /**
     * toString method to get String
     * representation of this task
     *
     * @return String representation of this
     * task
     */
    @Override
    public String toString(){
        if(isFinished){
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
    public boolean getCompletionStatus(){
        return this.isFinished;
    }

}
