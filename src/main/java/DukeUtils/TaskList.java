package DukeUtils;
import Task.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TaskList {
    public ArrayList<Task> tasksArrayList = new ArrayList<>();
    public Set<Task> taskSet = new HashSet<>();

    public TaskList(ArrayList<Task> tasksArrayList) {
        this.taskSet.addAll(tasksArrayList);
        this.tasksArrayList.addAll(tasksArrayList);
    }

    public TaskList(){}
}
