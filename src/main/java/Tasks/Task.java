package tasks;

import java.time.LocalDate;

/**
 * <h1>Task</h1>
 * <p>
 * Tasks contains the operations that can be performed on task.
 * </p>
 *
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Task class.
 */
public class Task {

    // symbol for indicating task is completed.
    private static final String CROSS = "X";

    // symbol for indicating task is yet to be completed.
    private static final String SPACE = " ";

    // symbol for task done.
    private static final String DONE = "[X]";

    // stores the start index value.
    private static final int START_INDEX = 0;

    // task variable stores the task.
    private final String task;

    // Tells if the task is completed.
    private boolean done;

    /**
     * Constructor for Task.
     * @param task the task to be done.
     * returns a new instance of task.
     */
    public Task(String task) {
        this.task = task.trim();
        this.done = false;
    }

    /**
     * Constructor for Task.
     * @param task the string output of Task.
     * @param dummyVariable int to differentiate from other constructor.
     * returns a new instance of Task.
     */
    public Task(String task, int dummyVariable) {

        if (task.substring(START_INDEX, DONE.length()).contains(DONE)) {
            this.done = true;
        } else {
            this.done = false;
        }

        this.task = task.substring(DONE.length()).trim();

    }

    /**
     * markDone marks the task completed.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * markDone unmarks the task as not completed.
     */
    public void unMarkDone() {
        this.done = false;
    }

    /**
     * returns the task to be performed.
     *
     * @return the task.
     */
    public String getTask() {
        return this.task;
    }


    /**
     * returns the date on which the task is due or the day event occurs.
     *
     * @return the date associated with task as LocalDate.
     */
    public LocalDate getDate() {
        return LocalDate.MIN;
    }

    /**
     * checks if the task is due before date.
     *
     * @param date the string given by user.
     * @return false trivially, as Tasks do not have deadlines, or event date.
     */
    public boolean isBefore(String date) {
        return false;
    }

    /**
     * checks if the task is on date.
     *
     * @param date the string given by the user
     * @return false trivially, as tasks do not have dates associated with them.
     */
    public boolean isOnDate(String date) {
        return false;
    }

    /**
     * checks if the task is on date.
     *
     * @param date the string given by the user
     * @return false trivially, as tasks do not have dates associated with them.
     */
    public boolean isOnDate(LocalDate date) {
        return false;
    }

    /**
     * checks if the task contains word.
     *
     * @param word the word to check for.
     * @return true if task contains the word; false otherwise.
     */
    public boolean contains(String word) {
        return this.task.contains(word);
    }

    /**
     * toSting method returns the string representation of the object.
     *
     * @return the string representing the task instance.
     */
    @Override
    public String toString() {
        return "[" + (this.done ? CROSS : SPACE) + "] " + this.task;
    }

}
