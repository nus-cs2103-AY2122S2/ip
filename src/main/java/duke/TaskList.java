package duke;

import java.util.ArrayList;

/**
 * Handles the current list of Tasks while Duke is running
 */
public class TaskList {
    private final String LIST_INIT = "Here are the task:\n";
    private ArrayList<Task> masterList;

    public TaskList(ArrayList<Task> list) {
        this.masterList = list;
    }

    public TaskList() {
        this.masterList = new ArrayList<>();
    }

    public int size() {
        return this.masterList.size();
    }

    public Task get(int index) {
        return this.masterList.get(index);
    }

    public void addTask(Task task) {
        masterList.add(task);
    }

    /**
     * Shows the list of existing tasks
     *
     * @return String of list of tasks
     */
    public String list() {
        String toPrint = LIST_INIT;
        for (int i = 0; i < masterList.size(); i++) {
            Task currTask = masterList.get(i);
            toPrint += "\t" + (i + 1) + ". " + currTask + "\n";
        }
        return toPrint;
    }

    /**
     * Mark a task as done
     *
     * @param index Index of task in masterList
     * @return Task marked as done
     */
    public Task mark(int index) {
        Task currTask = masterList.get(index);
        currTask.markAsDone();
        masterList.set(index, currTask);
        return currTask;
    }

    /**
     * Mark a task as undone
     *
     * @param index Index of task in masterList
     * @return Task marked as undone
     */
    public Task unmark(int index) {
        Task currTask = masterList.get(index);
        currTask.unmarkItem();
        masterList.set(index, currTask);
        return currTask;
    }

    /**
     * Delete a task
     *
     * @param index Index of task in masterList
     * @return Deleted task
     */
    public Task delete(int index) {
        Task currTask = masterList.get(index);
        masterList.remove(index);
        return currTask;
    }

    public String find(String keyword) {
        String toReturn = "Here are the matching tasks in your lists:\n";
        for (int i = 0; i < masterList.size(); i++) {
            if (masterList.get(i).toString().contains(keyword)) {
                toReturn += (i + 1) + "." + masterList.get(i) + "\n";
            }
        }

        return toReturn;
    }
}
