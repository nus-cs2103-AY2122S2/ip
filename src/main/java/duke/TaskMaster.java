package duke;

import duke.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the overarching list of tasks the user has noted down.
 */
public class TaskMaster {

    private ArrayList<Task> currentTasks;
    private ArrayList<Task> archivedTasks;

    /**
     * Constructor
     * @param group an arraylist containing 2 arraylists of tasks
     */
    public TaskMaster(List<ArrayList<Task>> group) {
        this.currentTasks = group.get(0);
        this.archivedTasks = group.get(1);
    }

    /**
     * adds the given task to the stored list of tasks
     * @param t Task t to be added
     */
    public void addTask(Task t) {
        int sizeBefore = this.currentTasks.size();
        this.currentTasks.add(t);
        assert this.currentTasks.size() == sizeBefore + 1;
    }

    /**
     * Deletes the task from the list of tasks, with index position i - 1
     * @param i int representing the index - 1 to be deleted
     * @return the deleted task
     */
    public Task deleteTask(int i) {
        int sizeBefore = this.currentTasks.size();
        Task removedTask = this.currentTasks.remove(i - 1);
        assert this.currentTasks.size() == sizeBefore - 1;
        return removedTask;//-1 because arr index starts frm 0
    }

    /**
     * retrieves all the tasks.
     * @return the tasks as an arraylist.
     */
    public ArrayList<Task> getCurrentTasks() {
        return this.currentTasks;
    }

    public ArrayList<Task> getArchivedTasks() { return this.archivedTasks; }

    /**
     * finds all tasks that have details that are related to the keyword.
     * @param keyword String to be matched on.
     * @return an arraylist of matching tasks
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.currentTasks) {
            if (task.getDetails().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     *
     */
    public void moveToArchives() {
        ArrayList <Task> toBeArchived = new ArrayList<>();
        for (Task t : this.currentTasks) {
            if (t.isComplete()) {
                toBeArchived.add(t);
            }
        }
        this.archivedTasks.addAll(toBeArchived);
        this.currentTasks.removeAll(toBeArchived);
    }
}
