package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> toDoList;

    public TaskList(ArrayList<Task> arr) {
        this.toDoList = arr;
    }

    public ArrayList<Task> getToDoList() {
        return toDoList;
    }

    public void add(Task task) {
        this.toDoList.add(task);
    }

    public Task get(int idx) {
        return this.toDoList.get(idx);
    }

    public Task remove(int idx) {
        return this.toDoList.remove(idx);
    }

    /**
     * Returns a list of <code>Tasks</code> that contains the specified keyword.
     * @param keyword The keyword to be searched.
     * @return List of relevant tasks that contains the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> relevantTasks = new ArrayList<>(100);
        for (Task task : toDoList) {
            if (task.getName().contains(keyword)) {
                relevantTasks.add(task);
            }
        }
        return relevantTasks;
    }
}
