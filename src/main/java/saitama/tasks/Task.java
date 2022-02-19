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
            reset();
        }
    }

    /**
     * Resets the task when it recurs.
     */
    protected void reset() {
        this.isDone = false;
        this.lastResetDate = LocalDate.now();
    }

    /**
     * Returns the done status of the task.
     *
     * @return the done status of the task
     */
    protected String getStatusIcon() {
        String tick = "\u2713";
        String cross = "\u2718";
        return (isDone ? tick : cross);
    }

    /**
     * Returns the string label of the RecurFrequency tag.
     *
     * @return The string label of the RecurFrequency tag
     */
    protected String getRecursiveFrequency() {
        if (recurFrequency != null) {
            return recurFrequency.getLabel();
        } else {
            return "";
        }
    }

    /**
     * Returns if the task is recurring.
     *
     * @return Whether the task is recurring
     */
    public boolean isRecurring() {
        return recurFrequency != null;
    }

    /**
     * Checks if the timeframe of a recurring task should be reset.
     *
     * @return Whether this task should be reset
     */
    protected boolean shouldReset() {
        if (!isRecurring()) {
            return false;
        }
        assert recurFrequency != null : "Task detects an error when checking if a task needs to be reset!";

        LocalDate today = LocalDate.now();
        LocalDate resetDate = lastResetDate;
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
            assert false; //recurFrequency can only take the enumerated forms, or null
        }

        if (!today.isBefore(resetDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks a task as not done
     */
    public void markAsUndone() {
        isDone = false;
    }

    public abstract void saveTask(FileWriter fw) throws IOException;

    @Override
    public String toString() {
        return String.format("[%s]%s %s", getStatusIcon(), getRecursiveFrequency(), description);
    }
}
