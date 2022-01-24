package DukeUtils;
import Task.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The type TaskList.
 */
public class TaskList {
    /**
     * The array list storing all tasks.
     */
    public ArrayList<Task> tasksArrayList = new ArrayList<>();
    /**
     * The set storing all tasks.
     */
    public Set<Task> taskSet = new HashSet<>();

    /**
     * Instantiates a new Task list.
     *
     * @param tasksArrayList the tasks array list from task.txt
     */
    public TaskList(ArrayList<Task> tasksArrayList) {
        this.taskSet.addAll(tasksArrayList);
        this.tasksArrayList.addAll(tasksArrayList);
    }
}
