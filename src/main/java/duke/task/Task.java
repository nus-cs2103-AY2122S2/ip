package duke.task;

/**
 * Tasks represent activities that the user has to do.
 * There are 3 different types of tasks: Todos, Deadlines and Events
 *
 * @author Jian Rong
 */
public abstract class Task {

    boolean checked = false;

    /**
     * Sets the value of a task to true.
     */
    public void setChecked() {
        this.checked = true;
    }

    /**
     * Sets the value of a task to false.
     */
    public void setUnChecked(){
        this.checked = false;
    }
}
