package duke;

/**
 Class that acts as parent class of all task types.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     *
     * Method to set the task to be done
     * @return nothing
     */
    public void setMark() {

        this.isDone = true;
    }

    /**
     *
     * Method to set the task to be not done
     * @return nothing
     */
    public void setUnmark() {
        this.isDone = false;
    }

    /**
     *
     * Method to get status icon of task to be used to print in Tasklist
     * @return X if it is done, " " if it is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     *
     * Method to obtain description of task
     * @return desription of task in String type
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * Method to get type of task the task is
     * @return char for the different event type
     */
    public char getType() {
        if (this instanceof Event) {
            return 'e';
        } else if (this instanceof Deadline) {
            return 'd';
        } else if (this instanceof Todo) {
            return 't';
        } else {
            return 'x';
        }
    }

    /**
     *
     * Method to convert task to String type to be printed in the task list
     * @return String of the printed task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
