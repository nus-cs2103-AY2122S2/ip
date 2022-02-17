package tasks;
/**
* A Todo class extending the Task class to create a task for the user to do.
*/
public class Todo extends Task {
    /**
    * Class constructor.
    * <p>
    * defaults the isDone boolean to false
    *
    * @param  taskName  String containing the desired name of the task
    * @see    Task
    */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
    * Class constructor.
    *
    * @param  taskName  String containing the desired name of the task
    * @param  isDone    boolean denoting whether the task is currently done or not
    * @see    Task
    */
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
    * Returns the formatted String of the task.
    *
    * @return      the formatted String
    */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
