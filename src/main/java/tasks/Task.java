package tasks;
/**
* Task class to create a task for the user to do.
*/
public class Task {
    protected boolean isDone = false;
    protected String taskName = "";

    ////////////////////////////////////////////////////////////////
    // Constructors
    /**
    * Class constructor.
    *
    * @param  taskName  String containing the desired name of the task
    * @param  isDone    boolean denoting whether the task is currently done or not
    */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
    * Class constructor.
    * <p>
    * defaults the isDone boolean to false
    *
    * @param  taskName  String containing the desired name of the task
    */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    ////////////////////////////////////////////////////////////////
    // Getters

    /**
    * Returns the String task name.
    *
    * @return      String containing task name
    */
    public String getTaskName() {
        return this.taskName;
    }
    /**
    * Returns the boolean whether the task is done.
    *
    * @return      boolean denoting whether task is done
    */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
    * Returns the String task status icon.
    * "X" is "done" and a blank is "not done"
    *
    * @return      String containing task status icon
    */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    ////////////////////////////////////////////////////////////////
    // Methods

    /**
    * Sets the boolean isDone to mark the task as done or not done.
    *
    * @param  bool the boolean to set isDone to
    */
    public void markTask(boolean bool) {
        this.isDone = bool;
    }

    /**
    * Returns the formatted String of the task.
    *
    * @return      the formatted String
    */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}