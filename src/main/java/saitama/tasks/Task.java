package saitama.tasks;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import saitama.tags.RecurFrequency;


/**
 * An abstract class representing a task.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected RecurFrequency recurFrequency;
    protected LocalDate lastResetDate;

    Task(String description, RecurFrequency recurFrequency) {
        this.description = description;
        this.recurFrequency = recurFrequency;
        this.isDone = false;
        this.lastResetDate = LocalDate.now();
    }

    Task(String description, boolean isDone, RecurFrequency recurFrequency,
         LocalDate lastResetDate) {
        this.description = description;
        this.recurFrequency = recurFrequency;
        this.lastResetDate = lastResetDate;
        this.isDone = isDone;
        if (shouldReset()) {
            this.isDone = false;
            this.lastResetDate = LocalDate.now();
        }
    }

    /**
     * Returns the done status of the task.
     *
     * @return the done status of the task.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the recursive tag of the task.
     *
     * @return the recursive tag of the task.
     */
    protected String getRecursiveFrequency() {
        if (recurFrequency != null) {
            return recurFrequency.getLabel();
        } else {
            return "";
        }
    }

    /**
     * Returns if the task is recursive.
     *
     * @return
     */
    public boolean isRecurring() {
        return recurFrequency != null;
    }

    /**
     * Checks if the timeframe of a recurring task should be reset.
     *
     * @return Whether a recurring task should be reset.
     */
    private boolean shouldReset() {
        LocalDate today = LocalDate.now();
        LocalDate resetDate;

        if (!isRecurring()) {
            return false;
        }

        assert recurFrequency != null : "Task detects an error when checking if a task needs to be reset!";

        switch (recurFrequency) {
        case DAILY:
            resetDate = lastResetDate.plusDays(1);
            break;
        case WEEKLY: //resets every Sunday
            resetDate = lastResetDate.with(next(SUNDAY));
            break;
        case BIWEEKLY: //resets every second Sunday
            resetDate = lastResetDate.with(next(SUNDAY)).with(next(SUNDAY));
            break;
        case MONTHLY: //resets every first day of the month
            resetDate = lastResetDate.with(TemporalAdjusters.firstDayOfNextMonth());
            break;
        default:
            resetDate = today;
        }

        if (!today.isBefore(resetDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    public abstract void saveTask(FileWriter fw) throws IOException;

    @Override
    public String toString() {
        return String.format("[%s]%s %s", this.getStatusIcon(), this.getRecursiveFrequency(),
                this.description);
    }
}
