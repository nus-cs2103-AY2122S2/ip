package duke;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructor that instantiate an ArrayList of task
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for duke.TaskList object
     * @parm List of task
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds tasks to the list
     * @param Task task object
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * deletes task in the given index
     * @param integer of taskNumber
     */
    public void deleteTask(int taskNum) {
        Task deletedTask = this.tasks.get(taskNum - 1);
        this.tasks.remove(taskNum - 1);
    }
    /**
     * marks task at specific index in the list as done
     * @param integer of taskNumber
     */
    public void markAsDone(int taskNum) {
        this.tasks.get(taskNum - 1).setAsMarked(); // calling method in duke.Task class
    }

    /**
     * marks task at specific index in the list as Not done
     * @param integer of taskNumber
     */
    public void markAsNotDone(int taskNum) {
        this.tasks.get(taskNum - 1).setAsUnmarked(); // calling method in duke.Task class
    }

    /**
     * Retrieves the task at that specified index
     * @param integer of taskNumber
     * @return duke.Task task
     */
    public Task get(int taskNum) {
        return this.tasks.get(taskNum);
    }

    /**
     * Retrieves the List of Tasks object
     * @return List<duke.Task> task
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gives the number of tasks in the list at the moment.
     * @return integer
     */
    public int size() {
        return this.tasks.size();
    }

}



