package duke.tasks;

import java.time.LocalDateTime;

/**
 * base abstract class from which all tasks inherit from
 * handles basic functionality such as marking and unmarking as done
 * Getters for various details
 */
public abstract class Task {
    protected String detail;
    protected boolean isMarked;

    /**
     * Constructor for the base Task class
     * @param detail
     */
    public Task(String detail) {
        this.detail = detail;
        this.isMarked = false;
    }

    /**
     * Abstract getter method for String representation of type of task
     * @return
     */
    public abstract String getType();

    /**
     * Abstract method to get Date of the task
     * @return
     */
    public abstract String getDate();

    /**
     * Abstract method to update Date of the task
     */
    public abstract void updateDate(LocalDateTime Update);

    /**
     * Abstract method to update Detail of the task
     */
    public abstract void updateDetail(String update);

    /**
     * Marks the task as done
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Marks the task as not done
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Getter function for the detail of the task
     * @return String of the task details
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Returns the status of the task 1 if done 0 if not done
     * @return 1 or 0 representing status of the task
     */
    public String getMark() {
        if (isMarked) {
            return "1";
        } else {
            return "0";
        }
    }
}
