package duke.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import duke.task.Task;

/**
 * The type TaskList.
 */
public class TaskList {
    /**
     * The array list storing all tasks.
     */
    private final ArrayList<Task> tasksArrayList = new ArrayList<>();
    /**
     * The set storing all tasks.
     */
    private final Set<Task> taskSet = new HashSet<>();

    /**
     * Instantiates a new Task list.
     *
     * @param tasksArrayList the tasks array list from task.txt
     */
    public TaskList(ArrayList<Task> tasksArrayList) {
        this.taskSet.addAll(tasksArrayList);
        this.tasksArrayList.addAll(tasksArrayList);
    }

    public ArrayList<Task> getTaskList() {
        return this.tasksArrayList;
    }

    public Set<Task> getTaskSet() {
        return this.taskSet;
    }
}
