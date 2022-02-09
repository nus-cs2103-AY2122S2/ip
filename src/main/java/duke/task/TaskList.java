package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a list of <code>Tasks</code>. A <code>TaskList</code> object is represented by an ArrayList of Tasks.
 */
public class TaskList {
    private ArrayList<Task> toDoList;
    private HashSet<LocalDate> dateList;

    /**
     * Returns a new instance of the <code>TaskList</code> object with the specified ArrayList.
     * @param arr ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> arr) {
        this.toDoList = arr;
        this.dateList = new HashSet<>();
    }

    /**
     * Returns the ArrayList of <code>Tasks</code>.
     * @return ArrayList of <code>Tasks</code>.
     */
    public ArrayList<Task> getToDoList() {
        return toDoList;
    }

    /**
     * Adds a <code>Task</code> to the list.
     * @param task <code>Task</code> to be added to the list.
     */
    public void add(Task task) {
        if (!(task instanceof Event)) {
            this.toDoList.add(task);
            return;
        }

        LocalDate date = task.getDate();
        if (dateList.contains(date)) {
            throw new DateClashException("Date clash with other task");
        }
        dateList.add(date);
        toDoList.add(task);
    }

    /**
     * Returns the <code>Task</code> at the specified index of the list.
     * @param idx Index of the task.
     * @return <code>Task</code> at the index.
     */
    public Task get(int idx) {
        return this.toDoList.get(idx);
    }

    /**
     * Removes the <code>Task</code> at the index of the list and returns it.
     * @param idx Index of the task.
     * @return <code>Task</code> removed at the index.
     */
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

    /**
     * Returns the size of the list
     * @return Size of the list
     */
    public int getSize() {
        return this.toDoList.size();
    }
}
